import json
from http import HTTPStatus
from http.server import SimpleHTTPRequestHandler
from urllib.parse import unquote, urlsplit


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

        result = {"path": "/".join(parts)}
        result["id"] = int(parts[-1]) if parts and parts[-1].isdigit() else None
        result["query"] = unquote(split.query.strip().lower())
        return result

    def do_OPTIONS(self):
        self.send_response(200)
        self.send_header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "Content-Type, Authorization")
        self.end_headers()

    @property
    def server_path(self):
        server_path = self.parse_path(self.path)
        return server_path
    
    @property
    def server_method(self):
        return self.command