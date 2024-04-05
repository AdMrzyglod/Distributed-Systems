# Zadanie REST

## Aplikacja REST pozwala na:
* konwersje walut - kurs jednej waluty na drugą w zadanym zakresie czasu (od 1999-01-04)
* porównywanie PKB państw - porównywanie PKB wybranych państw w wybranym zakresie 
lat (od 1960 do (w tym momencie) 2022), na wybranej dostępnej walucie na kursie
w zadanym zakresie czasu


## Strony (nie potrzeba kluczy do api):
* kursy walut: The Frankfurter API podaje kursy Europejskiego Banku Centralnego, strona api: https://api.frankfurter.app/
* PKB państw: The World Bank Documents & Report API podaje dane Banku Światowego, strona api: https://api.worldbank.org/


## Aby uruchomić należy:
* pobrać pakiety:
    ```bash
    pip install -r requirements.txt
    ```
* uruchomić aplikację:  
    ```bash
    uvicorn currencies_api:app --reload
    ```
## Endpointy:

* ### /token_index :
  Zwraca formularz który pozwala wygenerować token dostępowy do api
* ### /token_create :
  Tworzy i zwraca token dostępowy do api aktywny 6 godzin od stworzenia
* ### /input_index :
  Podaje stronę na której można uzupełnić dane konwersji walut lub porównywań PKB
* ### /currencies_names :
  Podaje JSON z kodami i nazwami walut
* ### /currencies_results :
  Podaje uzupełnioną stronę z danymi walut w zakresach czasu
* ### /currencies_results_json :
  Podaje JSON z danymi walut w zakresach czasu
* ### /countries_names :
  Podaje JSON z kodami i nazwami państw 
* ### /countries_results :
  Podaje uzupełnioną stronę z danymi PKB krajów w określonej walucie
* ### /countries_results_json :
  Podaje JSON z danymi PKB krajów w określonej walucie

