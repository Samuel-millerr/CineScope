from http import HTTPStatus

from cinescope.api.services.request_service import request_service
from cinescope.infra.db.deps import get_session
from cinescope.infra.server.settings import settings

serverType = settings.server


class RequestRouter:
    @staticmethod
    def post_request(server: serverType, method: str = "POST"):
        try:
            data = server.parse_json_body()
            with get_session() as db:
                request_service.create(data, db)
                server.send_json_response(data, HTTPStatus.CREATED)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def get_requests(server: serverType, method: str = "GET"):
        try:
            with get_session() as db:
                requests = request_service.get_all(db)
                server.send_json_response(requests)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def delete_request(server: serverType, method: str = "DELETE"):
        try:
            pk = server.server_path["id"]
            with get_session() as db:
                request = request_service.delete(pk, db)

                if not request:
                    server.send_json_response(
                        {"message": f"Request with ID {pk} not found"},
                        HTTPStatus.NOT_FOUND
                    )
                else:
                    server.send_status_only(HTTPStatus.NO_CONTENT)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e


request_router = RequestRouter()
