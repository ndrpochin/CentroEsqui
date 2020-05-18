package com.company;

import UI.MiEventoListener;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Confiteria {

    private int caja;           //contiene la cantidad de veces q se paga en la caja
    private int mesas;          //Guarda el total de personas q se encuentran en la confiteria (Debe ser menor a 100)
    private Semaphore sCaja;    //semaforo para controlar el acceso a caja
    private Semaphore sMostrador1;//semaforo para controlar el acceso a mostrador 1
    private Semaphore sMostrador2;//semaforo para controlar el acceso a mostrador 2
    private Semaphore sMostradorPostre;//semaforo para controlar el acceso al mostrador de postre
    private Semaphore mutex;//semaforo para controlar atributos de la clase
    private MiEventoListener listener;

    public Confiteria(Semaphore sC, Semaphore m1, Semaphore m2, Semaphore p) {
        this.caja = 0;
        this.mesas = 0;
        this.sCaja = sC;
        this.sMostrador1 = m1;        //Deben inicializarse afuera para poder controlar todos los accesos a los mostradores
        this.sMostrador2 = m2;
        this.sMostradorPostre = p;
        this.mutex = new Semaphore(1);
    }

    public int getMesas()
    {
        return this.mesas;
    }

    public void entrarACaja(Esquiador esquiador) {

        consolaLog("conf1"+esquiador.getNombre()+", entró a la confitería.");
        try {
            this.sCaja.acquire();
            this.mesas++;        //La persona ingresa a la confiteria
            consolaLog("conf1"+esquiador.getNombre()+", entra a la caja.");
            this.caja++;
        } catch (InterruptedException ex) {
            System.err.println("ERROR AL ADQUIRIR SEMAFORO PARA ENTRAR A CAJA");
        }
    }

    public void salirDeCaja(Esquiador esquiador) {
        this.sCaja.release();
        consolaLog("conf2"+esquiador.getNombre()+", salio de la caja.");
    }

    public void entrarARetirarComida(Esquiador esquiador)  {

        Random opcion = new Random();
        try {
            if(esquiador.getMenu() == 1){   //Si eligio el menu 1, tiene que ir a retirar al mostrador 1.
                this.sMostrador1.acquire();

                Thread.currentThread().sleep(20);
                if(opcion.nextBoolean())
                    esquiador.setMenuElegido("Arroz con Pollo \n");
                else
                    esquiador.setMenuElegido("Hamburguesa \n");

                this.sMostrador1.release();

                consolaLog("conf31 "+esquiador.getNombre()+", espera en mostrador 1" + "\n"+
                        ""+esquiador.getNombre()+", retira su menu.");
            }else {
                this.sMostrador2.acquire();
                Thread.currentThread().sleep(20);

                if(opcion.nextBoolean())
                    esquiador.setMenuElegido("Empanadas \n");
                else
                    esquiador.setMenuElegido("Pizza Napolitana \n");

                this.sMostrador2.release();
                consolaLog("conf32" + esquiador.getNombre() + ", espera en mostrador 2" + "\n" +
                        "" + esquiador.getNombre() + ", retira su menu.");
            }
        }catch (InterruptedException ex)        {
            System.err.println("ERROR EN UN SEMAFORO AL INGRESAR A MOSTRADOR O DORMIR AL VISITANTE");
        }
    }

    public void entrarARetirarPostre(Esquiador esquiador) throws InterruptedException    {
        Random opcion = new Random();
        try {
            if(esquiador.getPostre()){
                this.sMostradorPostre.acquire();
                Thread.currentThread().sleep(20);
                if(opcion.nextBoolean())
                    esquiador.setPostreElegido("Flan \n");
                else
                    esquiador.setPostreElegido("Helado \n");

                this.sMostradorPostre.release();
                consolaLog("conf4"+esquiador.getNombre()+", espera ingresar al mostrador de postre." +"\n"+
                        ""+esquiador.getNombre()+", retira su postre.");
            }else {
                consolaLog("conf4" + esquiador.getNombre() + ", no consumirá postre.");
            }
        }catch (InterruptedException ex)   {
            System.err.println("ERROR EN UN SEMAFORO AL INGRESAR A MOSTRADOR O DORMIR AL VISITANTE");
        }
        consolaLog("El visitante "+esquiador.getNombre()+", se sienta. Disponibilidad: "+this.mesas);
    }

    public void terminarDeComer(Esquiador esquiador)    {
        try {
            this.mutex.acquire();
            this.mesas--;
            consolaLog("conf5" + esquiador.getNombre() + ", deja su mesa. Disponibilidad: " + (100 - this.mesas));
        }catch (InterruptedException ex){
            System.err.println("ERROR AL ADQUIRIR SEMAFORO MUTEX (PARA DESCONTAR LAS MESAS)");
        }
        this.mutex.release();
        consolaLog("conf5"+esquiador.getNombre()+",terminó de comer y se va.");
    }

    public int cantidadDeComensales(){
        //este log no lo estaba manejando nadie en Grafica
        //consolaLog("Cantidad de comensales: "+this.caja);
        return  this.caja;
    }

    /*notifica a la interfaz gráfica cambios que debe imprimir en los textArea*/
    private void consolaLog(String mjs){
        if(listener != null){
            this.listener.cadenaCambio(this, mjs);
        }
    }

    public void addMiEventListener (MiEventoListener listen){
        this.listener = listen;
    }


}
