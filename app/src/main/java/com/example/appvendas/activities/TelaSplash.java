package com.example.appvendas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appvendas.R;

public class TelaSplash extends AppCompatActivity{
    private static int tempoSplash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainScreen();
            }
        }, tempoSplash);
    }

    public void mainScreen(){
        Intent i = new Intent(TelaSplash.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

