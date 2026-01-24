import json
from http import HTTPStatus
from http.server import HTTPServer, SimpleHTTPRequestHandler
from urllib.parse import unquote, urlsplit

from cinescope.core.settings import settings


class BaseHandler(SimpleHTTPRequestHandler):
    def send_json_response(self, data: dict, status: int = HTTPStatus.OK):
        self.send_response(status)
        self.send_header("Content-Type", "application/json")
        self.end_headers()
        self.wfile.write(json.dumps(data).encode("utf-8"))

    def parse_json_body(self):
        lenght = int(self.headers.get("Content-Length", 0))
        body = self.rfile.read(lenght).decode("utf-8")

        try:
            return json.loads(body)
        except:
            return None

    def parse_path(self, url: str):
        split = urlsplit(url)
        parts = split.path.split("/")

        result = {"path": "/" + "/".join(parts)}
        result["id"] = int(parts[-1]) if parts and parts[-1].isdigit() else None
        result["query"] = unquote(split.query.strip().lower())
        return result

    def do_OPTIONS(self):
        self.send_response(200)
        self.send_header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "Content-Type, Authorization")
        self.end_headers()


def run_server():
    """Função para iniciar o servidor, recebe a porta que deve ser utilizada, ou seja , o endereço do servidor, e o handler personalidado criado na classe acima. """
    httpd = HTTPServer((settings.HOST, settings.PORT), BaseHandler)
    print(f"Servidor rodando na porta {settings.BASE_SERVER}")
    httpd.serve_forever()
