package com.example.acdat_juegomanusinsprites.vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.acdat_juegomanusinsprites.bbdd.DbHelper;
import com.example.acdat_juegomanusinsprites.clases.Partida;
import com.example.acdat_juegomanusinsprites.databinding.ActivityHistorialViewBinding;
import com.example.acdat_juegomanusinsprites.rv.AdaptadorDatos;
import com.example.acdat_juegomanusinsprites.rv.ItemLista;

import java.util.ArrayList;

public class HistorialView extends AppCompatActivity implements View.OnClickListener {
    ActivityHistorialViewBinding binding;
    private AdaptadorDatos adaptadorDatos;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Partida> partidas;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHistorialViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnVolverHistorial.setOnClickListener(this);

        dbHelper = new DbHelper(HistorialView.this);
        partidas = dbHelper.obtenerPartidas();
        linearLayoutManager = new LinearLayoutManager(HistorialView.this);

        rellenarRecycler();
    }

    private void rellenarRecycler() {
        binding.rvPartidas.setLayoutManager(linearLayoutManager);
        adaptadorDatos = new AdaptadorDatos();
        binding.rvPartidas.setAdapter(adaptadorDatos);

        for (int i = 0; i < partidas.size(); i++) {
            adaptadorDatos.add(new ItemLista(partidas.get(i).getFecha(), (partidas.get(i).getPuntuacion() + "")));
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}