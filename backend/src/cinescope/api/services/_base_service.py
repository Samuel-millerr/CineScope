from typing import Generic, TypeVar

from sqlalchemy.future import select
from sqlalchemy.orm import Session

ModelType = TypeVar("ModelType")


class BaseService(Generic[ModelType]):
    def __init__(self, model: ModelType):
        self.model = model

    def get_all(self, db: Session):
        query = select(self.model)
        result = db.execute(query)
        model = result.scalars()

        model_list = []
        for md in model:
            model_list.append(md.to_dict())

        return model_list

    def get_one(self, pk: int, db: Session):
        model = self.get_one_as_model(pk, db)

        if model:
            return model.to_dict()
        return model

    def get_one_as_model(self, pk: int, db: Session):
        query = select(self.model).filter(self.model.id == pk)
        result = db.execute(query)
        model = result.scalar_one_or_none()

        return model

    def create(self, data, db: Session):
        model = self.model(**dict(data))
        db.add(model)
        db.commit()
        db.refresh(model)

        return model

    def patch(self, pk: int, data: dict, db: Session):
        model = self.get_one_as_model(pk, db)

        if not model:
            return

        for key, value in data.items():
            setattr(model, key, value)

        db.commit()
        db.refresh(model)

        return model.to_dict()

    def delete(self, pk: int, db: Session):
        model = self.get_one_as_model(pk, db)

        if not model:
            return

        db.delete(model)
        db.commit()

        return True
