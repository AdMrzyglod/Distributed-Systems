import json
from datetime import datetime, timedelta
import random
import string
from pydantic import BaseModel
from data_validators import validate_api_token

KEY_LEN = 64


class Token(BaseModel):
    access_token: str
    start_date: str
    end_date: str


def generate_random_api_key():
    return "".join(random.choice(string.ascii_letters + string.digits) for i in range(KEY_LEN))


def create_token() -> Token:

    with open("token_db.json", "r+") as file:
        data = json.load(file)

        access_token = generate_random_api_key()
        while access_token in data:
            access_token = generate_random_api_key()

        start_date = str(datetime.now())
        end_date = str(datetime.now() + timedelta(hours=6))
        token = Token(access_token=access_token, start_date=start_date, end_date=end_date)

        data[access_token] = token.dict()
        file.seek(0)
        json.dump(data, file)

    return token


def check_token(access_token: str) -> bool:
    if not validate_api_token(access_token):
        return False
    with open("token_db.json", "r") as file:
        data = json.load(file)
        if access_token in data:
            token_data = Token(**data[access_token])
            end_date = datetime.fromisoformat(token_data.end_date)
            if end_date > datetime.now():
                return True
    return False
