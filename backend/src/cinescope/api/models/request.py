from datetime import date
from enum import Enum

from sqlalchemy import JSON, Date, ForeignKey
from sqlalchemy import Enum as SqlEnum
from sqlalchemy.orm import Mapped, mapped_column, relationship

from cinescope.api.core.settings import settings

table_registry = settings.table_registry


class RequestType(str, Enum):
    ADICAO = "Adição"
    EDICAO = "Edição"


class RequestStatus(str, Enum):
    APROVADO = "Aprovado"
    PENDENTE = "Pendente"
    REPROVADO = "Reprovado"


@table_registry.mapped_as_dataclass
class Request:
    __tablename__ = "request"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    id_user: Mapped[int] = mapped_column(ForeignKey("user.id"))
    id_movie: Mapped[int] = mapped_column(
        ForeignKey("movie.id"), nullable=True
    )
    request_type: Mapped[RequestType] = mapped_column(SqlEnum(RequestType))
    request_status: Mapped[RequestStatus] = mapped_column(
        SqlEnum(RequestStatus), server_default="PENDENTE"
    )
    request_body: Mapped[JSON] = mapped_column(JSON)

    user: Mapped["UserModel"] = relationship(back_populates="requests", init=False)

    movie: Mapped["MovieModel"] = relationship(back_populates="requests", init=False)

    request_date: Mapped[Date] = mapped_column(
        Date, default=date.today
    )
