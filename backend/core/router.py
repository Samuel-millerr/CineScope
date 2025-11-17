"""
O router é a identificação de qual path está sendo definido pelo cliente, com a identificação de qual caminho é chamado a lógica do
endpoint em si.
"""
from views.auth_view import AuthHandler
from views.movies_view import MovieHandler
from views.actors_view import ActorHandler
from views.genres_views import GenreHandler

class Router:
    def handler_post(self, handler):
        server_path = handler.path
        parse_path = handler.parse_path(server_path)

        if parse_path["path"] == "/api/movies":
            MovieHandler.post_movie(self, handler)
        elif parse_path["path"] == "/api/auth/login":           
            AuthHandler.login(self, handler)
        elif parse_path["path"] == "/api/auth/sing_up":
            AuthHandler.sing_up(self, handler)

    def handler_get(self, handler):
        server_path = handler.path
        parse_path = handler.parse_path(server_path)
        
        if parse_path["path"] == ("/api"):
            handler.list_api_directory()
        elif parse_path["path"] == ("/api/movies") and not parse_path["query"]:
            MovieHandler.get_movies(self, handler)
        elif parse_path["path"].startswith("/api/movies") and parse_path["id"] and not parse_path["query"]:
            MovieHandler.get_movie(self, handler, parse_path["id"])
        elif parse_path["path"].startswith("/api/movies") and parse_path["query"]:
            MovieHandler.filter_movies(self, handler, parse_path["query"])
        elif parse_path["path"] == ("/api/actors") and not parse_path["query"]:
            ActorHandler.get_actors(self, handler)
        elif parse_path["path"].startswith("/api/actors") and parse_path["query"]:
            ActorHandler.filter_actors(self, handler, parse_path["query"])
        elif parse_path["path"] == ("/api/genres") and not parse_path["query"]:
            GenreHandler.get_genres(self, handler)

    def handler_put(self, handler):
        server_path = handler.path 
        parse_path = handler.parse_path(server_path)

        if parse_path["path"].startswith("/api/movies") and parse_path["id"]:
            MovieHandler.put_movie(self, handler, parse_path["id"]) 

    def handler_delete(self, handler):
        server_path = handler.path 
        parse_path = handler.parse_path(server_path)
        token = handler.parse_headers()

        if parse_path["path"].startswith("/api/movies") and parse_path["id"]:
            MovieHandler.delete_movie(self, handler, parse_path["id"], token)
    
