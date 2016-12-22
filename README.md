# Metoda elementów skończonych
Program do symulacji ustalonych procesów cieplnych.
### Użycie
Należy zmodyfikować plik wejściowy mes.txt wpisując dane potrzebne do przeprowadzenia symulacji w postaci par klucz-wartość.

Słowa kluczowe:
  - alpha (współczynnik konwekcji wymiany ciepła)
  - numberOfElements (liczba elementów skończonych w układzie)
  - temperature (temperatura otoczenia)
  - q (strumień ciepła)
  - elementX (dane elementu,gdzie X to numer elementu np. element1=length,firstNodeId,secondNodeId,surfaceArea,conductingRate)
 
Słowa kluczowe w pliku można podawać w dowolnej kolejności.

Przykładowa struktura pliku mes.txt:
```sh
numberOfElements=2
temperature=40
alpha=10
q=-150
element1=3.5,1,2,1,75
element2=3.5,2,3,1,75
```
### Wyniki
Po uruchomieniu programu utworzony zostanie plik result.txt zawierający wartośći poszczególnych węzłów układu. Przykładowy plik wynikowy dla układu dwuelementowego:
```sh
t0 = 69.0
t1 = 62.0
t2 = 55.0
```
Enjoy.
