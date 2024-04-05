import numpy as np
from data_models import Currency, Country

PKB_CURRENCY = "USD"
PRECISION = 10 ** 9


def construct_currency_model(data: dict, from_currency: str, to_currency: str, from_date: str,
                             to_date: str) -> Currency:
    currency = Currency(base=from_currency, target=to_currency, from_date=from_date, to_date=to_date)
    currency.daily_rates = [(day, convert_currencies(from_currency, to_currency, currencies)) for day, currencies in
                            data["rates"].items()]
    return currency


def construct_country_model(data: dict, countries_codes: dict, from_year: str, to_year: str) -> Country:
    country = Country(code=data["country_code"], name=countries_codes[data["country_code"]], from_year=from_year,
                      to_year=to_year)
    country.GDP_list = [(dataYear["date"], 0 if dataYear["value"] is None else dataYear["value"]) for dataYear in
                        data["data"][1]]

    return country


def construct_country_model_list(data: list, countries_codes: dict, from_year: str, to_year: str):
    countries_list = []
    for country_data in data:
        countries_list.append(construct_country_model(country_data, countries_codes, from_year, to_year))

    return countries_list


def convert_currencies(from_currency: str, to_currency: str, currencies: dict):
    if from_currency not in currencies and to_currency not in currencies:
        return 1
    if from_currency not in currencies:
        return currencies[to_currency]
    if to_currency not in currencies:
        return round(1 / currencies[from_currency], 4)
    return round(currencies[to_currency] / currencies[from_currency], 4)


def get_min_mean_max_currencies(currency: Currency):
    converted_data = np.array(currency.daily_rates)
    converted_array = converted_data[:, 1].astype(float)

    mean_value = np.round(np.mean(np.array(converted_array)), 4)
    index_min = np.argmin(np.array(converted_array))
    index_max = np.argmax(np.array(converted_array))

    return [mean_value], [converted_data[index_min][0], converted_array[index_min]], [converted_data[index_max][0],
                                                                                      converted_array[index_max]]


def construct_currencies_dict(currencies_names: dict, currency: Currency, mean_data: list, min_data: list, max_data: list):
    currencies_dict = dict(from_currency_name=currencies_names[currency.base],
                           to_currency_name=currencies_names[currency.target],
                           from_currency=currency.base,
                           to_currency=currency.target, from_date=currency.from_date,
                           to_date=currency.to_date, mean_value=mean_data[0], min_date=min_data[0],
                           min_value=min_data[1],
                           max_date=max_data[0], max_value=max_data[1])
    return currencies_dict


def convert_all_countries_data(countries_data: list):
    countries_list = countries_data[1]
    codes_dict = {country["id"]: country["name"] for country in countries_list}
    return codes_dict


def covert_country_string_to_list(country_string: str):
    country_list = [country.strip() for country in country_string.split(",") if len(country.strip()) != 0]
    return country_list


def get_min_mean_max_country(country: Country):
    converted_data = np.array(country.GDP_list)

    converted_array = converted_data[:, 1].astype(float)
    non_zero_array = [val for val in converted_array if val != 0]

    mean_value = np.round(np.mean(np.array(non_zero_array)), 4) if non_zero_array else 0
    index_min = np.argmin(np.array(converted_array))
    index_max = np.argmax(np.array(converted_array))

    return [mean_value], [converted_data[index_min][0], converted_array[index_min]], [converted_data[index_max][0],
                                                                                      converted_array[index_max]]


def get_min_mean_max_countries(country_list: list[Country]):
    converted_data = [get_min_mean_max_country(country) for country in country_list]

    return converted_data


def construct_countries_list(country_list: list[Country], country_data: list):
    countries_dict_list = []

    for model, convert in zip(country_list, country_data):
        country_dict = dict(country_name=model.name,
                            mean_PKB=convert[0][0],
                            min_year=convert[1][0], min_PKB=convert[1][1],
                            max_year=convert[2][0], max_PKB=convert[2][1])

        countries_dict_list.append(country_dict)

    return countries_dict_list


def construct_final_countries_dict(currency_dict: dict, countries_list: list, from_year: str, to_year: str):
    final_country_dict = dict(from_year=from_year, to_year=to_year, currency_dict=currency_dict.copy(),
                              countries_list=[])

    for country in countries_list:
        country_dict = country.copy()
        for currency_type in ["min_value", "mean_value", "max_value"]:
            country_dict[currency_type] = []
            for PKB_type in ["min_PKB", "mean_PKB", "max_PKB"]:
                country_dict[currency_type].append(
                    np.round((currency_dict[currency_type] * country[PKB_type]) / PRECISION, 2))
        final_country_dict["countries_list"].append(country_dict)

    final_country_dict["countries_list"].sort(key=lambda data: -data["max_PKB"])

    return final_country_dict
