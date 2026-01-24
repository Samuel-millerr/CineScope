from datetime import date

from sqlalchemy import DECIMAL, Date, ForeignKey, Text
from sqlalchemy.orm import Mapped, mapped_column, relationship

from cinescope.core.settings import settings

table_registry = settings.table_registry


@table_registry.mapped_as_dataclass
class Review:
    __tablename__ = "review"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    id_movie: Mapped[int] = mapped_column(ForeignKey("movie.id"))
    id_user: Mapped[int] = mapped_column(ForeignKey("user.id"))
    review_text: Mapped[str] = mapped_column(Text)
    review_ratting: Mapped[bool] = mapped_column(DECIMAL(2, 1))

    movie: Mapped["Movie": object] = relationship(back_populates="reviews")

    user: Mapped["User": object] = relationship(back_populates="reviews")

    review_date: Mapped[Date] = mapped_column(
        Date, default=date.today()
    )
