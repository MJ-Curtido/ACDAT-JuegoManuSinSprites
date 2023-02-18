package com.example.acdat_juegomanusinsprites.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_juegomanusinsprites.R;
import com.example.acdat_juegomanusinsprites.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mp = MediaPlayer.create(this, R.raw.menu);
        mp.setLooping(true);
        mp.setVolume(0.3f, 0.3f);
        mp.start();

        binding.btnJugar.setOnClickListener(this);
        binding.btnSalir.setOnClickListener(this);
        binding.btnHistorialPartidas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnJugar:
                mp.stop();
                startActivity(new Intent(this, JuegoView.class));
                break;
            case R.id.btnSalir:
                mp.stop();
                finish();
                break;
            case R.id.btnHistorialPartidas:
                startActivity(new Intent(this, HistorialView.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        mp.stop();
        super.onBackPressed();
    }
}