from datetime import date
from enum import Enum

from sqlalchemy import Date, String
from sqlalchemy import Enum as SqlEnum
from sqlalchemy.orm import Mapped, class_mapper, mapped_column, relationship

from cinescope.api.core.settings import settings

table_registry = settings.table_registry


class UserRole(str, Enum):
    COMUM = "Comum"
    ADMINISTRADOR = "Administrador"


@table_registry.mapped_as_dataclass
class UserModel:
    __tablename__ = "user"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    user: Mapped[str] = mapped_column(String(255), unique=True)
    hashed_password: Mapped[str] = mapped_column(String(255))
    first_name: Mapped[str] = mapped_column(String(255))
    last_name: Mapped[str] = mapped_column(String(255))
    email: Mapped[str] = mapped_column(String(255), unique=True)
    role: Mapped[UserRole] = mapped_column(
        SqlEnum(UserRole), server_default="Comum"
    )

    @property
    def full_name(self) -> str:
        return f"{self.first_name} {self.last_name}"

    reviews: Mapped[list["ReviewModel"]] = relationship(
        back_populates="user", cascade="all, delete-orphan", init=False
    )

    requests: Mapped[list["RequestModel"]] = relationship(
        back_populates="user", cascade="all, delete-orphan", init=False
    )

    created_at: Mapped[Date] = mapped_column(
        Date, default=date.today()
    )

    def to_dict(self):
        data = {}

        for c in class_mapper(self.__class__).columns:
            value = getattr(self, c.key)

            if isinstance(value, date):
                value = value.isoformat()

            data[c.key] = value

        return data
