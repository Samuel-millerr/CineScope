from http import HTTPStatus
from http.server import SimpleHTTPRequestHandler as HTTPHandler

from cinescope.api.services.actor_service import actor_service
from cinescope.infra.db.deps import get_session


def post_actor(server: HTTPHandler, method: str = "POST"):
    try:
        data = server.parse_json_body()
        with get_session() as db:
            actor = actor_service.get_actor_by_name(data["actor_name"], db)
            if actor:
                server.send_json_response(
                    {"message": "actor alredy exists"},
                    HTTPStatus.CONFLICT
                )
            else:
                actor_service.create(data, db)
                server.send_json_response(data, HTTPStatus.CREATED)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def get_one_actor(server: HTTPHandler, method: str = "GET"):
    try:
        pk = server.server_path["id"]
        with get_session() as db:
            actor = actor_service.get_one(pk, db)

            if actor:
                server.send_json_response(actor)
            else:
                server.send_json_response(
                    {"message": f"actor with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def get_actors(server: HTTPHandler, method: str = "GET"):
    try:
        with get_session() as db:
            actors = actor_service.get_all(db)
            server.send_json_response(actors)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def patch_actor(server: HTTPHandler, method: str = "PATCH"):
    try:
        data = server.parse_json_body()
        pk = server.server_path["id"]
        with get_session() as db:
            actor = actor_service.patch(pk, data, db)

            if not actor:
                server.send_json_response(
                    {"message": f"actor with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
            else:
                server.send_json_response(actor)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)


def delete_actor(server: HTTPHandler, method: str = "DELETE"):
    try:
        pk = server.server_path["id"]
        with get_session() as db:
            actor = actor_service.delete(pk, db)

            if not actor:
                server.send_json_response(
                    {"message": f"actor with ID {pk} not found"},
                    HTTPStatus.NOT_FOUND
                )
            else:
                print(actor)
                server.send_status_only(HTTPStatus.NO_CONTENT)
    except:
        server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
