""" 
Handler para permitir a criação, atualização, adição e remoção de filmes do banco de dados. 
"""
from core.handlers.base_handler import BaseHandler
from core.settings import config
from core.authentication.permissions import permission

from database.database_service import DatabaseService as db

status = config.status

class MovieHandler(BaseHandler):
    @staticmethod
    def get_movie_by_id(id_movie: int):
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT * FROM movie WHERE movie.id_movie = %s;", (id_movie,))
            return session.fetchone()

    def post_movie(self, handler):
        body = handler.parse_json_body()

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT * from movie WHERE LOWER(movie.movie_title) = LOWER(%s);", (body["titulo"],))
            result = session.fetchone()

        if not result:
            with db.session() as session:
                session.execute("USE cinescope;")
                
                query = """
                    INSERT INTO movie(movie_title, duration_time, publication_year, movie_synopsis, movie_poster) 
                    VALUES
                        (%s, %s, %s, %s, %s);
                """
                session.execute(query, (body["movie_title"], body["duration_time"], body["publication_year"], body["movie_synopsis"], body["movie_poster"],))

            handler.send_json_response({"message": "Movie successfully created"}, status["HTTP_201_CREATED"])
        else:
            handler.send_json_response({"error": "Movie alredy exist"}, status["HTTP_409_CONFLICT"])


    def get_movies(self, handler):
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT * FROM movie;")
            result = session.fetchall()

        movies_json = []
        for res in result:
            movie = {
                "id_movie": int(res[0]),
                "movie_title": str(res[1]),
                "duration_time": str(res[2]),
                "publication_year": int(res[3]),
                "movie_synopsis": str(res[4]),
                "movie_poster": str(res[5])
            }

            movies_json.append(movie)

        handler.send_json_response(movies_json, status["HTTP_200_OK"])
        
    def get_movie(self, handler, id_movie: int):
        result = MovieHandler.get_movie_by_id(id_movie)
        
        if result:
            movie_json = {
                "id_movie": int(result[0]),
                "titulo": str(result[1]),
                "duracao": str(result[2]),
                "ano_publicacao": int(result[3]),
                "sinopse": str(result[4]),
                "poster": str(result[5])
            }
            
            handler.send_json_response(movie_json, status["HTTP_200_OK"])
        else:
            handler.send_json_response({"error": "Movie not found"}, status["HTTP_404_NOT_FOUND"])

    def put_movie(self, handler, id_movie: int):
        body = handler.parse_json_body()

        result = MovieHandler.get_movie_by_id(id_movie)

        if result:
            with db.session() as session:
                session.execute("USE cinescope;")
                query = """
                    UPDATE filme 
                    SET 
                        movie_title = %s, 
                        duration_time = %s,
                        publication_year = %s, 
                        movie_synopsis = %s, 
                        movie_poster = %s
                    WHERE 
                        id_movie = %s;
                """

                session.execute(query, (body["movie_title"], body["duration_time"], body["publication_year"], body["movie_synopsis"], body["movie_poster"], id_movie,))

            handler.send_json_response(body, status["HTTP_200_OK"])
        else:
            handler.send_json_response({"error": "Movie not found"}, status["HTTP_404_NOT_FOUND"])

    @permission("administrador")
    def delete_movie(self, handler, id_movie: int, token: str = None):
        result = MovieHandler.get_movie_by_id(id_movie)

        if result:
            with db.session() as session:
                session.execute("USE cinescope;")
                session.execute("DELETE FROM movie WHERE movie.id_movie = %s;", (id_movie,))  

            handler.send_json_response({}, status["HTTP_204_NO_CONTENT"])
        else:
            handler.send_json_response({"error": "Movie not found"}, status["HTTP_404_NOT_FOUND"])

    def filter_movies(self, handler, query):
        query = query.split("=")[1]
        query = f"%{query}%"
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT * FROM movie WHERE LOWER(titulo) LIKE %s;", (query,))
            result = session.fetchall()

        movies_json = []
        for res in result:
            movie = {
                "id_movie": int(res[0]),
                "movie_title": str(res[1]),
                "duration_time": str(res[2]),
                "publication_year": int(res[3]),
                "movie_synopsis": str(res[4]),
                "movie_poster": str(res[5])
            }

            movies_json.append(movie)
        
        handler.send_json_response(movies_json, status["HTTP_200_OK"])