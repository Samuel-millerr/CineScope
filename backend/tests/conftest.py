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
