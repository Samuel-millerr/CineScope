""" 
Handler para permitir a criação, atualização, adição e remoção de filmes do banco de dados. 
"""
from core.handlers.base_handler import BaseHandler
from core.settings import config
from core.authentication.permissions import permission
from filters.movie_filters import MovieFilters

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
                
                query_movie = """
                    INSERT INTO movie(movie_title, duration_time, publication_year, movie_synopsis, movie_poster) 
                    VALUES
                        (%s, %s, %s, %s, %s);
                """
                session.execute(query_movie, (body["movie_title"], body["duration_time"], body["publication_year"], body["movie_synopsis"], body["movie_poster"],))

                session.execute("SELECT LAST_INSERT_ID();")
                id_movie = session.fetchone()[0]

                genres = body.get("genres", [])
                for id_genre in genres:
                    query_genre = """
                        INSERT INTO movie_genre (id_movie, id_genre)
                        VALUES (%s, %s);
                    """
                    session.execute(query_genre, (id_movie, id_genre))
        
                actors = body.get("actors", [])
                for id_actor in actors:
                    query_actor = """
                        INSERT INTO movie_actor (id_movie, id_actor)
                        VALUES (%s, %s);
                    """
                    session.execute(query_actor, (id_movie, id_actor))
                    
                directors = body.get("directors", [])
                for id_director in directors:
                    query_director = """
                        INSERT INTO movie_director(id_movie, id_director)
                        VALUES (%s, %s);
                    """
                    session.execute(query_director, (id_movie, id_director))
                    
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
                "movie_title": str(result[1]),
                "duration_time": str(result[2]),
                "publication_year": int(result[3]),
                "movie_synopsis": str(result[4]),
                "movie_poster": str(result[5])
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
        params = {}
        if query:
            for param in query.split("&"):
                if "=" in param:
                    key, value = param.split("=", 1)
                    params[key] = value

        filtro = params.get("filter")
    
        if filtro:
            if filtro == "movies_simple_info":
                movies_json = MovieFilters.movies_simple_info()
            elif filtro == "movie_simple_info":
                parse_path = handler.parse_path(handler.path)
                movies_json = MovieFilters.movie_simple_info(parse_path["id"])
            elif filtro.split("?")[0] == "movies_related":
                genre = filtro.split("?")[1].split("=")[1]
                movie_title = filtro.split("?")[2].split("=")[1]
                movies_json = MovieFilters.movies_related(genre, movie_title)
            elif filtro.split("?")[0] == "movies_adm_list":
                movies_json = MovieFilters.movies_adm_list()
        
        handler.send_json_response(movies_json, status["HTTP_200_OK"])