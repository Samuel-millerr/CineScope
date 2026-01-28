from typing import Generator

from cinescope.db.engine import Session
from sqlalchemy.orm import Session as AlchemySession


def get_session() -> Generator:
    session: AlchemySession = Session()
    try:
        yield session
    finally:
        session.close()
