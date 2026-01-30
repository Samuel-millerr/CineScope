from contextlib import contextmanager
from typing import Generator

from sqlalchemy.orm import Session as AlchemySession

from cinescope.infra.db.engine import SessionLocal


@contextmanager
def get_session() -> Generator:
    session: AlchemySession = SessionLocal()
    try:
        yield session
    finally:
        session.close()
