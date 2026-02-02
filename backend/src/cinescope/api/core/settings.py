from sqlalchemy.orm import registry


class Settings:
    table_registry = registry()


settings = Settings()
