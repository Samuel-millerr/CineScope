from sqlalchemy import Engine, create_engine
from sqlalchemy.orm import Session as AlchemySession
from sqlalchemy.orm import sessionmaker

from cinescope.infra.server.settings import settings

engine: Engine = create_engine(settings.DATABASE_URL)

Session: AlchemySession = sessionmaker(
    autocommit=False,
    autoflush=False,
    expire_on_commit=False,
    class_=AlchemySession,
    bind=Engine
)
