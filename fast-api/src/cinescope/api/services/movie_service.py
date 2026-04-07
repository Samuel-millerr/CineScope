from sqlalchemy.future import select
from sqlalchemy.orm import Session

from cinescope.api.models.movie import MovieModel
from cinescope.api.services._base_service import BaseService


class MovieService(BaseService[MovieModel]):
    def get_service_by_title(self, title: str, db: Session):
        query = select(self.model).filter(self.model.movie_title == title)
        result = db.execute(query)
        movie = result.scalar_one_or_none()
        return movie


movie_service = MovieService(MovieModel)
