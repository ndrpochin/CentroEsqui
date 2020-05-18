package com.company;

import java.util.concurrent.Semaphore;

public class Molinete {

    private LectorDeTarjetas lector;
    private Semaphore semaforo; //semaforo para controlar el acceso al molinete

    public Molinete(Semaphore s){
        this.lector = new LectorDeTarjetas();
        this.semaforo = s;
    }

    public void pasarTarjeta() throws InterruptedException    {
        this.semaforo.acquire();    //tomo el semaforo para que ningun otro hilo acceda a leerTarjeta
        Thread.sleep(15);

        this.lector.leerTarjeta();

        Thread.sleep(15);
        this.semaforo.release();    //dejo el semaforo para que otros hilos puedan acceder a leerTarjeta
    }

    public int personasPorLector()  {
        return this.lector.getCantidad();
    }

}
