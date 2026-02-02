from sqlalchemy.future import select
from sqlalchemy.orm import Session

from cinescope.api.models.director import DirectorModel
from cinescope.api.services._base_service import BaseService


class DirectorService(BaseService[DirectorModel]):
    def get_director_by_name(self, name: str, db: Session):
        query = select(self.model).filter(self.model.director_name == name)
        result = db.execute(query)
        director = result.scalar_one_or_none()
        return director


director_service = DirectorService(DirectorModel)
