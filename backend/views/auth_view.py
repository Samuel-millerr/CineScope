""" Handler de autenticação, contém rotas como de login e cadastro"""
from core.handlers.base_handler import BaseHandler
from core.authentication.authentication import Authentication
from core.settings import config
from database.database_service import DatabaseService as db
import datetime
import locale
locale.setlocale(locale.LC_TIME, 'pt_BR.UTF-8')

status = config.status

class AuthHandler(BaseHandler):
    def login(self, handler):
        body = handler.parse_json_body()

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("""
                SELECT id_user, user, password, user_role, email 
                FROM user WHERE email = %s
            """, (body["email"],))
            result = session.fetchone()

        if not result:
            return handler.send_json_response({"error": "User not found"}, status["HTTP_404_NOT_FOUND"])

        user = {
            "id_user": result[0],
            "user": result[1],
            "password": result[2],
            "role": result[3],
            "email": result[4]
        }

        if body["password"] != user["password"]:
            return handler.send_json_response({"error": "Invalid credentials"}, status["HTTP_401_UNAUTHORIZED"])

        token = Authentication.generate_token(user)

        return handler.send_json_response({
            "token": token,
            "user_role": user["role"],
            "user": user["user"],
            "email": user["email"]
        }, 200)

    def sing_up(self, handler):
        body = handler.parse_json_body()

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT email FROM user WHERE email = %s", (body["email"],))
            result = session.fetchone()
            print(result)


        full_name = f"{body['firstName']} {body['lastName']}"

        query = """
            INSERT INTO user(user, password, user_role, user_name, email, created_at)
            VALUES (%s, %s, "Comum", %s, %s, %s)
        """

        params = (
            body["user"],
            body["password"],
            full_name,
            body["email"],
            datetime.datetime.now()
        )

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, params)

        return handler.send_json_response({"message": "User created"}, status["HTTP_201_CREATED"])
    
    @staticmethod 
    def get_user_id(user):
        query = """
            SELECT user.id_user
            FROM user
            WHERE user.user = %s;
        """

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (user.split("/")[3],))
            id_user = {"id_user": session.fetchone()[0]}
        
        return id_user
        

    def get_info_user(self, handler, user: int):
        query = """
            SELECT user.user_name, user.email, user. created_at
            FROM user
            WHERE user.user = %s;
        """

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (user.split("/")[3],))
            result = session.fetchone()

        user_json = {
            "user_name": result[0],
            "email": result[1],
            "date_created": result[2].strftime("%d de %B de %Y")
        }

        return handler.send_json_response(user_json, status["HTTP_200_OK"])
