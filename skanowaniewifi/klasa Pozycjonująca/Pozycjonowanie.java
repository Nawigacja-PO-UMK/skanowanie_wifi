package com.example.skanowaniewifi;

import android.content.Context;
import android.widget.TextView;

public class Pozycjonowanie {

        private Context kontekst;
        private Wifi_Manager WIFI;
        private String plikBazy;

    private Baza baza;

        Pozycjonowanie(Context kontekst,String plikBazy) {
            baza= new Baza(plikBazy,kontekst);
            WIFI = new Wifi_Manager(kontekst);
            this.plikBazy=plikBazy;
        }

        public void Wświetl_czujniki(TextView wifi) {
            wypisz akcjaskanu= new wypisz(wifi);
            WIFI.Akcje_Wifi(akcjaskanu);
        }

        public void zapisz_skan_do_Bazy(wspułżedne XY)
        {
            zapisywanie_wifi_do_Bazy sesja= new zapisywanie_wifi_do_Bazy(baza,XY);
            WIFI.Akcje_Wifi(sesja);
        }
        public void odczytaj_pozycje(TextView ekran)
        {
            odczytywanie_pozycji sesja=new odczytywanie_pozycji(baza,ekran);
            WIFI.Akcje_Wifi(sesja);
        }

        public void dynamiczne_funkcje_na_wifi(Akcje_na_Wifi akcja)
        {
            WIFI.Akcje_Wifi(akcja);
        }

        public String wypisz_zawartość_bazy() {

            typ_danych_bazy_skan[] skany = baza.odczytaj_dane();
            String wypisz = new String("");
            if(skany!= null) {
                for (int i = 0; i < skany.length; i++) {
                    wypisz += "pozycja :" + String.valueOf(skany[i].XY.Y) + " " + String.valueOf(skany[i].XY.X) + "\n";
                    wypisz += "liczba zarejestrowanych punktów" + String.valueOf(skany[i].AP.length) + "\n";
                }
            }
            return wypisz;
        }

        public void Kasuj_baze_skanów()
        {
            baza.kasuj();
        }

}

