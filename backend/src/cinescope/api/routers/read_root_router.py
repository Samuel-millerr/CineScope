from http.server import SimpleHTTPRequestHandler as HTTPHandler


def read_root(router: HTTPHandler, method: str = "GET"):
    router.send_json_response({
        "message": "API running sucessfully"
    })
