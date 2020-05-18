package com.company;

import UI.MiEventoListener;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Medio  {

    private int numMedio;
    private Molinete molinetes []; //numMedio y molinetes [] se corresponden en que numMedio determina la cantidad de molinetes de ese medio
    private int cantVisitantes;    //acumula la cantidad de visitantes totales del medio.
    private AeroSilla [] colSillas;
    private Semaphore mutex;
    private MiEventoListener listener;
    private int lugaresAeroSilla;
    private int turnoDeSilla;  //corresponde a la silla que está actualmente disponible para que suban pasajeros

    //la cantidad de molinetes para un medio es igual a la cantidad de lugares por silla

    public Medio(int idMedio, int cantLugaresxSilla) {

        this.numMedio = idMedio;    //id
        this.cantVisitantes = 0;
        this.mutex = new Semaphore(1);      //Semaforo para el visitante.
        this.lugaresAeroSilla = cantLugaresxSilla;
        crearMolinetes();
        crearAerosillas(lugaresAeroSilla);
        turnoDeSilla = 0;
    }

    public int verNumMedio(){
        return numMedio;
    }

    private void crearAerosillas(int lugaresAeroSilla){
        int cantSillas = verCantidadSillas();
        this.colSillas = new AeroSilla [cantSillas];

        for(int idSilla = 0; idSilla < cantSillas; idSilla++){    //hay que cargar las 20 sillas
            this.colSillas[idSilla] = new AeroSilla(lugaresAeroSilla, idSilla);
            //aprovecho el contador del for para usaro de id de cada aerosilla.
        }
    }

    /*Dependiendo del Medio, seteamos la cantidad de sillas
    * como precondicion se espera que lugaresAeroSilla sea entre 1 y 4; sino no crea ninguna silla*/
    private int verCantidadSillas(){
        int cantidad = 0;
        switch (this.lugaresAeroSilla){
            case 1: //Medio 1 tiene 75 sillas (de 1 lugar cada una)
                cantidad = 75;      break;
            case 2: //Medio 2 tiene dos lugares por silla
                cantidad = 60;      break;
            case 3:
                cantidad = 45;      break;
            case 4:
                cantidad = 30;      break;
        }
        return  cantidad;
    }
    private void crearMolinetes(){
        this.molinetes = new Molinete[this.lugaresAeroSilla];   //todos los Medios tiene N molinetes de acceso

        for ( int i=0; i<this.lugaresAeroSilla; i++  ){
            this.molinetes[i] = new Molinete(new Semaphore(lugaresAeroSilla)); //
        }   //Molinete [0] tiene 1 solo permiso
             //Molinete [1] tiene 2 permisos (2 molintetes)
    }

    private void prenderSillas() {
        /*prenderSillas hace rotar permanentemente el numero que identifica a la silla que está disponible para subir a los esquiadores*/
        int cantidad = verCantidadSillas();
        try {
            for (int i = 0; i < cantidad; i++) { //este FOR se debe adecuar a la cantidad de sillas de cada medio(varia)
                Thread.currentThread().sleep(510); //Se tardan 30 segundos(510) entre silla y silla.
                //Si el tiempo entre silla y silla es muy poco, no se juntan suficientes esquiadores para llenar una silla de 4 lugares.

                if (turnoDeSilla == (cantidad-1))
                    turnoDeSilla = 0;   //reinicio el ciclo.
                else
                    turnoDeSilla++;

               // System.out.println("medio  "+this.numMedio+"    silla   "+turnoDeSilla+"      La silla cambió en "+ System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ascenso (Esquiador esquiador) throws InterruptedException {

        int contAerosilla = 0;
//        System.out.println("entro al metodo ascenso");

        if((esquiador.getComplejo().complejoAbierto())) {
            consolaLog(esquiador.getMedio()+""+esquiador.getNombre()+" ENTRO al Medio de Elevacion");
//          El esquiador entra y pasa por el molinete
            Thread.sleep(10);

            entrarAMolinete(esquiador);
            Thread.sleep(10);
            //salirDeMolinete(esquiador);

            boolean estaSentado = false;
            while (!estaSentado) {
                synchronized (this) {
                    estaSentado = this.colSillas[turnoDeSilla].sentarseMonitor();
                    //entre estas dos lineas puede llegar a cambiar el valor de "turnoDeSilla" por eso se sincroniza
                    //System.out.println("esta sentado? "+estaSentado);
                    if (estaSentado) {
                        esquiador.setNumSilla(turnoDeSilla);
                        consolaLog(esquiador.getMedio()+""+esquiador.getNombre()+" se subió a la aerosilla "+ this.turnoDeSilla);
                    }
                }   //el metodo chequea si hay lugar, sino, va a esperar a la próxima silla.
                //sincronizado para que turnoDeSilla no cambie de valor entre "Sentarse" y "setNumSilla"
            }
            int aux = verCantidadSillas()/2;    //Si el medio tiene 30 sillas, aux = 15. Que será el tiempo que tarde en llegar a la cima.
            Thread.sleep(aux * 340);       //Tiempo de ascenso

            int sillaCima = esquiador.verNumSilla();
            colSillas[sillaCima].llegarALaCimaMonitor(); //libera los lugares de la aerosilla

            consolaLog(esquiador.getMedio()+""+esquiador.getNombre()+" logró llegar a la cima");
            esquiar(esquiador);
        }else{
//          System.out.println("EL COMPLEJO CERRO, NO SE PUEDE SUBIR A NINGUN MEDIO");
            consolaLog("EL COMPLEJO CERRO, NO SE PUEDE SUBIR A NINGUN MEDIO");
        }
    }

    /*El esquiador desciende en un tiempo random, tiene que haber un piso mínimo. Por ejemplo el equivalente a 10 min REALES*/
    public void esquiar(Esquiador esquiador) throws InterruptedException {
        Random n = new Random();
        consolaLog(esquiador.getMedio()+""+esquiador.getNombre()+ " descendiendo...");
        Thread.sleep(1000 *n.nextInt(6)); //simula el tiempo de descenso
    }

    public void entrarAMolinete(Esquiador esquiador) throws InterruptedException    {
        long ini = System.currentTimeMillis();
        int numMolinete = 0;
        //String mensaje = "";
        Random r = new Random();
        try {
            this.mutex.acquire();
            //numMedio es CERO, no hay opciones de molinete, hay uno solo.
            if(numMedio > 0)
                numMolinete = r.nextInt(numMedio);

            Thread.sleep(15);
            consolaLog(esquiador.getMedio()+""+esquiador.getNombre()+", ENTRO a molinete "+numMolinete);
            Molinete moli = this.molinetes[numMolinete]; //el esquiador entra por cualquiera de los molinetes del medio
            //recordamos: numMedio es el "id" del Medio y a la vez me dice: la cantidad de molinetes y de lugares en las sillas que hay.
            this.cantVisitantes++;  //aumento el clientesEnEspera de visitantes

            moli.pasarTarjeta();    //el visitante debe pasar la tarjeta
            salirDeMolinete(esquiador);

            this.mutex.release();   //libero semaforo
        } catch (InterruptedException ex) {
            System.err.println("ERROR AL ADQUIRIR SEMAFORO EN MEDIO: "+this.numMedio);
        }
        /*long fin = System.currentTimeMillis();
        System.out.println("tiempo de entrar al molinete    "+(fin-ini));*/
    }

    public void salirDeMolinete(Esquiador esquiador) throws InterruptedException    {
        consolaLog(esquiador.getMedio()+""+esquiador.getNombre()+", salió del molinete ");
        //Tiempo que tarda visitante en pasar por molinete
        Thread.sleep(100);

    }

    public int personasPorMedio()  {
        int cant = 0;
        for(int i = 0; i < lugaresAeroSilla; i++)  {
            cant = cant + this.molinetes[i].personasPorLector();
        }
        return cant;
    }

    /*notifica a la interfaz gráfica cambios que debe imprimir en los textArea*/
    private void consolaLog(String mjs){
        if(listener != null){
            this.listener.cadenaCambio(this, mjs);
        }
    }
    public void addMiEventListener (MiEventoListener listen)
    {
        this.listener = listen;
    }

    public static class HiloMedio extends  Thread{

        Medio numMedio;
        long horaCierre;

        public HiloMedio(Medio m, long hCierre){
            numMedio = m;
            horaCierre = hCierre;
        }
        public void run(){
            while (System.currentTimeMillis()< horaCierre ){
                this.numMedio.prenderSillas();
            } //infinitamente va a estar llamando al ciclo de girar las aerosillas
            //System.err.println("over");
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
