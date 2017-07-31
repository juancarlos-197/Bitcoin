package com.example.juanc.bitcoin.Modelo;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.juanc.bitcoin.R;

public class SplashScreen extends AppCompatActivity {

    /*Designación de variables*/
    public static final int segundos = 5;
    public static final int milisegundos = segundos * 1000;
    private ProgressBar pbProgreso;
    public static final int regresar = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pbProgreso = (ProgressBar) findViewById(R.id.pbProgreso);
        pbProgreso.setMax(maximos_progreso());
        empezarAnimacion();
    }

    /* Metodo para la animacion por medio de 8 segundos y que transcurra de segundo
     * en segundo */
    public void empezarAnimacion() {
        new CountDownTimer(milisegundos, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                pbProgreso.setProgress(establecer_prpgreso(millisUntilFinished));

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashScreen.this, MapsActivity.class);
                startActivity(intent);
                finish();

            }
        }.start();
    }


    /*Como funcion: la primera vez regresa 8000 lo cual seria 8000 - 8000 = 0
     * 0 / 1000 = 0, la seguiente vez va a devolver 8000 -7000 = 1000
      * 1000/1000=1 y asi va dando 0,1,2,3,4,5,6,7*/
    public int establecer_prpgreso(long milisegun) {
        return (int) ((milisegundos - milisegun) / 1000);
    }

    public int maximos_progreso() {
        return segundos - regresar;
    }
}
