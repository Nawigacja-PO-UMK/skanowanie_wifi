package com.example.skanowaniewifi;

import static java.lang.Math.abs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.wifi.ScanResult;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


public class Baza {

    private String plikBazy;
    private Context kontekst;
    private JSONArray Bazadanych;

    public Baza(String plikBazy,Context kontekst)  {
        this.plikBazy=plikBazy;
        this.kontekst=kontekst;
        Bazadanych = new JSONArray();
    }

    public typ_danych_bazy_skan[] odczytaj_dane()  {

        String JSON=odczytaj_plik();
        if( JSON!=null) {
           return parsowanie_z_JSON(JSON);
        }
        return null;
    }
    public String odczytaj_plik()
    {
        SharedPreferences plik = kontekst.getSharedPreferences(plikBazy, Context.MODE_PRIVATE);
        String dane=plik.getString("baza","");
        return dane;

    }
    private void Zapisywanie_do_pliku(String dane)
    {
        SharedPreferences plik = kontekst.getSharedPreferences(plikBazy, Context.MODE_PRIVATE);
        SharedPreferences.Editor edytor =plik.edit();
        edytor.putString("baza",dane);
        edytor.apply();
    }
    public void Zapisywanie_do_Bazy(List<ScanResult> rezultat_skanu, wspułżedne XY)
    {
        formatowanie_danych_do_bazy dane= new formatowanie_danych_do_bazy(rezultat_skanu,XY);
        String JSON=parsowanie_do_JSON(dane.get());
        if(JSON!=null) {
            Zapisywanie_do_pliku(JSON);
            Toast.makeText(kontekst, "zapisane", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(kontekst, "nie można zapisać", Toast.LENGTH_LONG).show();
    }

   private String parsowanie_do_JSON(typ_danych_bazy_skan dane)
    {
        try {
            //klasa wspułzedne
            JSONObject xy= new JSONObject();
            xy.put("X",dane.XY.X);
            xy.put("Y",dane.XY.Y);
            JSONArray lista_punktów=new JSONArray();
           for (skan sk: dane.AP) {
                //pojedyńczy zeskanowany router
                JSONObject punkt = new JSONObject();
                punkt.put("Name", sk.Nazwa);
                punkt.put("MAC", sk.MAC);
                punkt.put("RSSI", sk.RSSI);
                ///klasa
                lista_punktów.put(punkt);
            }
            JSONObject skan=new JSONObject();
            skan.put("XY",xy);
            skan.put("skan",lista_punktów);
            Bazadanych.put(skan);
             return Bazadanych.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private typ_danych_bazy_skan[] parsowanie_z_JSON( String dane)
    {

        try {
            JSONArray skany=new JSONArray(dane);
            typ_danych_bazy_skan[]  wynik = new typ_danych_bazy_skan[skany.length()];
            for (int j=0;j<skany.length();j++) {
                JSONObject skan = skany.getJSONObject(j);
                wynik[j]=new typ_danych_bazy_skan();
                ///klasa wspułżednych
                JSONObject xy = skan.getJSONObject("XY");
                wynik[j].XY = new wspułżedne();
                wynik[j].XY.X = (float) xy.getDouble("X");
                wynik[j].XY.Y = (float) xy.getDouble("Y");
                // skany
                JSONArray listapunktów = skan.getJSONArray("skan");
                wynik[j].AP = new skan[listapunktów.length()];
                for (int i = 0; i < listapunktów.length(); i++) {
                    wynik[j].AP[i] = new skan();
                    JSONObject punkt = listapunktów.getJSONObject(i);

                    wynik[j].AP[i].Nazwa = punkt.getString("Name");
                    wynik[j].AP[i].MAC = punkt.getString("MAC");
                    wynik[j].AP[i].RSSI = punkt.getInt("RSSI");
                }
            }
            return wynik;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void kasuj()
    {
        SharedPreferences plik = kontekst.getSharedPreferences(plikBazy, Context.MODE_PRIVATE);
        SharedPreferences.Editor edytor = plik.edit();
        edytor.clear();
        edytor.apply();

    }
}
