from cinescope.api.routers.router import run_server
from cinescope.infra.db.engine import engine
from cinescope.infra.server.settings import settings

table_registry = settings.table_registry


def create_tables() -> None:
    print(f"Tabelas do banco de dados sendo criadas na URL: {settings.DATABASE_URL}")
    table_registry.metadata.drop_all(engine)
    table_registry.metadata.create_all(engine)

    print("Tabelas no banco de dados criadas com sucesso!")


create_tables()
run_server()
