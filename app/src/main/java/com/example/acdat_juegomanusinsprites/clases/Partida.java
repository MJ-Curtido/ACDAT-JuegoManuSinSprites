package com.example.acdat_juegomanusinsprites.clases;

import java.util.Objects;

public class Partida {
    private int id;
    private String fecha, puntuacion;

    public Partida(String fecha, String puntuacion) {
        this.id = id;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
    }

    public Partida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partida partida = (Partida) o;
        return id == partida.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
