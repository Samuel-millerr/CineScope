import os

from dotenv import load_dotenv

from cinescope.infra.server.base_handler import BaseHandler

load_dotenv()


class Settings:
    HOST = "localhost"
    PORT = 8000
    BASE_SERVER = (f"http://{HOST}:{PORT}/api/")

    DATABASE_URL: str = os.getenv("DATABASE_URL", "sqlite:///cinescope:")
    # DATABASE_TEST_URL: str = os.getenv("DATABASE_TEST_URL", "sqlite:///:memory:")

    server = BaseHandler


settings = Settings()
