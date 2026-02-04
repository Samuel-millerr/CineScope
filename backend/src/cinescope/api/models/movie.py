from datetime import time

from sqlalchemy import ForeignKey, String, Text, Time
from sqlalchemy.orm import Mapped, mapped_column, relationship

from cinescope.api.core.settings import settings

table_registry = settings.table_registry


@table_registry.mapped_as_dataclass
class Movie:
    __tablename__ = "movie"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    movie_title: Mapped[str] = mapped_column(String(255), unique=True)
    duration_time: Mapped[time] = mapped_column(Time)
    publication_year: Mapped[int]
    movie_synopsis: Mapped[str] = mapped_column(Text)
    movie_poster: Mapped[str] = mapped_column(Text)

    directors: Mapped[list["MovieDirector"]] = relationship(
        back_populates="movie", cascade="all, delete-orphan", init=False
    )

    actors: Mapped[list["MovieActor"]] = relationship(
        back_populates="movie", cascade="all, delete-orphan", init=False
    )

    genres: Mapped[list["MovieGenre"]] = relationship(
        back_populates="movie", cascade="all, delete-orphan", init=False
    )

    reviews: Mapped[list["Review"]] = relationship(
        back_populates="movie", cascade="all, delete-orphan", init=False
    )

    requests: Mapped[list["Request"]] = relationship(
        back_populates="movie", cascade="all, delete-orphan", init=False
    )


@table_registry.mapped_as_dataclass
class MovieDirector:
    __tablename__ = "movie_director"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    id_movie: Mapped[int] = mapped_column(ForeignKey("movie.id"))
    id_director: Mapped[int] = mapped_column(ForeignKey("director.id"))

    movie: Mapped["Movie"] = relationship(back_populates="directors", init=False)

    director: Mapped["DirectorModel"] = relationship(back_populates="movies", init=False)


@table_registry.mapped_as_dataclass
class MovieActor:
    __tablename__ = "movie_actor"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    id_movie: Mapped[int] = mapped_column(ForeignKey("movie.id"))
    id_actor: Mapped[int] = mapped_column(ForeignKey("actor.id"))

    movie: Mapped["Movie"] = relationship(back_populates="actors", init=False)

    actor: Mapped["ActorModel"] = relationship(back_populates="movies", init=False)


@table_registry.mapped_as_dataclass
class MovieGenre:
    __tablename__ = "movie_genre"

    id: Mapped[int] = mapped_column(init=False, primary_key=True)
    id_movie: Mapped[int] = mapped_column(ForeignKey("movie.id"))
    id_genre: Mapped[int] = mapped_column(ForeignKey("genre.id"))

    movie: Mapped["Movie"] = relationship(back_populates="genres", init=False)

    genre: Mapped["GenreModel"] = relationship(back_populates="movies", init=False)
