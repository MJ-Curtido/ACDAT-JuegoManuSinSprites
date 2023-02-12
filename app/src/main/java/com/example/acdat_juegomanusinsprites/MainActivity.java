package com.example.acdat_juegomanusinsprites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.acdat_juegomanusinsprites.vistas.PianoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PianoView(MainActivity.this));
    }
}