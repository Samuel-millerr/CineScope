from cinescope.api.routers.actor_router import (
    delete_actor,
    get_actors,
    get_one_actor,
    patch_actor,
    post_actor,
)
from cinescope.api.routers.read_root_router import read_root

urls = {
    "/api/": [read_root],
    "/api/actor/": [post_actor, get_actors],
    "/api/actor/<pk>": [get_one_actor, patch_actor, delete_actor]
}
