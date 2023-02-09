package com.example.acdat_juegomanusinsprites.clases;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Objects;

public class TeclaPiano {
    private double base, altura, posX, posY;
    protected int id;
    private static int idTemp;
    protected Paint paint;
    private boolean mover;

    public TeclaPiano(double posX, double posY, double base, double altura) {
        this.id = idTemp;
        this.posX = posX;
        this.posY = posY;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.base = base;
        this.altura = altura;
        this.idTemp++;
    }

    public TeclaPiano(double posX, double posY, boolean mover, double base, double altura) {
        this.id = idTemp;
        this.posX = posX;
        this.posY = posY;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.base = base;
        this.altura = altura;
        this.idTemp++;
        this.mover = mover;
    }

    public float getBase() {
        return (float) base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public float getAltura() {
        return (float) altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public float getPosX() {
        return (float) posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return (float) posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public boolean isMover() {
        return mover;
    }

    public void setMover(boolean mover) {
        this.mover = mover;
    }

    public Boolean isTouched(int x, int y) {
        Boolean dentro = false;

        if(x > getPosX() && x < (getPosX() + getBase()) && y > getPosY() && y < (getPosY() + getAltura())) {
            dentro = true;
        }

        return dentro;
    }

    public void onDraw(Canvas canvas) {
        canvas.drawRect(getPosX(), getPosY(), getPosX() + getBase(), getPosY() + getAltura(), getPaint());
    }

    public void setPositionUpdated(int x, int y){
        this.setPosX(this.getPosX() + x);
        this.setPosY(this.getPosY() + y);
    }

    public boolean isHover(TeclaPiano t) {
        double centroX = (getBase() / 2) + getPosX();
        double centroY = (getAltura() / 2) + getPosY();

        double centroXR = (t.getBase() / 2) + t.getPosX();
        double centroYR = (t.getAltura() / 2) + t.getPosY();

        double distanciaPuntos = Math.sqrt(Math.pow(centroXR - centroX, 2) + Math.pow(centroYR - centroY, 2));

        if(distanciaPuntos < (getBase() / 2) && getBase() == t.getBase() && getAltura() == t.getAltura()){
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeclaPiano that = (TeclaPiano) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TeclaPiano{" +
                "base=" + base +
                ", altura=" + altura +
                ", posX=" + posX +
                ", posY=" + posY +
                ", id=" + id +
                ", paint=" + paint +
                ", mover=" + mover +
                '}';
    }
}
