from http.server import SimpleHTTPRequestHandler as HTTPHandler

from cinescope.api.services.actor_service import actor_service
from cinescope.infra.db.deps import get_session


def post_actor(router: HTTPHandler, method: str = "POST"):
    data = router.parse_json_body()
    with get_session() as db:
        actor_service.create(data=data, db=db)

def get_one_actor(router: HTTPHandler, method: str = "GET"):
    ...