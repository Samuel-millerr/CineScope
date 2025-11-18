"""
Handler para permitir a busca simples de diretores.
"""

from core.handlers.base_handler import BaseHandler
from core.settings import config

from database.database_service import DatabaseService as db

status = config.status

class DirectorHandler(BaseHandler):
    def get_directors(self, handler):
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT id_director, director_name FROM director;")
            result = session.fetchall()

        directors_json = []
        for res in result:
            directors_json.append({
                "id_director": int(res[0]),
                "director_name": str(res[1])
            })

        handler.send_json_response(directors_json, status["HTTP_200_OK"])
