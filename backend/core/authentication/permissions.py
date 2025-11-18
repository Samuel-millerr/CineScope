from functools import wraps
from core.authentication.authentication import Authentication
from core.handlers.base_handler import BaseHandler


def permission(role_required):
    def decorator(func):
        @wraps(func)
        def wrapper(self, handler: BaseHandler, *args, **kwargs):
            token = handler.get_token()

            if not token:
                return handler.send_json_response(
                    {"error": "Token não enviado"},
                    401
                )

            data = Authentication.verify_token(token)
            if isinstance(data, dict) and data.get("error"):
                return handler.send_json_response(
                    {"error": data["error"]},
                    data["status"]
                )

            if data.get("role").lower() != role_required.lower():
                return handler.send_json_response(
                    {"error": "Acesso negado"},
                    403
                )

            return func(self, handler, *args, **kwargs)

        return wrapper
    return decorator
