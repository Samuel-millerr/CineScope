from sqlalchemy import String
from sqlalchemy.orm import Mapped, mapped_column, relationship

from cinescope.infra.server.settings import settings

table_registry = settings.table_registry


@table_registry.mapped_as_dataclass
class Director:
    __tablename__ = "director"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    director_name: Mapped[str] = mapped_column(String(255), unique=True)

    movies: Mapped[list["MovieDirector": object]] = relationship(
        back_populates="director", cascade="all, delete-orphan"
    )
