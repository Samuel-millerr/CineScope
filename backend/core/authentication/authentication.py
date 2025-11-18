import jwt
from datetime import datetime, timedelta

SECRET_KEY = "senha_super_secreta"
ALGORITHM = "HS256"

class Authentication:
    @staticmethod
    def generate_token(user: dict):
        payload = {
            "id_user": user["id_user"],
            "role": user["role"],
            "email": user["email"],
            "iat": datetime.utcnow(),
            "exp": datetime.utcnow() + timedelta(hours=1)
        }

        return jwt.encode(payload, SECRET_KEY, algorithm=ALGORITHM)

    @staticmethod
    def verify_token(token: str):
        try:
            return jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM])
        except jwt.ExpiredSignatureError:
            return {"error": "Token expirado", "status": 401}
        except jwt.InvalidTokenError:
            return {"error": "Token inválido", "status": 401}
