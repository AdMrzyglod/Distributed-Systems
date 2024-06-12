# Zadanie - Zookeeper

## Zadanie zostało napisane w języku Java.

  Po stworzeniu node uruchamiana jest aplikacja zewnętrzna. Po usunięciu node aplikacja zewnętrzna jest zatrzymywana.
  Dodanie potomka do node aktualizuje liczbę potomków w GUI. GUI umożliwia po kliknięciu przycisku
  wyświetlenie struktury drzewa node. Aplikacja działa w środowisku Replicated ZooKeeper.

### Aby uruchomić ZNodeWatcher należy użyć komendy:

```bash
    ./gradlew --console plain runWatcher --args "host:port znode program"
```
gdzie "host:port" to host oraz port serwera, "znode" to nazwa znode, w zadaniu "/a",
"program" to nazwa programu do uruchomienia


## Dostępne komendy:

* ### exit :
  Kończy działanie programu


## Uruchomienie:

* Należy przenieść katalog tmp (z katalogu files) w odpowiednie miejsce.
* Należy przenieść pliki konfiguracyjne zoo (z katalogu files/conf) w odpowiednie miejsce.
* Uruchomienie serwerów:
  * ```bash
    ./zkServer2.cmd \zoo1.cfg  
     ```
  * ```bash
    ./zkServer2.cmd \zoo2.cfg  
     ```
  * ```bash
    ./zkServer2.cmd \zoo3.cfg  
     ```
* Uruchomienie klientów:
  * ```bash
    ./zkCli.cmd -server 127.0.0.1:2181   
     ```
  * ```bash
    ./zkCli.cmd -server 127.0.0.1:2182  
     ```
  * ```bash
    ./zkCli.cmd -server 127.0.0.1:2183  
     ```

* Następnie należy uruchomić aplikację (przykładowe uruchomienie):
  ```bash
    ./gradlew --console plain runWatcher --args "127.0.0.1:2181 /a mspaint"
  ```










