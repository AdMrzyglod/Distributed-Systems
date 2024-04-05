from fastapi import FastAPI, Request
from starlette import status
from starlette.responses import JSONResponse
from data_processors import (get_min_mean_max_currencies, construct_currencies_dict, convert_all_countries_data, \
                             covert_country_string_to_list, PKB_CURRENCY, get_min_mean_max_countries,
                             construct_currency_model, construct_country_model_list, construct_final_countries_dict,
                             construct_countries_list)
from data_services import get_all_currencies, get_currency_by_date, get_countries_codes, get_countries_data_by_date
from data_validators import validate_date, date_compare, validate_currency_code, validate_year, year_compare, \
    validate_country_code
from fastapi.templating import Jinja2Templates
from token_authentication import create_token, check_token

app = FastAPI()

templates = Jinja2Templates(directory="templates")


@app.get("/")
async def root():
    return {"message": "APP"}


@app.get("/token_index")
async def token_index(request: Request):
    return templates.TemplateResponse(
        request=request, name="token_index.html"
    )


@app.get("/token_create")
async def token_create():
    token = create_token()
    return {"token": token}


@app.get("/input_index")
async def get_input_html(request: Request):
    currencies_data, data_code = await get_all_currencies()
    if data_code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych"})
    data, code = await get_countries_codes()
    if code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych"})

    return templates.TemplateResponse(
        request=request, name="index.html",
        context=dict(currencies_data=currencies_data, countries_data=convert_all_countries_data(data))
    )


@app.get("/currencies_names")
async def get_currencies_name():
    data, code = await get_all_currencies()
    if code == status.HTTP_200_OK:
        return JSONResponse(status_code=code, content=data)
    else:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych walut"})


@app.get("/currencies_results")
async def get_currency_data(request: Request, api_key_cr: str, from_currency: str, to_currency: str, from_date: str,
                            to_date: str):
    if not check_token(api_key_cr):
        return JSONResponse(status_code=status.HTTP_401_UNAUTHORIZED,
                            content={"Error": "Klucz jest niepoprawny lub wygasł"})

    currencies_data, data_code = await get_all_currencies()

    if data_code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych"})

    if not validate_date(from_date) or not validate_date(to_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy format daty"})

    if not date_compare(from_date, to_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Data początkowa musi być mniejsza lub równa niż data końcowa oraz obie "
                                              "daty muszą być mniejsze lub równe niż dzień dzisiejszy"})
    if not validate_currency_code(currencies_data, from_currency) or not validate_currency_code(currencies_data,
                                                                                                to_currency) or from_currency == to_currency:
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy kod waluty"})

    data, code = await get_currency_by_date(from_date, to_date)
    if code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych"})

    currency = construct_currency_model(data, from_currency, to_currency, from_date, to_date)
    context_dict = construct_currencies_dict(currencies_data, currency,
                                             *get_min_mean_max_currencies(currency))

    return templates.TemplateResponse(
        request=request, name="currency_view.html", context=context_dict
    )


@app.get("/currencies_results_json")
async def get_currency_data_json(api_key_cr: str, from_currency: str, to_currency: str, from_date: str, to_date: str):
    if not check_token(api_key_cr):
        return JSONResponse(status_code=status.HTTP_401_UNAUTHORIZED,
                            content={"Error": "Klucz jest niepoprawny lub wygasł"})

    currencies_data, data_code = await get_all_currencies()

    if data_code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych"})

    if not validate_date(from_date) or not validate_date(to_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy format daty"})

    if not date_compare(from_date, to_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Data początkowa musi być mniejsza lub równa niż data końcowa oraz obie "
                                              "daty muszą być mniejsze lub równe niż dzień dzisiejszy"})
    if not validate_currency_code(currencies_data, from_currency) or not validate_currency_code(currencies_data,
                                                                                                to_currency) or from_currency == to_currency:
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy kod waluty"})

    data, code = await get_currency_by_date(from_date, to_date)
    if code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych"})

    currency = construct_currency_model(data, from_currency, to_currency, from_date, to_date)
    context_dict = construct_currencies_dict(currencies_data, currency,
                                             *get_min_mean_max_currencies(currency))

    return context_dict


@app.get("/countries_names")
async def get_countries_name():
    data, code = await get_countries_codes()
    if code == status.HTTP_200_OK:
        return JSONResponse(status_code=code, content=convert_all_countries_data(data))

    return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                        content={"Error": "Błąd podczas pobierania danych państw"})


