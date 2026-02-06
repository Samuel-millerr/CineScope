from sqlalchemy.future import select
from sqlalchemy.orm import Session

from cinescope.api.models.user import UserModel
from cinescope.api.services._base_service import BaseService


class UserService(BaseService[UserModel]):
    def get_user_by_atribute(self, atribute, data_atribute, db: Session):
        query = select(self.model).filter(atribute == data_atribute)
        result = db.execute(query)
        user = result.scalar_one_or_none()
        return user

    def create(self, data, db: Session):
        exists_username = self.get_user_by_atribute(
            self.model.user,
            data["user"],
            db                
        )
        if exists_username:
            return False, {"message": "User with username alredy exists"}
        
        exists_email = self.get_user_by_atribute(
            self.model.email,
            data["email"],
            db
        )
        if exists_email:
            return False, {"message": "User with email alredy exists"}
        
        user = super().create(data, db)
        return user, {}

user_service = UserService(UserModel)
