package com.example.acdat_juegomanusinsprites.hilos;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class HiloTecla extends Thread {
    private SurfaceHolder sh;
    private SurfaceView view;
    private  boolean run;

    public HiloTecla(SurfaceView view) {
        this.sh = view.getHolder();
        this.view = view;
        run = false;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (run){
            canvas = null;
            try {
                canvas = sh.lockCanvas(null);
                if(canvas != null) {
                    synchronized (sh) {
                        view.postInvalidate();
                    }
                }
            } finally {
                if (canvas != null) sh.unlockCanvasAndPost(canvas);
            }
        }
    }
}
