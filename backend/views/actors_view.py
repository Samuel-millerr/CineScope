"""
Handler para permitir a busca de todos os atores e outros tipos de requisção 
"""

from core.handlers.base_handler import BaseHandler
from core.settings import config
from filters.actor_filters import ActorFilters

from database.database_service import DatabaseService as db

status = config.status

class ActorHandler(BaseHandler):
    def get_actors(self, handler):
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute("SELECT * FROM actor;")
            result = session.fetchall()

        actors_json = []
        for res in result:
            movie = {
                "id_actor": int(res[0]),
                "actor_name": str(res[1]),
                "actor_photo": str(res[2]),
            }

            actors_json.append(movie)

        handler.send_json_response(actors_json, status["HTTP_200_OK"])
        
    def filter_actors(self, handler, query):
        params = {}
        if query:
            for param in query.split("&"):
                if "=" in param:
                    key, value = param.split("=", 1)
                    params[key] = value

        filtro = params.get("filter")
        if filtro:
            if filtro.split("?")[0] == "actor_by_movie":
                id_movie = filtro.split("?")[1].split("=")[1]
                actors_json = ActorFilters.actor_by_movie(id_movie)
                
        handler.send_json_response(actors_json, status["HTTP_200_OK"])