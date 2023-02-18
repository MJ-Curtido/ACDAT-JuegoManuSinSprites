package com.example.acdat_juegomanusinsprites.rv;

import android.graphics.drawable.Drawable;

public class ItemLista {
    private String textoFecha;
    private String textoPuntuacion;

    public String getTextoFecha() {
        return textoFecha;
    }

    public void setTextoFecha(String textoFecha) {
        this.textoFecha = textoFecha;
    }

    public String getTextoPuntuacion() {
        return textoPuntuacion;
    }

    public void setTextoPuntuacion(String textoPuntuacion) {
        this.textoPuntuacion = textoPuntuacion;
    }

    public ItemLista(String textoFecha, String textoPizza) {
        this.textoFecha = textoFecha;
        this.textoPuntuacion = textoPizza;
    }
}
