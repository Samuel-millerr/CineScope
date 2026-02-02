from cinescope.api.core.settings import settings as api_settings
from cinescope.infra.db.engine import engine
from cinescope.infra.server.base_router import run_server
from cinescope.infra.server.settings import settings as server_settings

table_registry = api_settings.table_registry


def create_tables() -> None:
    print(f"Tabelas do banco de dados sendo criadas na URL: {server_settings.DATABASE_URL}")
    table_registry.metadata.drop_all(engine)
    table_registry.metadata.create_all(engine)

    print("Tabelas no banco de dados criadas com sucesso!")


# create_tables()
run_server(8000)
