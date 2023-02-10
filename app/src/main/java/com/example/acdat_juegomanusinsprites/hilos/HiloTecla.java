package com.example.acdat_juegomanusinsprites.hilos;

import com.example.acdat_juegomanusinsprites.clases.TeclaPiano;

public class HiloTecla extends Thread {
    private TeclaPiano teclaPiano;
    private long tiempoDeUpdate;
    private int cont;

    public HiloTecla(TeclaPiano teclaPiano) {
        this.teclaPiano = teclaPiano;

        tiempoDeUpdate = 20;
        cont = 0;
    }

    @Override
    public void run() {
        try {
            while (true){
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
