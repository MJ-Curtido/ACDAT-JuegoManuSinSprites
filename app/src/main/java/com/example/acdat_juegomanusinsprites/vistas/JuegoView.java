package com.example.acdat_juegomanusinsprites.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JuegoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PianoView(JuegoView.this, JuegoView.this));
    }

    public void salirJuego() {
        finish();
    }
}