from sqlalchemy import String
from sqlalchemy.orm import Mapped, mapped_column, relationship

from cinescope.infra.server.settings import settings

table_registry = settings.table_registry


@table_registry.mapped_as_dataclass
class Genre:
    __tablename__ = "genre"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    genre: Mapped[str] = mapped_column(String(255))

    movies: Mapped[list["MovieGenre"]] = relationship(
        back_populates="genre", cascade="all, delete-orphan", init=False
    )
