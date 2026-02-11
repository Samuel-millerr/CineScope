from http import HTTPStatus

from cinescope.api.services.movie_service import movie_service
from cinescope.infra.db.deps import get_session
from cinescope.infra.server.settings import settings

serverType = settings.server


class MovieRouter:
    @staticmethod
    def post_movie(server: serverType, method: str = "POST"):
        try:
            data = server.parse_json_body()
            with get_session() as db:
                movie = movie_service.get_service_by_title(data["movie_title"], db)
                if movie:
                    server.send_json_response(
                        {"message": "Movie alredy exists"},
                        HTTPStatus.CONFLICT
                    )
                else:
                    movie_service.create(data, db)
                    server.send_json_response(data, HTTPStatus.CREATED)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def get_one_movie(server: serverType, method: str = "GET"):
        try:
            pk = server.server_path["id"]
            with get_session() as db:
                movie = movie_service.get_one(pk, db)

                if movie:
                    server.send_json_response(movie)
                else:
                    server.send_json_response(
                        {"message": f"Movie with ID {pk} not found"},
                        HTTPStatus.NOT_FOUND
                    )
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def get_movies(server: serverType, method: str = "GET"):
        try:
            with get_session() as db:
                movies = movie_service.get_all(db)
                server.send_json_response(movies)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def patch_movie(server: serverType, method: str = "PATCH"):
        try:
            data = server.parse_json_body()
            pk = server.server_path["id"]
            with get_session() as db:
                movie = movie_service.patch(pk, data, db)

                if not movie:
                    server.send_json_response(
                        {"message": f"Movie with ID {pk} not found"},
                        HTTPStatus.NOT_FOUND
                    )
                else:
                    server.send_json_response(movie)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def delete_movie(server: serverType, method: str = "DELETE"):
        try:
            pk = server.server_path["id"]
            with get_session() as db:
                movie = movie_service.delete(pk, db)

                if not movie:
                    server.send_json_response(
                        {"message": f"Movie with ID {pk} not found"},
                        HTTPStatus.NOT_FOUND
                    )
                else:
                    server.send_status_only(HTTPStatus.NO_CONTENT)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e


movie_router = MovieRouter()
