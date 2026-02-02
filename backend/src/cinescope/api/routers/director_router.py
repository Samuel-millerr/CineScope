from http import HTTPStatus
from http.server import SimpleHTTPRequestHandler as HTTPHandler

from cinescope.api.services.director_service import director_service
from cinescope.infra.db.deps import get_session


def post_director(server: HTTPHandler, method: str = "POST"):
    try:
        data = server.parse_json_body()
        with get_session() as db:
            director = director_service.get_director_by_name(data["director_name"], db)
            if director:
                server.send_json_response(
                    {"message": "Director alredy exists"},
                    HTTPStatus.CONFLICT
                )
            else:
                director_service.create(data, db)
                server.send_json_response(data, HTTPStatus.CREATED)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def get_one_director(server: HTTPHandler, method: str = "GET"):
    try:
        pk = server.server_path["id"]
        with get_session() as db:
            director = director_service.get_one(pk, db)

            if director:
                server.send_json_response(director)
            else:
                server.send_json_response(
                    {"message": f"Director with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def get_directors(server: HTTPHandler, method: str = "GET"):
    try:
        with get_session() as db:
            directors = director_service.get_all(db)
            server.send_json_response(directors)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def patch_director(server: HTTPHandler, method: str = "PATCH"):
    try:
        data = server.parse_json_body()
        pk = server.server_path["id"]
        with get_session() as db:
            director = director_service.patch(pk, data, db)

            if not director:
                server.send_json_response(
                    {"message": f"Director with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
            else:
                server.send_json_response(director)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def delete_director(server: HTTPHandler, method: str = "DELETE"):
    try:
        pk = server.server_path["id"]
        with get_session() as db:
            director = director_service.delete(pk, db)

            if not director:
                server.send_json_response(
                    {"message": f"Director with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
            else:
                server.send_status_only(HTTPStatus.NO_CONTENT)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
