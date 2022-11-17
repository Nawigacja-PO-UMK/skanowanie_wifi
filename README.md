# skanowanie_wifi

Klasa Pozycjonowanie jest główną klasą pozycjonującą za pomoca wifi i zapisującą pozycje ze skanami.By utworzyć instacje tej klasy potrzeba klasy Context którą uzyskujemy dzięki getApplicationContext()(ważne! ta medoda musi byś wykonywana w klasię dziedziczącej po AppCompatActivity).   

Klasa pozycjonująca zawiera metody:

void Wświetl_czujniki(TextView wifi)
wyświetla ona w sposub dynamiczne aktualne skany.

void zapisz_skan_do_Bazy(float x,float y)
wykonuje skan i zapisyje x,y w bazie ze skanem 
