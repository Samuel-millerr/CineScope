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
from cinescope.api.routers.genre_router import (
    delete_genre,
    get_genres,
    get_one_genre,
    patch_genre,
    post_genre
)
from cinescope.api.routers.read_root_router import read_root

urls = {
    "/api/": [read_root],
    "/api/actor/": [post_actor, get_actors],
    "/api/actor/<pk>": [get_one_actor, patch_actor, delete_actor],
    "/api/director/": [post_director, get_directors],
    "/api/director/<pk>": [get_one_director, patch_director, delete_director],
    "/api/genre/": [post_genre, get_genres],
    "/api/genre/<pk>": [get_one_genre, patch_genre, delete_genre]
}
