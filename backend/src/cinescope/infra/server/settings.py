import os

from dotenv import load_dotenv
from sqlalchemy.orm import registry

load_dotenv()


class Settings:
    HOST = "0.0.0.0"
    PORT = 8000
    BASE_SERVER = (f"http://{HOST}:{PORT}/api/")
    DATABASE_URL: str = os.getenv("DATABASE_URL", "sqlite:///:memory:")

    table_registry = registry()


settings = Settings()
