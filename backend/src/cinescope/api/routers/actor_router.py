import json

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
        server.send_response_only(HTTPStatus.INTERNAL_SERVER_ERROR)

def get_one_actor(server: HTTPHandler, method: str = "GET"):
    try:
        pk = server.server_path["id"]
        with get_session() as db:
            actor = actor_service.get_one(pk, db)
            actor_json = json.dumps(actor)
            if actor:
                server.send_response(actor_json, HTTPStatus)
    except: 
        server.send_response_only(HTTPStatus.INTERNAL_SERVER_ERROR)

def get_actors(server: HTTPHandler, method: str = "GET"):
    print("getando todos os atores")
    ...