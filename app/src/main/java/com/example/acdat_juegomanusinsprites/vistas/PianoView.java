package com.example.acdat_juegomanusinsprites.vistas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private HiloTecla hiloTecla;
    private ArrayList<TeclaPiano> teclas;
    private int figuraActiva;
    private int iniX, iniY, iniBase, iniAltura, anchoPantalla, altoPantalla;
    private final int FILAS_TECLAS = 4, COLUMNAS_TECLAS = 7;

    public PianoView(Context context) {
        super(context);

        figuraActiva = -1;
        anchoPantalla = getResources().getDisplayMetrics().widthPixels;
        altoPantalla = getResources().getDisplayMetrics().heightPixels;
        iniX = iniBase * (int)(Math.random() * FILAS_TECLAS);
        iniY = 0 - iniAltura;
        iniBase = anchoPantalla / FILAS_TECLAS;
        iniAltura = altoPantalla / COLUMNAS_TECLAS;

        getHolder().addCallback(this);
        setBackgroundColor(Color.WHITE);
    }

    public void crearTecla() {
        teclas.add(new TeclaPiano(iniX, iniY, iniBase, iniAltura, this));
        HiloTecla hilo = new HiloTecla(teclas.get(teclas.size()-1));
        hilo.start();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        int id = 0;

        teclas = new ArrayList<TeclaPiano>();

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

        canvas.drawColor(Color.WHITE);

        TeclaPiano t = teclas.get(0);
        t.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (teclas){
            for(int i = teclas.size() - 1; i >= 0; i--){
                TeclaPiano tecla = teclas.get(i);
                if(tecla.isTouched(event.getX(), event.getY())){
                    teclas.remove(tecla);
                    return false;
                }
            }
        }

        return false;
    }
}
