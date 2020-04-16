package com.nbegin.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Basedrum basedrum;
    Cymbale1 cymbale;
    Hihat hihat;
    Snare snare;
    Tom1 tom1;
    Tom2 tom2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialiseClass();

    }

    private void InitialiseClass() {
        basedrum = findViewById(R.id.bassedrum1);
        cymbale = findViewById(R.id.cymbale1);
        hihat = findViewById(R.id.hihat);
        snare = findViewById(R.id.snare);
        tom1 = findViewById(R.id.tom1);
        tom2 = findViewById(R.id.tom2);
    }


}
