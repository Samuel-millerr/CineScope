from typing import Generator

from sqlalchemy.orm import Session as AlchemySession

from cinescope.db.engine import Session


def get_session() -> Generator:
    session: AlchemySession = Session()
    try:
        yield session
    finally:
        session.close()
