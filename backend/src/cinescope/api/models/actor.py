from sqlalchemy import String
from sqlalchemy.orm import Mapped, class_mapper, mapped_column, relationship

from cinescope.infra.server.settings import settings

table_registry = settings.table_registry


@table_registry.mapped_as_dataclass
class ActorModel:
    __tablename__ = "actor"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    actor_name: Mapped[str] = mapped_column(String(255), unique=True)
    actor_photo: Mapped[str] = mapped_column(String(512))

    movies: Mapped[list["MovieActor"]] = relationship(
        back_populates="actor", cascade="all, delete-orphan", init=False
    )

    def to_dict(self):
        return {c.key: getattr(self, c.key) for c in class_mapper(self.__class__).columns}
