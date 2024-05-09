# Zadanie - gRPC-Web

## Technologia middleware: gRPC.


## Serwer został napisany w języku Java (ServerGRPC).

### Aby uruchomić serwer należy użyć komendy:

```bash
    ./gradlew --console plain runServer
```

## Trzeba uruchomić envoy proxy.

### Aby uruchomić proxy trzeba użyć komendy w folderze envoy:

```bash
    docker-compose up
```

## Klient został napisany w języku JavaScript React (client-grpc).

### Aby uruchomić należy użyć komendy:

* uruchomić klienta:
    ```bash
    npm start
    ```



## Funkcje serwisu gRPC:

Aplikacja do ankiet pozwala na stworzenie ankiety, wylistowanie ankiet (strumieniowo),
zagłosowanie, wylistowanie głosów dla ankiety (strumieniowo), pobranie statystyk dla ankiety.


## Podsumowanie:

* Klient został zrealizowany w JavaScript, zaś serwer w Java. GRPC, którego interfejs jest napisany w proto
może być używany w różnych językach programowania.
* Użycie gRPC-Web wymagało pobrania odpowiednich bibliotek i pluginów.
* Można używać wywołań strumieniowych po stronie serwera.
* Trzeba dla odpowiedniej komunikacji skonfigurować serwer proxy Envoy, aby przekazywał żądania między klientem a serwerem. 
Wynika to z używania różnych wersji protokołu HTTP. Jest to dodatkowa warstwa w komunikacji między klientem a serwerem, 
co wpływa na wydajność (jest to wolniejsze).
* Można przesyłać dane w postaci obiektów, które muszą być wcześniej zdefiniowane.   