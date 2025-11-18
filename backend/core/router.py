"""
O router é a identificação de qual path está sendo definido pelo cliente, com a identificação de qual caminho é chamado a lógica do
endpoint em si.
"""
from views.auth_view import AuthHandler
from views.movies_view import MovieHandler
from views.actors_view import ActorHandler
from views.genres_views import GenreHandler
from views.director_view import DirectorHandler
from views.request_view import RequestHandler
from views.reviews_view import ReviewHandler

class Router:

    def handler_post(self, handler):
        path = handler.parse_path(handler.path)

        if path["path"] == "/api/movies":
            MovieHandler.post_movie(self, handler)
        elif path["path"] == "/api/auth/login":
            AuthHandler.login(self, handler)
        elif path["path"] == "/api/auth/sing_up":
            AuthHandler.sing_up(self, handler)


    def handler_get(self, handler):
        path = handler.parse_path(handler.path)
        
        if path["path"] == "/api":
            handler.list_api_directory()
        elif path["path"] == "/api/movies" and not path["query"]:
            MovieHandler.get_movies(self, handler)
        elif path["path"].startswith("/api/movies") and path["id"] and not path["query"]:
            MovieHandler.get_movie(self, handler, path["id"])
        elif path["path"].startswith("/api/movies") and path["query"]:
            MovieHandler.filter_movies(self, handler, path["query"])
        elif path["path"] == "/api/actors" and not path["query"]:
            ActorHandler.get_actors(self, handler)
        elif path["path"].startswith("/api/actors") and path["query"]:
            ActorHandler.filter_actors(self, handler, path["query"])
        elif path["path"] == "/api/genres":
            GenreHandler.get_genres(self, handler)
        elif path["path"] == "/api/directors":
            DirectorHandler.get_directors(self, handler)
        elif path["path"] == "/api/requests":
            RequestHandler.get_requests(self, handler)
        elif path["path"].startswith("/api/reviews") and path["id"]:
            ReviewHandler.get_reviews_by_movie(self, handler, path["id"])
        elif path["path"] == "/api/users":
            AuthHandler.get_all_users(self, handler)
        elif path["path"].startswith("/api/users"):
            AuthHandler.get_info_user(self, handler, path["path"])
        elif path["path"].startswith("/api/requests_user"):
            RequestHandler.get_requests_by_user(self, handler, path["path"])
        elif path["path"].startswith("/api/reviews_user"):
            ReviewHandler.get_reviews_by_user(self, handler, path["path"])
            
    def handler_put(self, handler):
        path = handler.parse_path(handler.path)
        
        if path["path"].startswith("/api/movies") and path["id"]:
            MovieHandler.put_movie(self, handler, path["id"])
        elif path["path"].startswith("/api/requests/allow") and path["id"]:
            RequestHandler.allow_request(self, handler, path["id"])
        elif path["path"].startswith("/api/requests/deny") and path["id"]:
            RequestHandler.deny_request(self, handler, path["id"])

    def handler_delete(self, handler):
        path = handler.parse_path(handler.path)
        
        if path["path"].startswith("/api/movies") and path["id"]:
            MovieHandler.delete_movie(self, handler, path["id"])
