import functools

from http.server import HTTPServer

from cinescope.infra.server.settings import settings
from cinescope.infra.server.base_handler import BaseHandler

def router(path: str):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kwargs):
            router = args[0]
            router_path = router.parse_path(router.path)
            print(router_path)
            print(path)
            if router_path["path"] == path:
               return func(router)
            result = func(*args, **kwargs)
            return result
        return wrapper
    return decorator

@router("/api/")
def read_root(router: BaseHandler):
    router.send_json_response({
        "message": "API running sucessfully"
    })

class Router(BaseHandler):
    def do_GET(self):
        print("GET")
        read_root(self)
    
    def do_POST(self):
        print("POST")

    def do_PATCH(self):
        print("PATCH")

    def do_DELETE(self):
        print("DELETE")

def run_server():
    """Função para iniciar o servidor, recebe a porta que deve ser utilizada, ou seja , o endereço do servidor, e o handler personalidado criado na classe acima. """
    httpd = HTTPServer((settings.HOST, settings.PORT), Router)
    print(f"Servidor rodando na porta {settings.BASE_SERVER}")
    httpd.serve_forever()