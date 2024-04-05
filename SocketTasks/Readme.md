

Aby uruchomić program:

 - serwera należy wpisać w konsole:
    ./gradlew --console plain runServer

 - klienta należy wpisać w konsole:
    ./gradlew --console plain runClient --args "exampleNickname"
    
    gdzie w miejsce exampleNickname należy wpisać nick użytkownika


Wpisanie u klienta:
- tekstu powoduje wysłanie wiadomości przez TCP
- U powoduje wysłanie przygotowanego w programie ASCII Art przez UDP
- M powoduje wysłanie przygotowanego w programie ASCII Art przez Multicast
- EXIT lub użycie skrótu CTRL-C powoduje zakończenie programu klienta


Wpisanie na serwerze:
- EXIT lub użycie skrótu CTRL-C powoduje zakończenie programu serwera