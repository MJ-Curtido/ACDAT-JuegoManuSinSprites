package com.example.acdat_juegomanusinsprites.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acdat_juegomanusinsprites.R;
import com.example.acdat_juegomanusinsprites.clases.Partida;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolder> {
    private List<ItemLista> mDataSet;

    public AdaptadorDatos() {
        mDataSet = new ArrayList<ItemLista>();
    }

    public void add(ItemLista i) {
        mDataSet.add(i);
        notifyItemInserted(mDataSet.indexOf(i));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemLista itemLista = mDataSet.get(position);
        holder.fecha.setText(itemLista.getTextoFecha());
        holder.puntuacion.setText(itemLista.getTextoPuntuacion());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView fecha;
        protected TextView puntuacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fecha = (TextView) itemView.findViewById(R.id.txtFecha);
            this.puntuacion = (TextView) itemView.findViewById(R.id.txtPuntuacion);
        }
    }
}
