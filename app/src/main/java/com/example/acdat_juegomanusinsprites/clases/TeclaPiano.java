package com.example.acdat_juegomanusinsprites.clases;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.acdat_juegomanusinsprites.vistas.PianoView;

import java.util.Objects;

public class TeclaPiano {
    private double base, altura, posX, posY;
    protected int id, ySpeed;;
    private static int idTemp;
    protected Paint paint;
    private final double POS_X_INI, POS_Y_INI;
    private PianoView pianoView;

    public TeclaPiano(double posX, double posY, double base, double altura, PianoView pianoView) {
        this.id = idTemp;
        this.posX = posX;
        this.posY = posY;
        POS_X_INI = posX;
        POS_Y_INI = posY;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.base = base;
        this.altura = altura;
        this.pianoView = pianoView;
        this.idTemp++;
        this.ySpeed = 30;
    }

    public Boolean isTouched(float x, float y) {
        Boolean dentro = false;

        if(x > posX && x < (posX + base) && y > posY && y < (posY + altura)) {
            dentro = true;
        }

        return dentro;
    }

    public void onDraw(Canvas canvas) {
        canvas.drawRect((float) posX, (float)  posY, (float)  posX + (float)  base, (float)  posY + (float)  altura, paint);
    }

    public void setPositionUpdated(int x, int y){
        posX = posX + x;
        posY = posY + y;
    }

    public boolean isHover(TeclaPiano t) {
        double centroX = (base / 2) + posX;
        double centroY = (altura / 2) + posY;

        double centroXR = (t.base / 2) + t.posX;
        double centroYR = (t.altura / 2) + t.posY;

        double distanciaPuntos = Math.sqrt(Math.pow(centroXR - centroX, 2) + Math.pow(centroYR - centroY, 2));

        if(distanciaPuntos < (base / 2) && base == t.base && altura == t.altura){
            return true;
        }

        return false;
    }

    public void update(){
        if(posY > pianoView.getHeight() - altura - ySpeed){
            ySpeed = -ySpeed;
        }
        posY = posY + ySpeed;
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
                '}';
    }
}
