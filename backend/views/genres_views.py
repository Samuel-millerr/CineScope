""" 
Handler para permitir a busca simples de gêneros.
"""

from core.handlers.base_handler import BaseHandler
from core.settings import config

from database.database_service import DatabaseService as db

status = config.status

class GenreHandler(BaseHandler):
    def get_genres(self, handler):
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT genre.id_genre, genre.genre FROM genre;")
            result = session.fetchall()

        genres_json = []
        for res in result:
            genres_json.append({
                "id_genre": int(res[0]),
                "genre": str(res[1])
            })

        handler.send_json_response(genres_json, status["HTTP_200_OK"])