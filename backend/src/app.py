import all_models
from cinescope.core.settings import settings
from cinescope.db.engine import engine

table_registry = settings.table_registry


def create_tables() -> None:
    print(f"Tabelas do banco de dados sendo criadas na URL: {settings.DATABASE_URL}")
    table_registry.metadata.drop_all(engine)
    table_registry.metadata.create_all(engine)

    print("Tabelas no banco de dados criadas com sucesso!")


create_tables()
