package com.example.skanowaniewifi;

import android.net.wifi.ScanResult;

import java.util.List;

public class zapisywanie_wifi_do_Bazy implements Akcje_na_Wifi{

    private Baza baza;
    private wspułżedne XY;
    zapisywanie_wifi_do_Bazy(Baza baza, wspułżedne XY)
    {
        this.XY=XY;
        this.baza=baza;
    }

    @Override
    public void Wykonywanie_funkcji_wifi(List<ScanResult> rezultat_skanu) {

        baza.Zapisywanie_do_Bazy(rezultat_skanu,XY);
    }

    @Override
    public boolean kiedy_zakończyć_skanowanie(List<ScanResult> results) {
        return true;
    }
}
