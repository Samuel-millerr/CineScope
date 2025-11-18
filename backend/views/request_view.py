from core.handlers.base_handler import BaseHandler
from database.database_service import DatabaseService as db
from core.authentication.permissions import permission
from core.settings import config
import json as json

from views.auth_view import AuthHandler

status = config.status

class RequestHandler(BaseHandler):
    def get_requests(self, handler):
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("""
                SELECT r.id_request, r.id_user, r.request_type, r.request_date, r.request_status, r.request_body, u.user_name
                FROM request r
                JOIN user u ON u.id_user = r.id_user
                WHERE r.request_status = 'Pendente';
            """)
            result = session.fetchall()

        requests_json = []
        for row in result:
            requests_json.append({
                "id_request": row[0],
                "id_user": row[1],
                "user_name": row[6],
                "request_type": row[2],
                "request_date": str(row[3]),
                "request_status": row[4],
                "request_body": json.loads(row[5])
            })

        handler.send_json_response(requests_json, status["HTTP_200_OK"])
    
    def get_requests_by_user(self, handler, user):
        id_user = AuthHandler.get_user_id(user)

        query = """
            SELECT 
                request.request_date,
                request.request_type,
                request.request_status,
                request.request_body
            FROM request
            WHERE request.id_user = %s;
        """

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (id_user["id_user"],))
            result = session.fetchall()

        requests_json = []
        for row in result:
            requests_json.append({
                "request_date": row[0].isoformat(),
                "request_type": row[1],
                "request_status": row[2],
                "request_body": row[3]
            })

        handler.send_json_response(requests_json, status["HTTP_200_OK"])
            
    @permission("administrador")
    def allow_request(self, handler, id_request: int):
        with db.session() as session:
            session.execute("USE cinescope;")

            session.execute("""
                SELECT request_type, id_movie, request_body
                FROM request
                WHERE id_request = %s;
            """, (id_request,))
            result = session.fetchone()

            if not result:
                handler.send_json_response({"error": "Request not found"}, 404)
                return

            request_type, id_movie, body_json = result
            body = json.loads(body_json)

            if request_type == "Adição":
                session.execute("""
                    INSERT INTO movie(movie_title, duration_time, publication_year,
                                    movie_synopsis, movie_poster)
                    VALUES (%s, %s, %s, %s, %s);
                """, (
                    body["movie_title"],
                    body["duration_time"],
                    body["publication_year"],
                    body["movie_synopsis"],
                    body["movie_poster"]
                ))

                session.execute("SELECT LAST_INSERT_ID();")
                id_movie = session.fetchone()[0]

            else:
                session.execute("""
                    UPDATE movie SET
                        movie_title=%s,
                        duration_time=%s,
                        publication_year=%s,
                        movie_synopsis=%s,
                        movie_poster=%s
                    WHERE id_movie=%s;
                """, (
                    body["movie_title"],
                    body["duration_time"],
                    body["publication_year"],
                    body["movie_synopsis"],
                    body["movie_poster"],
                    id_movie
                ))

                session.execute("DELETE FROM movie_genre WHERE id_movie=%s;", (id_movie,))
                session.execute("DELETE FROM movie_actor WHERE id_movie=%s;", (id_movie,))
                session.execute("DELETE FROM movie_director WHERE id_movie=%s;", (id_movie,))

            for g in body.get("genres", []):
                session.execute("INSERT INTO movie_genre VALUES(NULL, %s, %s);", (id_movie, g))
            for a in body.get("actors", []):
                session.execute("INSERT INTO movie_actor VALUES(NULL, %s, %s);", (id_movie, a))
            for d in body.get("directors", []):
                session.execute("INSERT INTO movie_director VALUES(NULL, %s, %s);", (id_movie, d))

            # Atualiza o status
            session.execute("""
                UPDATE request SET request_status = 'Aprovado'
                WHERE id_request = %s;
            """, (id_request,))

        handler.send_json_response({"message": "Requisição processada com sucesso!"}, 200)


    @permission("administrador")
    def deny_request(self, handler, id_request: int):

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("""
                UPDATE request SET request_status = 'Reprovado'
                WHERE id_request = %s;
            """, (id_request,))

        handler.send_json_response({"message": "Solicitação recusada."}, 200)
