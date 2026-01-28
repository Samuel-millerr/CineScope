from cinescope.infra.server.base_handler import BaseHandler

def read_root(router = BaseHandler):
    router.send_json_response({
        "message": "API running sucessfully"
    })