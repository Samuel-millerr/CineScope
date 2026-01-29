import inspect
import functools
from http.server import HTTPServer

from cinescope.api.core.urls import urls
from cinescope.infra.server.base_handler import BaseHandler
from cinescope.infra.server.settings import settings


def router(func):
    @functools.wraps(func)
    def wrapper(*args, **kwars):
        router = args[0]
        router_path = router.parse_path(router.path)
        print(router_path)
        if router_path["path"] in urls:
            router_func = urls[router_path["path"]]
            sig = inspect.signature(router_func[0])
            params = sig.parameters
            print(params["method"].default)
            # router_func(router)
            # print(router_func.method)
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
    print(f"Servidor rodando na porta {settings.BASE_SERVER}")
    httpd.serve_forever()
