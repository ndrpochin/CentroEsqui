package com.company;

import UI.MiEventoListener;

public class AeroSilla{


    private int idSilla;
    private int lugaresDisponibles;
    private int numLugares;
    private MiEventoListener listener;

    public  AeroSilla(int lugaresSilla, int numID){
        this.idSilla = numID;
        this.lugaresDisponibles = lugaresSilla;
        this. numLugares = lugaresSilla;
    }


    public int getIdSilla()   {
        return this.idSilla;
    }

    //El mensaje que devuelve debe ser administrado por el llamador
    //El metodo espera unos 85 miliseg para que se suban los esquiadores que puedan

    public synchronized boolean sentarseMonitor(){
                boolean band = false;
        try {
            if(lugaresDisponibles == 0){ //no hay lugar
                band = false;
            }else{
                lugaresDisponibles--; //ocupo un lugar
                band = true;
            }
        }catch (Error e){   System.err.println("ERROR AL ADQUIRIR SEMAFORO DE LOS LUGARES DE LA AEROSILLA"); }
        return band;
    }
    public synchronized void llegarALaCimaMonitor() {
        lugaresDisponibles = numLugares;
    }

    public void addMiEventListener (MiEventoListener listen)    {
        this.listener = listen;
    }

}
