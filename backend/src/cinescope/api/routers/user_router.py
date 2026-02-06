from http import HTTPStatus

from cinescope.api.services.user_service import user_service
from cinescope.infra.db.deps import get_session
from cinescope.infra.server.settings import settings

serverType = settings.server


class UserRouter:
    @staticmethod
    def post_user(server: serverType, method: str = "POST"):
        try:
            data = server.parse_json_body()
            with get_session() as db:
                user, message = user_service.create(data, db)
                if not user:
                    server.send_json_response(
                        message,
                        HTTPStatus.CONFLICT
                    )
                else:
                    server.send_json_response(data, HTTPStatus.CREATED)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def get_one_user(server: serverType, method: str = "GET"):
        try:
            pk = server.server_path["id"]
            with get_session() as db:
                user = user_service.get_one(pk, db)

                if user:
                    server.send_json_response(user)
                else:
                    server.send_json_response(
                        {"message": f"Genre with ID {pk} not found"},
                        HTTPStatus.NOT_FOUND
                    )
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e
    
    @staticmethod
    def get_users(server: serverType, method: str = "GET"):
        try:
            with get_session() as db:
                users = user_service.get_all(db)
                server.send_json_response(users)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e
        
    @staticmethod
    def delete_users(server: serverType, method: str = "DELETE"):
        try:
            pk = server.server_path["id"]
            with get_session() as db:
                user = user_service.delete(pk, db)

                if not user:
                    server.send_json_response(
                        {"message": f"User with ID {pk} not found"},
                        HTTPStatus.NOT_FOUND
                    )
                else:
                    server.send_status_only(HTTPStatus.NO_CONTENT)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

user_router = UserRouter()
