from sqlalchemy.future import select
from sqlalchemy.orm import Session

from cinescope.api.models.user import UserModel
from cinescope.api.services._base_service import BaseService


class UserService(BaseService[UserModel]):
    def get_user_by_atribute(self, target_atribute, atribute_value, db: Session):
        if type(target_atribute) == str:
            target_atribute = getattr(self.model, target_atribute)

        query = select(self.model).filter(target_atribute == atribute_value)
        result = db.execute(query)
        user = result.scalar_one_or_none()
        return user

    def create(self, data, db: Session):
        exists_username = self.get_user_by_atribute(self.model.user, data["user"], db)
        if exists_username:
            return False, {"message": "User with username alredy exists"}

        exists_email = self.get_user_by_atribute(self.model.email, data["email"], db)
        if exists_email:
            return False, {"message": "User with email alredy exists"}

        user = super().create(data, db)
        db.commit()
        db.refresh(user)
        return user, {"message": "User sucessfully created"}


user_service = UserService(UserModel)
