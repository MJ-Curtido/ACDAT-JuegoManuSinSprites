package com.example.acdat_juegomanusinsprites.vistas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_juegomanusinsprites.clases.TeclaPiano;
import com.example.acdat_juegomanusinsprites.hilos.HiloPiano;
import com.example.acdat_juegomanusinsprites.hilos.HiloTecla;

import java.util.ArrayList;

public class PianoView extends SurfaceView implements SurfaceHolder.Callback {
    private HiloPiano hiloPiano;
    private ArrayList<TeclaPiano> teclas;
    private ArrayList<HiloTecla> hilos;
    private int iniBase, iniAltura, anchoPantalla, altoPantalla;
    public final int FILAS_TECLAS = 4, COLUMNAS_TECLAS = 7, Y_SPEED_FIN = 50;;
    private int contNuevaTecla, contPiezas, puntuacion;
    private double limitNuevaTecla;
    private Boolean jugando;

    public PianoView(Context context) {
        super(context);

        anchoPantalla = getResources().getDisplayMetrics().widthPixels;
        altoPantalla = getResources().getDisplayMetrics().heightPixels;
        iniBase = anchoPantalla / FILAS_TECLAS;
        iniAltura = altoPantalla / COLUMNAS_TECLAS;
        contNuevaTecla = -3;
        limitNuevaTecla = 5;
        contPiezas = 0;
        puntuacion = 0;
        jugando = true;

        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    public int getIniBase() {
        return iniBase;
    }

    public int getIniAltura() {
        return iniAltura;
    }

    public int getContPiezas() {
        return contPiezas;
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public void crearTecla() {
        teclas.add(new TeclaPiano(iniBase, iniAltura, this));
        HiloTecla hilo = new HiloTecla(teclas.get(teclas.size()-1));
        hilos.add(hilo);
        hilo.start();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int id = 0;

        teclas = new ArrayList<TeclaPiano>();
        hilos = new ArrayList<HiloTecla>();

        crearTecla();

        hiloPiano = new HiloPiano(this);
        hiloPiano.setRunning(true);
        hiloPiano.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        TeclaPiano t;

        for (int i = 0; i < teclas.size(); i++) {
            t = teclas.get(i);
            t.onDraw(canvas);
        }

        dibujarTablero(canvas);

        if (contNuevaTecla <= limitNuevaTecla) {
            contNuevaTecla++;
        }
        else {
            contNuevaTecla = -3;

            if (limitNuevaTecla > -2) {
                limitNuevaTecla = limitNuevaTecla - 1;
            }

            contPiezas++;

            crearTecla();
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(238, 130, 238));
        paint.setTextSize(77);

        canvas.drawText(puntuacion + "", ((anchoPantalla / 2) - 60), 70, paint);
    }

    private void dibujarTablero(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);

        canvas.drawLine(iniBase, 0, iniBase, altoPantalla, paint);
        canvas.drawLine((iniBase * 2), 0, (iniBase * 2), altoPantalla, paint);
        canvas.drawLine((iniBase * 3), 0, (iniBase * 3), altoPantalla, paint);
        canvas.drawLine(0, (altoPantalla - iniAltura - Y_SPEED_FIN), anchoPantalla, (altoPantalla - iniAltura - Y_SPEED_FIN), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (jugando) {
            if(teclas.get(0).isTouched(event.getX(), event.getY())){
                teclas.remove(0);
                hilos.get(0).setRunning(false);
                hilos.remove(0);
                puntuacion += 10;
            }
            else {
                pararJuego();
            }
        }
        else {
            //hacer que lleve a la pagina inicial y todos felices :)
        }

        return false;
    }

    public void pararJuego() {
        hiloPiano.setRunning(false);

        for (int j = 0; j < hilos.size(); j++) {
            hilos.get(j).setRunning(false);
        }
    }
}
