# skanowanie_wifi

Klasa Pozycjonowanie jest główną klasą pozycjonującą za pomoca wifi i zapisującą pozycje ze skanami.By utworzyć instacje tej klasy potrzeba klasy Context którą uzyskujemy dzięki getApplicationContext()(ważne! ta metoda musi byś wykonywana w klasię dziedziczącej po AppCompatActivity). Klasa ta też potrzebuje odpowiednich uprawnień w pliku AndroidManifest.xml:

"\<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"\>\</uses-permission\>
    \<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"\>\</uses-permission\>
    \<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"\>\</uses-permission\>
    \<uses-permission android:name="android.permission.MANAGE_DOCUMENTS"
        tools:ignore="ProtectedPermissions"\>\</uses-permission\>
"

Klasa pozycjonująca zawiera metody:

void Wświetl_czujniki(TextView wifi)
wyświetla ona w sposub dynamiczne aktualne skany.

void zapisz_skan_do_Bazy(float x,float y)
wykonuje skan i zapisyje x,y w bazie ze skanem 

String wypisz_zawartość_bazy()
wypisuje zawartośc bazy w stringu 

void odczytaj_pozycje(TextView ekran)
odczytuje dynamicznie pozycje i wypisuje na ekran 

void dynamiczne_funkcje_na_wifi(Akcje_na_Wifi akcja)
Pozwala na wykonanie dynamicznych operacji na skanach za pomocą interfejsu

interfajes Akcje_na_Wifi implementuje dwie metody

void Wykonywanie_funkcji_wifi(List \<ScanResult\> rezultat_skanu)
ta metoda jest wykonywana kiedy skanowanie wifi się zakończyło i podaje rezultat skanu

boolean kiedy_zakończyć_skanowanie(List \<ScanResult\> results)
Ta metoda określa kiedy zakończyć nasłuchiwanie skanów z tym że nawet z wartością false wykona się przynajmniej raz.
  
Klasa odczytywanie_pozycji implementuje Akcje_na_Wifi i wykonuje szukania 
 // Ta klasa wymaga dalszej implementacji pod uczenie maszynowe 
