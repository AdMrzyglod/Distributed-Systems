import httpx

CURRENCY_URL = "https://api.frankfurter.app/"
COUNTRY_URL = "http://api.worldbank.org/v2/"


async def get_all_currencies():
    async with httpx.AsyncClient() as client:
        response = await client.get(CURRENCY_URL + "currencies")
        if response.status_code == 200:
            return response.json(), 200
        else:
            return {"Error": "Wystąpił problem z pobraniem danych"}, response.status_code


async def get_currency_by_date(from_date: str, to_date: str):
    async with httpx.AsyncClient() as client:
        response = await client.get(CURRENCY_URL + from_date + ".." + to_date + "?from=" + "USD")
        if response.status_code == 200:
            return response.json(), 200
        else:
            return {"Error": "Wystąpił problem z pobraniem danych"}, response.status_code


async def get_countries_codes():
    async with httpx.AsyncClient() as client:
        response = await client.get(COUNTRY_URL + "country?format=json")
        if response.status_code != 200:
            return {"Error": "Wystąpił problem z pobraniem danych"}, response.status_code

        number_of_countries = response.json()[0]["total"]

        response_all_countries = await client.get(
            COUNTRY_URL + "country?format=json&per_page=" + str(number_of_countries))
        if response_all_countries.status_code != 200:
            return {"Error": "Wystąpił problem z pobraniem danych"}, response_all_countries.status_code

        return response_all_countries.json(), 200


async def get_countries_data_by_date(countries_codes: list[str], from_year: str, to_year: str):
    list_of_countries_ranges = []
    async with httpx.AsyncClient() as client:
        for country_code in countries_codes:
            response = await client.get(
                COUNTRY_URL + "country/" + country_code + "/indicator/NY.GDP.MKTP.CD?format=json&date=" + from_year + ":" + to_year + "&per_page=100")
            if response.status_code != 200:
                return {"Error": "Wystąpił problem z pobraniem danych"}, response.status_code
            list_of_countries_ranges.append(dict(country_code=country_code, data=response.json()))

    return list_of_countries_ranges, 200
