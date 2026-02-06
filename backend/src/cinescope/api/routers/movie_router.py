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
        
movie_router = MovieRouter()