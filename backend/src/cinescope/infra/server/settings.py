import os

from dotenv import load_dotenv

load_dotenv()


class Settings:
    HOST = "127.0.0.1"
    PORT = 8000
    BASE_SERVER = (f"http://{HOST}:{PORT}/api/")
    DATABASE_URL: str = os.getenv("DATABASE_URL", "sqlite:///cinescope:")
    # DATABASE_TEST_URL: str = os.getenv("DATABASE_TEST_URL", "sqlite:///:memory:")


settings = Settings()
