""" 
Raiz do handler do servidor, tem como objetivo passar funções específicas que poderam ser usadas em todos os endpoints, 
como envio de resposta e conversão do body da requisição.

É aqui onde o SimplesHTTPHandler é chamado e tem sua criação principal, todos os outros metódos e questões do servidor 
vão se basear no presente nesse arquivo.
"""

from http.server import SimpleHTTPRequestHandler
from core.authentication.authentication import Authentication

import json as json
import os as os
from urllib.parse import urlsplit, unquote

class BaseHandler(SimpleHTTPRequestHandler):
    def send_json_response(self, data, status=200):
        self.send_response(status)
        self.send_header("Content-Type", "application/json")
        self.end_headers()
        self.wfile.write(json.dumps(data).encode("utf-8"))

    def send_token(self, data, token, status=200):
        self.send_response(status)
        self.send_header("Content-Type", "application/json")
        self.end_headers()
        self.wfile.write(json.dumps(data).encode("utf-8"))

    def parse_json_body(self):
        length = int(self.headers.get("Content-Length", 0))
        body = self.rfile.read(length).decode("utf-8")

        try:
            return json.loads(body)
        except:
            return None

    def get_token(self):
        auth = self.headers.get("Authorization")
        if auth and auth.startswith("Bearer "):
            return auth.split(" ")[1]
        return None

    def parse_path(self, url):
        split = urlsplit(url)
        parts = split.path.strip("/").split("/")

        result = {"path": "/" + "/".join(parts)}
        result["id"] = int(parts[-1]) if parts and parts[-1].isdigit() else None
        result["query"] = unquote(split.query.strip().lower())
        return result

    def end_headers(self):
        self.send_header("Access-Control-Allow-Origin", "*")
        super().end_headers()

    def do_OPTIONS(self):
        self.send_response(200)
        self.send_header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "Content-Type, Authorization")
        self.end_headers()
    
    def authenticate(self):
        token = self.get_token()
        if not token:
            self.token_data = None
            return

        decoded = Authentication.verify_token(token)

        if "error" in decoded:
            self.token_data = None
        else:
            self.token_data = decoded
    
    def list_api_directory(self):
        """ Função utilizada para renderizar a página da API """
        path = os.path.join(os.getcwd(), r"core\template.html")
        try:
            with open(path, "r", encoding="utf-8") as arquivo:
                content = arquivo.read()
                self.send_response(200)
                self.send_header("content-type", "text/html; charset=utf-8")
                self.end_headers()
                self.wfile.write(content.encode("utf-8"))
        except FileNotFoundError:
            self.send_error(404, f"Arquivo não {path} encontrado")