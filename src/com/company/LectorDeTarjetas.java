package com.company;

import java.util.concurrent.Semaphore;

public class LectorDeTarjetas {

    private int cantidad; //contiene la cantidad de lecturas de UN SOLO molinete
    private Semaphore mutex;

    public LectorDeTarjetas() {
        this.cantidad = 0;
        this.mutex = new Semaphore(1);
    }

    public int getCantidad()   {
        return this.cantidad;
    }

    public void leerTarjeta() { //se ocupa el molinete. por lo tanto se aumenta uno a la cantidad
        try {
            this.mutex.acquire();//adquiero semaforo para poder aumentar el contador
            this.cantidad++;
            this.mutex.release();   //libero el semaforo
        }catch (InterruptedException ex) {
            System.err.println("ERROR AL ADQUIRIR SEMAFORO EN LECTOR TARJETAS");
        }
    }

}
