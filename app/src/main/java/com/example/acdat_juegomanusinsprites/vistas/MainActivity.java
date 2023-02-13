package com.example.acdat_juegomanusinsprites.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_juegomanusinsprites.R;
import com.example.acdat_juegomanusinsprites.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnJugar.setOnClickListener(this);
        binding.btnSalir.setOnClickListener(this);
        binding.btnHistorialPartidas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnJugar:
                startActivity(new Intent(this, PianoView.class));
                break;
            case R.id.btnSalir:
                finish();
                break;
            case R.id.btnHistorialPartidas:
                break;
        }
    }
}