@app.get("/countries_results")
async def get_countries_results(request: Request, api_key_ct: str, currency_code: str, from_currency_date: str,
                                to_currency_date: str,
                                countries_list: str, from_year: str,
                                to_year: str):
    if not check_token(api_key_ct):
        return JSONResponse(status_code=status.HTTP_401_UNAUTHORIZED,
                            content={"Error": "Klucz jest niepoprawny lub wygasł"})

    currencies_data, data_code = await get_all_currencies()

    if data_code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych walut"})

    if not validate_currency_code(currencies_data, currency_code):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy kod waluty"})

    if not validate_date(from_currency_date) or not validate_date(to_currency_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy format daty"})

    if not date_compare(from_currency_date, to_currency_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Data początkowa musi być mniejsza lub równa niż data końcowa oraz obie "
                                              "daty muszą być mniejsze lub równe niż dzień dzisiejszy"})

    if not validate_year(from_year) or not validate_year(to_year):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy format roku"})

    if not year_compare(from_year, to_year):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Rok początkowy musi być mniejszy lub równy niż rok końcowy oraz oba "
                                              "muszą być mniejsze lub równe niż rok aktualny"})

    countries_list_convert = covert_country_string_to_list(countries_list)

    data_countries, countries_code = await get_countries_codes()
    if countries_code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych państw"})
    data_countries_convert = convert_all_countries_data(data_countries)

    for country_code in countries_list_convert:
        if not validate_country_code(data_countries_convert, country_code):
            return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                                content={"Error": "Nieprawidłowe kody krajów"})

    data_currency_range, code_range = await get_currency_by_date(from_currency_date, to_currency_date)
    if code_range != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych walut"})

    currency = construct_currency_model(data_currency_range, PKB_CURRENCY, currency_code, from_currency_date,
                                        to_currency_date)
    currency_dict = construct_currencies_dict(currencies_data, currency,
                                              *get_min_mean_max_currencies(currency))

    data_countries_range, code_country_range = await get_countries_data_by_date(countries_list_convert, from_year,
                                                                                to_year)
    if code_country_range != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych krajów"})

    country_list_convert = construct_country_model_list(data_countries_range, data_countries_convert, from_year,
                                                        to_year)
    countries_list_dict = construct_countries_list(country_list_convert,
                                                   get_min_mean_max_countries(country_list_convert))

    context_dict = construct_final_countries_dict(currency_dict, countries_list_dict, from_year, to_year)

    return templates.TemplateResponse(
        request=request, name="countries_view.html",
        context=context_dict
    )


@app.get("/countries_results_json")
async def get_countries_results_json(api_key_ct: str, currency_code: str, from_currency_date: str,
                                     to_currency_date: str,
                                     countries_list: str, from_year: str,
                                     to_year: str):
    if not check_token(api_key_ct):
        return JSONResponse(status_code=status.HTTP_401_UNAUTHORIZED,
                            content={"Error": "Klucz jest niepoprawny lub wygasł"})

    currencies_data, data_code = await get_all_currencies()

    if data_code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych walut"})

    if not validate_currency_code(currencies_data, currency_code):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy kod waluty"})

    if not validate_date(from_currency_date) or not validate_date(to_currency_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy format daty"})

    if not date_compare(from_currency_date, to_currency_date):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Data początkowa musi być mniejsza lub równa niż data końcowa oraz obie "
                                              "daty muszą być mniejsze lub równe niż dzień dzisiejszy"})

    if not validate_year(from_year) or not validate_year(to_year):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Nieprawidłowy format roku"})

    if not year_compare(from_year, to_year):
        return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY,
                            content={"Error": "Rok początkowy musi być mniejszy lub równy niż rok końcowy oraz oba "
                                              "muszą być mniejsze lub równe niż rok aktualny"})

    countries_list_convert = covert_country_string_to_list(countries_list)

    data_countries, countries_code = await get_countries_codes()
    if countries_code != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych państw"})
    data_countries_convert = convert_all_countries_data(data_countries)

    for country_code in countries_list_convert:
        if not validate_country_code(data_countries_convert, country_code):
            return JSONResponse(status_code=status.HTTP_422_UNPROCESSABLE_ENTITY, content={"Error": "Nieprawidłowe "
                                                                                                    "kody krajów"})

    data_currency_range, code_range = await get_currency_by_date(from_currency_date, to_currency_date)
    if code_range != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych walut"})

    currency = construct_currency_model(data_currency_range, PKB_CURRENCY, currency_code, from_currency_date,
                                        to_currency_date)
    currency_dict = construct_currencies_dict(currencies_data, currency,
                                              *get_min_mean_max_currencies(currency))

    data_countries_range, code_country_range = await get_countries_data_by_date(countries_list_convert, from_year,
                                                                                to_year)
    if code_country_range != status.HTTP_200_OK:
        return JSONResponse(status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                            content={"Error": "Błąd podczas pobierania danych krajów"})

    country_list_convert = construct_country_model_list(data_countries_range, data_countries_convert, from_year,
                                                        to_year)
    countries_list_dict = construct_countries_list(country_list_convert,
                                                   get_min_mean_max_countries(country_list_convert))

    context_dict = construct_final_countries_dict(currency_dict, countries_list_dict, from_year, to_year)

    return context_dict
