from cinescope.api.routers.actor_router import (
    delete_actor,
    get_actors,
    get_one_actor,
    patch_actor,
    post_actor,
)
from cinescope.api.routers.director_router import (
    delete_director,
    get_directors,
    get_one_director,
    patch_director,
    post_director,
)
from cinescope.api.routers.read_root_router import read_root

urls = {
    "/api/": [read_root],
    "/api/actor/": [post_actor, get_actors],
    "/api/actor/<pk>": [get_one_actor, patch_actor, delete_actor],
    "/api/director/": [post_director, get_directors],
    "/api/director/<pk>": [get_one_director, patch_director, delete_director]
}
