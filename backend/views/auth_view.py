""" Handler de autenticação, contém rotas como de login e cadastro"""
from core.handlers.base_handler import BaseHandler
from core.authentication.authentication import Authentication as auth
from core.settings import config

from database.database_service import DatabaseService as db

import datetime as datetime

status = config.status
class AuthHandler(BaseHandler):
    def login(self, handler):
        body = handler.parse_json_body()

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT user.id_user, user.user, user.password, user.user_role, user.email FROM user WHERE user.email = %s", (body["email"],))
            result = session.fetchone()
        
        if not result:
            return handler.send_json_response({"error": "User not found"}, status=status["HTTP_404_NOT_FOUND"])

        user = {
            "id_user": int(result[0]),
            "user": str(result[1]),
            "password": str(result[2]),
            "role": str(result[3]),
            "email": str(result[4])
        }
                
        if not auth.verify_password(body["password"], user["password"]):
            return handler.send_json_response({"error": "User or password wrongs"}, status=status["HTTP_401_UNAUTHORIZED"])
        
        token = auth.generate_token(user)

        handler.send_token(
            {
                "token": token,
                "user_role": user["role"],
                "user": user["user"],
                "email": user["email"]
            },
            token
        )
        
    def sing_up(self, handler):
        body = handler.parse_json_body()
        if "email" in body and isinstance(body["email"], dict):
            body = body["email"]

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT user.email FROM user WHERE user.email = %s", (body["email"],))
            result = session.fetchone()

        if not result:
            full_name = body["firstName"] + " " + body["lastName"]

            query = """
            INSERT INTO user(user, password, user_role, user_name, email, created_at)
            VALUES (%s, %s, "Comum", %s, %s, %s);
            """
            
            params = (
                body["user"],
                body["password"],
                full_name,
                body["email"],
                datetime.datetime.now(),
            )
            
            with db.session() as session:
                session.execute("USE cinescope;")
                session.execute(query, params)

            handler.send_json_response({"message": "user successfully created"}, status["HTTP_201_CREATED"])
        else:
            handler.send_json_response({"message": "user alredy exist"}, status["HTTP_409_CONFLICT"])
        
        return {"message": "user successfully created"}
