package com.example.skanowaniewifi;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Kompas  {

    private SensorManager Czujniki;
    List<Sensor> czujnik_grawitacji;

    public Kompas(@NonNull Context kontekst) {
        Czujniki = (SensorManager) kontekst.getSystemService(Context.SENSOR_SERVICE);
    }

    public void Akcje_Czujniki(TextView ekran_Magnetometru,TextView ekran_Akcelerometru)
    {
        akcje_czujnika(ekran_Magnetometru,Sensor.TYPE_MAGNETIC_FIELD);
        akcje_czujnika(ekran_Akcelerometru,Sensor.TYPE_ACCELEROMETER);
    }

    private void akcje_czujnika(TextView ekran,int typ)
    {
        SensorEventListener  czujnik= new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent event)
            {
                ekran.setText(Wypisz_dane_czujnika(event.values));
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        Czujniki.registerListener(czujnik, Czujniki.getDefaultSensor(typ),0,null );
    }
    private String Wypisz_dane_czujnika(float[] dane)
    {
        String wynik=new String();

        wynik+="X:"+dane[0];
        wynik+="\ny:"+dane[1];
        wynik+="\nZ:"+dane[2];
        return wynik;
    }
}
