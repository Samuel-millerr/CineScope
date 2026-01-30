from sqlalchemy.future import select
from sqlalchemy.orm import Session

from cinescope.api.models.actor import ActorModel
from cinescope.api.services.base_service import BaseService


class ActorService(BaseService[ActorModel]):
    def get_actor_by_name(self, name: str, db: Session):
        query = select(self.model).filter(self.model.actor_name == name)
        result = db.execute(query)
        actor = result.scalar_one_or_none()
        return actor

actor_service = ActorService(ActorModel)
