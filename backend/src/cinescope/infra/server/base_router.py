import functools
import inspect
from http.server import HTTPServer

from cinescope.api.core.urls import urls
from cinescope.infra.server.base_handler import BaseHandler
from cinescope.infra.server.settings import settings


def router(func):
    @functools.wraps(func)
    def wrapper(*args, **kwars):
        server: BaseHandler = args[0]
        server_path = server.server_path
        server_method = server.server_method

        if server_path["id"]:
            server_path["path"] = server_path["path"].replace(f"{server_path["id"]}", "<pk>")

        if server_path["path"] in urls:
            router_funcs = urls[server_path["path"]]
            for router_func in router_funcs:
                sig = inspect.signature(router_func)
                params = sig.parameters
                router_method = params["method"].default
                if server_method == router_method:
                    router_func(server)
    return wrapper


class Router(BaseHandler):
    @router
    def do_GET(self):
        ...

    @router
    def do_POST(self):
        ...

    @router
    def do_PATCH(self):
        ...

    @router
    def do_DELETE(self):
        ...


def run_server():
    """Função para iniciar o servidor, recebe a porta que deve ser utilizada, ou seja , o endereço do servidor, e o handler personalidado criado na classe acima. """
    httpd = HTTPServer((settings.HOST, settings.PORT), Router)
    print(f"Servidor rodando no caminho {settings.BASE_SERVER}")
    httpd.serve_forever()
