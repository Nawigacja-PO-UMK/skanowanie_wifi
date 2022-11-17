package com.example.skanowaniewifi;

import static java.lang.Math.abs;

import android.net.wifi.ScanResult;
import android.widget.TextView;

import java.util.List;

public class odczytywanie_pozycji implements Akcje_na_Wifi{

    private Baza baza;
    private wspułżedne XY;
    private final int skala_błędu_skanowania=4;
    private TextView ekran;
    odczytywanie_pozycji(Baza baza, TextView ekran)
    {
        this.XY=XY;
        this.baza=baza;
        this.ekran=ekran;
        ekran.setText("pozycja jest odczytywana");
    }

    typ_danych_bazy_skan odczytaj_pozycje(wspułżedne XY)
    {
        typ_danych_bazy_skan[] skany=baza.odczytaj_dane();
        if(skany!=null) {
            for (typ_danych_bazy_skan skan : skany) {
                if (skan.XY.X == XY.X && skan.XY.Y == XY.Y)
                    return skan;
            }
        }
        return null;
    }

    typ_danych_bazy_skan odczytaj_pozycje(List<ScanResult> rezultat_saknu)
    {
        typ_danych_bazy_skan[] skany=baza.odczytaj_dane();
        String wynik=new String();
        int lidzba_trafien=0;
        if(skany==null)
            return null;
        for (typ_danych_bazy_skan skan: skany)
        {
            lidzba_trafien=0;
            for (skan i: skan.AP)
            {
                for (ScanResult j: rezultat_saknu)
                {
                    if(i.MAC.equals(j.BSSID)&& abs(i.RSSI-j.level)<skala_błędu_skanowania )
                        lidzba_trafien++;

                }
            }
            if(lidzba_trafien>2)
                return skan;
        }

        return null;

    }

    @Override
    public void Wykonywanie_funkcji_wifi(List<ScanResult> rezultat_skanu) {

        typ_danych_bazy_skan dane=odczytaj_pozycje(rezultat_skanu);
        if(dane!= null)
        ekran.setText("aktualna pozycja to X:"+String.valueOf(dane.XY.X)+"aktualna pozycja to Y:"+String.valueOf(dane.XY.Y));
         else
            ekran.setText("nie znaleziono pozycji");
    }

    @Override
    public boolean kiedy_zakończyć_skanowanie(List<ScanResult> results) {
        return true;
    }
}
