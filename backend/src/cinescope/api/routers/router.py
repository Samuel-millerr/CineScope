from http.server import HTTPServer

from cinescope.infra.server.base_handler import BaseHandler
from cinescope.infra.server.settings import settings


class Router(BaseHandler):
    def do_GET(self):
        path = self.parse_path(self.path)
        print(path)

        if path["path"] == "/api/":
            print("API funcionando normalmente")
            self.send_json_response(
                {
                    "message": "API do cinescope funcionando normalmente"
                }
            )


def run_server():
    """Função para iniciar o servidor, recebe a porta que deve ser utilizada, ou seja , o endereço do servidor, e o handler personalidado criado na classe acima. """
    httpd = HTTPServer((settings.HOST, settings.PORT), Router)
    print(f"Servidor rodando na porta {settings.BASE_SERVER}")
    httpd.serve_forever()
