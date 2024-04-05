from typing import List, Tuple
from pydantic import BaseModel


class Currency(BaseModel):
    base: str
    target: str
    from_date: str
    to_date: str
    daily_rates: List[Tuple[str, float]] = []


class Country(BaseModel):
    code: str
    name: str
    from_year: str
    to_year: str
    GDP_list: List[Tuple[str, float]] = []
