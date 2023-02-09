package com.example.acdat_juegomanusinsprites.vistas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.acdat_juegomanusinsprites.clases.TeclaPiano;
import com.example.acdat_juegomanusinsprites.hilos.HiloTecla;

import java.util.ArrayList;

public class PianoView extends SurfaceView implements SurfaceHolder.Callback {
    private HiloTecla hiloTecla;
    private ArrayList<TeclaPiano> teclas;
    private int figuraActiva;
    private int iniX, iniY, iniBase, iniAltura;

    public PianoView(Context context) {
        super(context);

        //hacer que se divida el width de la pantalla entre 4 para poder poner cuánto mide la base de las teclas, además también para poder saber en qué cuatro sitios podemos hacer aparecer las teclas en la pantalla, y lo mismo con el height para dividirlo entre 7 o así más o menos y poder poner la altura de la tecla acorde a cualquier pantalla
        figuraActiva = -1;
        iniX = 0;
        iniY = 0;
        iniBase = 0;
        iniAltura = 0;

        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int id = 0;

        teclas = new ArrayList<TeclaPiano>();
        teclas.add(new TeclaPiano(iniX, iniY, 200,100));
        teclas.add(new TeclaPiano(iniX, iniY, 500, 200));

        hiloTecla = new HiloTecla(this);
        hiloTecla.setRunning(true);
        hiloTecla.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        hiloTecla.setRunning(false);

        while (retry){
            try {
                hiloTecla.join();
                retry = false;
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        TeclaPiano t = teclas.get(0);
        t.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for (TeclaPiano t : teclas) {
                    if (t.isTouched(x, y)) {
                        figuraActiva = t.getId();
                        iniX = (int) event.getX();
                        iniY = (int) event.getY();
                        Log.i("FiguraActiva", "ID: " + figuraActiva);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(figuraActiva != -1){
                    teclas.get(figuraActiva).setPositionUpdated(x - iniX, y - iniY);
                    iniX = (int) event.getX();
                    iniY = (int) event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                figuraActiva = -1;
        }

        return true;
    }
}
