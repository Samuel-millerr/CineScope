import threading
import time

import pytest

from src.cinescope.infra.server.base_router import run_server


@pytest.fixture(scope="session")
def server():
    thread = threading.Thread(
        target=run_server,
        kwargs={"port": 8001},
        daemon=True
    )
    thread.start()

    time.sleep(1)
    yield

# @pytest.fixture()
# def session():
#     table_registry = api_settings.table_registry
#     print(server_settings.DATABASE_TEST_URL)
#     engine = create_engine(server_settings.DATABASE_TEST_URL)
#     table_registry.metadata.create_all(engine)

#     with Session(engine) as session:
#         yield session

#     table_registry.metadata.drop_all(engine)
