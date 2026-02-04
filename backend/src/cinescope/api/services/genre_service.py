from sqlalchemy.future import select
from sqlalchemy.orm import Session

from cinescope.api.models.genre import GenreModel
from cinescope.api.services._base_service import BaseService


class GenreService(BaseService[GenreModel]):
    def get_genre_by_name(self, name: str, db: Session):
        query = select(self.model).filter(self.model.genre == name)
        result = db.execute(query)
        genre = result.scalar_one_or_none()
        return genre


genre_service = GenreService(GenreModel)
