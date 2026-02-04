from http import HTTPStatus

from cinescope.api.services.genre_service import genre_service
from cinescope.infra.db.deps import get_session
from cinescope.infra.server.settings import settings

serverType = settings.server


def post_genre(server: serverType, method: str = "POST"):
    try:
        data = server.parse_json_body()
        with get_session() as db:
            genre = genre_service.get_genre_by_name(data["genre"], db)
            if genre:
                server.send_json_response(
                    {"message": "Genre alredy exists"},
                    HTTPStatus.CONFLICT
                )
            else:
                genre_service.create(data, db)
                server.send_json_response(data, HTTPStatus.CREATED)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def get_one_genre(server: serverType, method: str = "GET"):
    try:
        pk = server.server_path["id"]
        with get_session() as db:
            genre = genre_service.get_one(pk, db)

            if genre:
                server.send_json_response(genre)
            else:
                server.send_json_response(
                    {"message": f"Genre with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def get_genres(server: serverType, method: str = "GET"):
    try:
        with get_session() as db:
            genres = genre_service.get_all(db)
            server.send_json_response(genres)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def patch_genre(server: serverType, method="PATCH"):
    try:
        data = server.parse_json_body()
        pk = server.server_path["id"]
        with get_session() as db:
            genre = genre_service.patch(pk, data, db)

            if not genre:
                server.send_json_response(
                    {"message": f"Genre with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
            else:
                server.send_json_response(genre)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def delete_genre(server: serverType, method: str = "DELETE"):
    try:
        pk = server.server_path["id"]
        with get_session() as db:
            genre = genre_service.delete(pk, db)

            if not genre:
                server.send_json_response(
                    {"message": f"Genre with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
            else:
                server.send_status_only(HTTPStatus.NO_CONTENT)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
