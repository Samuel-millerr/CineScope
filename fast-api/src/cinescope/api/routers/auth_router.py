from http import HTTPStatus
from http.server import SimpleHTTPRequestHandler as HTTPHandler

from cinescope.api.services.auth_service import auth_service
from cinescope.api.services.user_service import user_service
from cinescope.infra.db.deps import get_session
from cinescope.infra.server.settings import settings

serverType = settings.server

class AuthRouter:
    @staticmethod
    def create_acess_token(server: serverType, method: str = "POST"):
        data = server.parse_json_body()

        with get_session() as db:
            user = user_service.get_user_by_atribute("email", data["email"] , db)

            if not user:
                server.send_json_response({"message": "user not found"}, status=HTTPStatus.NOT_FOUND)
                return None
            
            if not auth_service.verify_password(data["password"], user.hashed_password):
                server.send_json_response({"message": "incorrect email or password"})
                return None
            
            server.send_json_response({"access_token": auth_service.create_acess_token(data), "token_type": "Bearer"})
            
auth_router = AuthRouter()

    
