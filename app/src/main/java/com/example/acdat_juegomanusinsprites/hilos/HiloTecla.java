package com.example.acdat_juegomanusinsprites.hilos;

import com.example.acdat_juegomanusinsprites.clases.TeclaPiano;

public class HiloTecla extends Thread {
    private TeclaPiano teclaPiano;
    private long tiempoDeUpdate;
    private int cont;
    boolean running;

    public HiloTecla(TeclaPiano teclaPiano) {
        this.teclaPiano = teclaPiano;

        tiempoDeUpdate = 20;
        cont = 0;
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        try {
            while (running){
                cont++;
                Thread.sleep(tiempoDeUpdate);

                teclaPiano.update();

                if (cont == 1000) {
                    cont = 0;
                    tiempoDeUpdate--;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
