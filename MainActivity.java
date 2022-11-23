package com.example.skanowaniewifi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    TextView wifi,plik,magnetomet,akcelenometr;
    Switch przełocznik;
    EditText X;
    EditText Y;
    Pozycjonowanie pozycja;
    final String Baza="daneBazy.jos";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //deklaracja klas widoku
        wifi = (TextView) findViewById(R.id.WIFI);
        przełocznik=(Switch) findViewById(R.id.switch3);
        X=(EditText) findViewById(R.id.editTextNumberSigned);
        Y=(EditText) findViewById(R.id.editTextNumberSigned3);
        plik= (TextView) findViewById(R.id.plik);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        pozycja = new Pozycjonowanie(getApplicationContext(),Baza);
    }

    public void Kasuj_skany(View view)
    {
        pozycja.Kasuj_baze_skanów();
        plik.setText(pozycja.wypisz_zawartość_bazy());

    }


    public void Operacje_na_pozycji(View view)
    {
        if(przełocznik.isChecked()) {
            zapiszywanie_pozycji();
        }
        else
            odczytywanie_pozycji();
    }

    private void odczytywanie_pozycji()
    {

        pozycja.odczytaj_pozycje(wifi);
    }

    private void zapiszywanie_pozycji() {
        pozycja.zapisz_skan_do_Bazy(Float.valueOf(String.valueOf(X.getText())),Float.valueOf(String.valueOf(Y.getText())));
    }


    public void odczytwanie_plik(View view)
    {
        plik.setText(pozycja.wypisz_zawartość_bazy());
    }


    public void wyslij_do_bazy(View view)
    {
        pozycja.wyślij_skany_do_bazy();
    }
}