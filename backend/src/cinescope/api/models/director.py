from sqlalchemy import String
from sqlalchemy.orm import Mapped, class_mapper, mapped_column, relationship

from cinescope.api.core.settings import settings

table_registry = settings.table_registry


@table_registry.mapped_as_dataclass
class DirectorModel:
    __tablename__ = "director"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    director_name: Mapped[str] = mapped_column(String(255), unique=True)

    movies: Mapped[list["MovieDirector"]] = relationship(
        back_populates="director", cascade="all, delete-orphan", init=False
    )

    def to_dict(self):
        return {c.key: getattr(self, c.key) for c in class_mapper(self.__class__).columns}
