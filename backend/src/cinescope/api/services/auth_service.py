from datetime import datetime, timedelta
from jwt import encode, decode
from jwt.exceptions import DecodeError
from pwdlib import PasswordHash

pwd_context = PasswordHash.recommended()

SECRET_KEY = "your_secret_key"
ALGORITHM = "HS256"
ACCESS_TOKEN_EXPIRE_MINUTES = 30

class AuthService:
    def get_password_hash(self, password: str):
        return pwd_context.hash(password)

    def verify_password(self, plain_password: str, hashed_password: str):
        return pwd_context.verify(plain_password, hashed_password)

    def create_acess_token(self, data: dict):
        to_encode = data.copy()
        
        expire = datetime.now().astimezone() + timedelta(
            minutes=ACCESS_TOKEN_EXPIRE_MINUTES
        )

        to_encode.update({"exp": expire})
        
        encode_jwt = encode(to_encode, SECRET_KEY, algorithm=ALGORITHM)
        return encode_jwt
    
auth_service = AuthService()