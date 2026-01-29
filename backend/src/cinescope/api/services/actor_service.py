from cinescope.api.models.actor import ActorModel
from cinescope.api.services.base_service import BaseService


class ActorService(BaseService[ActorModel]):
    ...


actor_service = ActorService(ActorModel)
