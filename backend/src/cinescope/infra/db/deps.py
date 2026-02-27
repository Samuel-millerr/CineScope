from contextlib import contextmanager
from typing import Generator

from sqlalchemy.orm import Session as AlchemySession

from cinescope.api.core.settings import settings as server_settings
from cinescope.infra.db.engine import SessionLocal, engine


@contextmanager
def get_session() -> Generator:
    session: AlchemySession = SessionLocal()
    try:
        yield session
    finally:
        session.close()


def create_tables() -> None:
    table_registry = server_settings.table_registry

    print(
        f"Tabelas do banco de dados sendo criadas na URL: {server_settings.DATABASE_URL}"
    )
    table_registry.metadata.drop_all(engine)
    table_registry.metadata.create_all(engine)

    print("Tabelas no banco de dados criadas com sucesso!")
