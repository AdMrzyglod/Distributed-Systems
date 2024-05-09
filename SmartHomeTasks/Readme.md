# Zadanie - "Inteligentny" dom

## Technologia middleware: ZERO ICE. 


## Serwer został napisany w języku Java (JavaServer).

### Aby uruchomić serwer należy użyć komendy:

```bash
    ./gradlew --console plain runServer --args "number"
```
gdzie "number" to numer z zakresu od 2 do 50 oznaczający numer serwera


## Dostępne komendy:

* ### show :
  Pokazuje identyfikatory aktualnie dostępnych instancji urządzeń
* ### exit :
  Zamyka serwer


## Klient został napisany w języku Python (PythonClient).

### Aby uruchomić należy:

* pobrać pakiety:
    ```bash
    pip install -r requirements.txt
    ```
* uruchomić klienta:  
    ```bash
    python clientICE.py number
    ```
gdzie number to numer z zakresu od 2 do 50 oznaczający numer serwera, do którego należy się podłączyć


## Dostępne komendy:

* ### set [category]/[name]_[type] :
  Tworzy lub gdy istnieje przełącza na instancje odpowiedniego urządzenia.
  * category: 
    * camera
    * lamp
  * name: nazwa 
  * type: 
    * dla lamp:
      * RGB
      * SCHEDULE
    * dla camera:
      * CAMERA
* ### categories :
  Zwraca aktualne nazwy kategorii oraz typy dla każdej kategorii
* ### format:
  Zwraca poprawny format do wpisania w set, [category]/[name]_[type]
* ### exit :
  Kończy działanie programu
### Dla kamery:
* ### camera max_degrees :
  Włącza funkcję getMaxDegreesHorizontalVertical(), zwracającą maksymalne wartości ruchu kamery poziomo i pionowo
* ### camera current_degrees :
  Włącza funkcję getCurrentDegreesHorizontalVertical(), zwracającą aktualne wartości ruchu kamery poziomo i pionowo
* ### camera battery_status :
  Włącza funkcję getBatteryStatus(), zwracającą aktualny stan baterii
* ### camera set_degrees [horizontal_degrees] [vertical_degrees] :
  Włącza funkcję setDegrees(horizontal_degrees, vertical_degrees) ustawiającą wartości ruchu kamery
### Dla każdej lampy:
* ### lamp power_status:
  Włącza funkcję getStatus() zwracającą stan uruchomienia lampy
* ### lamp set_status [status]:
  Włącza funkcję setPowerStatus(status), ustawiającą stan uruchomienia lampy
  * status:
    * ON
    * OFF
* ### lamp brightness:
  Włącza funkcję getBrightnessPercentage() zwracającą wartość jasności lampy
* ### lamp set_brightness [percentage]:
  Włącza funkcję setBrightnessPercentage(percentage) ustawiającą jasność lampy na percentage
  * percentage:
    * zakres od 0 do 100 (int)
### Dla lampy RGB:
* ### lamp color_list:
  Włącza funkcję getColorList() zwracającą listę kolorów dla RGB, lub jeżeli pusta wartość ustawioną
* ### lamp current_color:
  Włącza funkcję getCurrentColor() zwraca aktualny kolor, który ma lampa
* ### lamp add_color [red] [green] [blue]:
  Włącza funkcję addColorToList(RGB(red, green, blue)) dodającą do listy nowy kolor,
  * red,green,blue:
    * zakres od 0 do 255
* ### lamp delete_color [index]:
  Włącza funkcję deleteColorFromList(index) usuwającą kolor na indeksie index,
  * index - int
### Dla lampy SCHEDULE:
* ### lamp plan:
  Włącza funkcję getPlan() zwracającą tygodniowy plan włączenia lampy (Dzień->Godziny działania)
* ### lamp get_day_hours [day_number]:
  Włącza funkcję getDayHours(Day.valueOf(day_number)) zwracającą godziny działania lampy w danym dniu tygodnia
  * day_number: int, zakres od 0 do 6 (Poniedziałek: 0, ...)
* ### lamp update_day_hours [day_number] [start_hour] [end_hour]:
  Włącza funkcję updateDayHours(Day.valueOf(day_number), SmartHome.Hours(start_hour, end_hour)) ustawiającą dla danego
  dnia zakres godzin świecenia lampy
  * day_number: int, zakres od 0 do 6 (Poniedziałek: 0, ...)
  * start_hour, end_hour: int, godziny



