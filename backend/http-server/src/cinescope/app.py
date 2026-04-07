from cinescope.api.core.settings import settings as api_settings
from cinescope.infra.db.deps import create_tables
from cinescope.infra.server.base_router import run_server

table_registry = api_settings.table_registry


create_tables()
run_server(8000)
