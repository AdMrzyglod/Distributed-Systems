from datetime import datetime


def validate_date(date_str: str):
    try:
        datetime.strptime(date_str, '%Y-%m-%d')
        return True
    except ValueError:
        return False


def date_compare(from_date: str, to_date: str):
    return datetime.strptime("1999-01-04", "%Y-%m-%d") <= datetime.strptime(from_date, "%Y-%m-%d") <= datetime.strptime(
        to_date, "%Y-%m-%d") < datetime.now()


def validate_currency_code(currencies_data: dict, code: str):
    if not code.isalpha() or not code.isupper() or len(code) != 3:
        return False
    return code in currencies_data


def validate_year(year: str):
    if len(year) != 4 or not year.isdecimal():
        return False
    return True


def year_compare(from_year: str, to_year: str):
    return 1960 <= int(from_year) <= int(to_year) <= datetime.now().year-2


def validate_country_code(countries_data: dict, code: str):
    if not code.isalpha() or not code.isupper() or len(code) != 3:
        return False
    return code in countries_data


def validate_api_token(access_token: str):
    if len(access_token) != 64:
        return False
    return access_token.isalnum()
