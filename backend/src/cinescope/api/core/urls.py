from cinescope.api.routers.actor_router import post_actor, get_one_actor
from cinescope.api.routers.read_root_router import read_root

urls = {
    "/api/": [read_root],
    "/actor/": [post_actor],
    "/actor/<pk>": [get_one_actor]
}
