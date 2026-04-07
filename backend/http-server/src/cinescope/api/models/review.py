from datetime import date
from decimal import Decimal

from sqlalchemy import DECIMAL, Date, ForeignKey, Text
from sqlalchemy.orm import Mapped, class_mapper, mapped_column, relationship

from cinescope.api.core.settings import settings

table_registry = settings.table_registry


@table_registry.mapped_as_dataclass
class ReviewModel:
    __tablename__ = "review"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    id_movie: Mapped[int] = mapped_column(ForeignKey("movie.id"))
    id_user: Mapped[int] = mapped_column(ForeignKey("user.id"))
    review_text: Mapped[str] = mapped_column(Text)
    review_ratting: Mapped[bool] = mapped_column(DECIMAL(2, 1))

    movie: Mapped["MovieModel"] = relationship(back_populates="reviews", init=False)

    user: Mapped["UserModel"] = relationship(back_populates="reviews", init=False)

    review_date: Mapped[Date] = mapped_column(Date, default=date.today())

    def to_dict(self):
        data = {}

        for column in class_mapper(self.__class__).columns:
            value = getattr(self, column.key)

            # Converte date
            if isinstance(value, date):
                value = value.isoformat()
            elif isinstance(value, Decimal):
                value = float(value)

            data[column.key] = value

        return data
