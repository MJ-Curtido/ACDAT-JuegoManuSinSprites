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

    public TeclaPiano(double base, double altura, PianoView pianoView) {
        this.id = idTemp;
        this.posX = pianoView.getIniBase() * (int)(Math.random() * pianoView.FILAS_TECLAS);
        this.posY = 0 - pianoView.getIniAltura();
        POS_X_INI = posX;
        POS_Y_INI = posY;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.base = base;
        this.altura = altura;
        this.pianoView = pianoView;
        this.idTemp++;
        if ((20 + (pianoView.getContPiezas() / 4)) < pianoView.Y_SPEED_FIN) {
            this.ySpeed = 20 + (pianoView.getContPiezas() / 4);
        }
        else {
            this.ySpeed = pianoView.Y_SPEED_FIN;
        }
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

    public void update(){
        if((posY + altura) > (pianoView.getAltoPantalla() - altura)) {
            pianoView.pararJuego();
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
