from cinescope.api.routers.actor_router import actor_router
from cinescope.api.routers.director_router import director_router
from cinescope.api.routers.genre_router import genre_router
from cinescope.api.routers.user_router import user_router
from cinescope.api.routers.read_root_router import read_root

urls = {
    "/api/": [read_root],
    "/api/actors": [actor_router.post_actor, actor_router.get_actors],
    "/api/actors/<pk>": [actor_router.get_one_actor, actor_router.patch_actor, actor_router.delete_actor],
    "/api/directors": [director_router.post_director, director_router.get_directors],
    "/api/directors/<pk>": [director_router.get_one_director, director_router.patch_director, director_router.delete_director],
    "/api/genres": [genre_router.post_genre, genre_router.get_genres],
    "/api/genres/<pk>": [genre_router.get_one_genre, genre_router.patch_genre, genre_router.delete_genre],
    "/api/users": [user_router.post_user, user_router.get_users],
    "/api/users/<pk>": [user_router.get_one_user, user_router.delete_users]
}
