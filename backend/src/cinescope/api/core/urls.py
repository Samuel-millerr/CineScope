from cinescope.api.routers.actor_router import post_actor, get_one_actor, get_actors
from cinescope.api.routers.read_root_router import read_root

urls = {
    "/api/": [read_root],
    "/api/actor/": [post_actor, get_actors],
    "/api/actor/<pk>": [get_one_actor]
}
