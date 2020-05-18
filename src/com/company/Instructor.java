package com.company;

import UI.MiEventoListener;
import java.util.ArrayList;

public class Instructor implements  Runnable{

    private String nombre;
    private int numProfe;
    private MiEventoListener listener;
    private static CabinaInstructores cabina;
    private ComplejoInvernal complejoInvernal;
    private boolean duerme;
    private ArrayList clase;
    private boolean terminoClase;
    private boolean tipoClase;		//true=sky   false=snow

    public Instructor(String nuevoNombre, int num, CabinaInstructores nuevaCabina, ComplejoInvernal complejo){
        this.nombre = nuevoNombre;
        this.numProfe = num;
        cabina = nuevaCabina;
        duerme = true;
        clase =  new ArrayList();
        terminoClase = false;
        tipoClase = true;
        complejoInvernal = complejo;
    }
    public void darClaseSky(){
        tipoClase = true;
    }
    public void darClaseSnow(){
        tipoClase = false;
    }

    public void agregarAlumnos(ArrayList arr){
        clase.addAll(arr);
    }

    public void agregarAlumno(Esquiador es){
        clase.add(es);
    }

    public void quitarAlumno(){
        terminoClase = true;

        while(clase.size() > 0){
            synchronized (clase.get(0)) {
                Esquiador es = (Esquiador) clase.get(0);
                //				System.out.println(nombre+" despierta al alumno --> "+es.name());
                es.notify();        //despierto el hilo Esquiador para que se vaya de la clase
                consolaLog("ins1 se retira "+es.getNombre()+" porque terminó la clase");
                clase.remove(0);
                //Log("despertando a "+es.getNombre());
            }
        }
    }

    public boolean terminoClase(){
        return terminoClase;
    }

    public void run(){

        while(complejoInvernal.complejoAbierto() & !cabina.seCompletoLaClase()){
            //if(){
                dormir();
            //}else{
                try {
                    if(tipoClase)
                        cabina.darClaseSky(this);
                    else
                        cabina.darClaseSnow(this);

                    terminoClase = false;           //reinicio para que pueda armar otra clase.
                    consolaLog("ins2"+nombre +" se toma un descanso");

                    Thread.sleep(350);
                    this.duerme = true;
                    cabina.nuevoProfeDisponible();      //Tiene un descanso antes de armar otra clase.

                } catch (InterruptedException e) {   e.printStackTrace();     }
            }
        //}
        System.out.println("Instructor se retira porque cerró el complejo");
    }

    public boolean duerme() {
        return (duerme);
    }

    public int verNumProfe(){
        return numProfe;
    }
    public void setNumProfe(int numProfe) {
        this.numProfe = numProfe;
    }

    public String name(){
        return nombre;
    }
    public void dormir(){
        synchronized (this) {
            try {
                //Log(">>profe "+nombre+" durmiendo<<");
                consolaLog("ins3"+nombre+" durmiendo");
                duerme = true;
                wait();
            } catch (InterruptedException e) {		e.printStackTrace();	}
        }
        duerme = false;
    }


    /*notifica a la interfaz gráfica cambios que debe imprimir en los textBoxs*/
    private void consolaLog(String mjs){
        if(listener != null){
            this.listener.cadenaCambio(this, mjs);
        }
    }
    public void addMiEventListener (MiEventoListener listen)
    {
        this.listener = listen;
    }

    private void Log(String msj) {
        System.out.println(msj);
    }
}

