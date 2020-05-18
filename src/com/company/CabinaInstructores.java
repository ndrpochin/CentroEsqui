package com.company;

import UI.MiEventoListener;

import java.util.ArrayList;

public class CabinaInstructores{

    private MiEventoListener listener;
    private int cantAlumnosEsperandoSky;
    private int cantAlumnosEsperandoSnow;

    private int contadorClasesSky;
    private int contadorClasesSnow;
    private int cantProfesDisponibles;
    private int turnoProfe;
    private int numOrden;
    private int numOrdenSnow;

    private ArrayList listaSky;
    private ArrayList listaSnow;


    public CabinaInstructores() {
        //numeroDeAlumnosParaUnaCLase = 4;
        cantAlumnosEsperandoSky = 0;
        cantAlumnosEsperandoSnow = 0;
        cantProfesDisponibles = 5;
        turnoProfe = 0;
        numOrden = 1;
        numOrdenSnow = 1;
        listaSky =  new ArrayList();
        listaSnow =  new ArrayList();
        contadorClasesSky = 0;
        contadorClasesSnow = 0;

    }
    /**True si hay 4 alumnos y es el turno del profe, va a despertar el hilo instructor
     * en otro caso seguirá durmiendo*/

    public synchronized int getContadorClasesSky(){
        return contadorClasesSky;
    }
    public synchronized int getContadorClasesSnow(){
        return contadorClasesSnow;
    }

    public synchronized boolean seCompletoLaClase(){
        Log("seCompletoLaClase "+ cantAlumnosEsperandoSky);
        return(cantAlumnosEsperandoSky == 4 || cantAlumnosEsperandoSnow == 4);
    }

    public synchronized int verTurno(){
        return turnoProfe;
    }

    public synchronized void darClaseSky (Instructor ins) throws InterruptedException {
        contadorClasesSky++;
        cantAlumnosEsperandoSky = 0;		//resetea los valores para que se forme otra clase
        try {
            long fin = System.currentTimeMillis() + 40000; //la clase dura 40 minitos (40.000 ms)
            //Log("*** "+ins.name()+" arrancando clase...	"+fin);
            consolaLog("sky1"+ins.name()+ " arrancando clase");
            while(fin > System.currentTimeMillis())
                wait(10000); //como nada interrumpe este wait, lo temporizamos para que en algun momento salga

        } catch (InterruptedException e) {			e.printStackTrace();		}
        //Log("<<<< "+ins.name()+ " despidiendo alumnos");
        ins.quitarAlumno();     //despierto a todos los esquiadores que tenía en la clase
        consolaLog("skybye");
        notifyAll();

    }
    public synchronized void darClaseSnow(Instructor ins) throws InterruptedException {
        contadorClasesSnow++;
        cantAlumnosEsperandoSnow = 0;		//resetea los valores para que se forme otra clase
        try {
            long fin = System.currentTimeMillis()+40000;    //la clase dura 40 minitos (40.000 ms)
            //Log("SNOW "+"*** "+ins.name()+" arrancando clase...	"+fin);
            consolaLog("snow1"+ins.name()+ " arrancando clase");
            while(fin > System.currentTimeMillis())
                wait(10000);    //como nada interrumpe este wait, lo temporizamos para que en algun momento salga

        } catch (InterruptedException e) {			e.printStackTrace();		}
        //Log("SNOW "+"<<<< "+ins.name()+" Terminó la clase en "+System.currentTimeMillis());
        //Log("SNOW "+"<<<< "+ins.name()+ " despidiendo alumnos");
        ins.quitarAlumno();
        consolaLog("snowbye");
        notifyAll();        //despierto a todos los esquiadores que tenia en la clase.

    }
    public synchronized void nuevoProfeDisponible(){
        this.cantProfesDisponibles++;
        consolaLog("profe++");
    }

    public synchronized void llegaSky (Esquiador es) throws InterruptedException {

        long horaDeEntrada = System.currentTimeMillis();
        long horaSalida = horaDeEntrada + 20000;     //si en 20 min no se armó el grupo, se irá el esquiador
        boolean seCansoDeEsperar = false;

        if(cantAlumnosEsperandoSky == 4){
            wait();     //queda en espera un momento porque se está armando la clase/despertando al profe
        }

        if(cantProfesDisponibles > 0){

            cantAlumnosEsperandoSky++;
            // Log("->SKY llego un alumno, van "+cantAlumnosEsperandoSky);
            listaSnow.add(es);
            es.asignarOrden(cantAlumnosEsperandoSky);
            consolaLog("sky++");
            notifyAll();

            while(!seCansoDeEsperar & cantAlumnosEsperandoSky != 4){   //hasta que lleguen 4 o se canse de esperar el primer esquiador
                if(es.verOrden() == 1){
                    wait(1000);  //cuando pase el tiempo sin armar una clase el primer alumno va a salir y despertar a los hilos en espera por esa clase
                    if(horaSalida < System.currentTimeMillis())
                        seCansoDeEsperar = true;
                }else{
                    wait(); //los demás hilos esperan acá
                }
            }

            if(seCansoDeEsperar){
                consolaLog("sky2 El esquiador "+es.getNombre()+" se cansó de esperar y desarma el grupo de sky");
                limpiarListaNoHayClase(listaSky); //liberar los hilos
                cantAlumnosEsperandoSky = 0;
                notifyAll();
            }else{
                while(es.verOrden() != numOrden){
                    wait();	        //los voy despertando en orden
                }
                numOrden++;
                Instructor ins = es.buscarProfesor(turnoProfe);

                if(ins!= null){
                    Log(es.getNombre()+ " registrandose en la clase del profe "+ins.name());

                    if(es.verOrden() % 4 == 0){
                        ins.agregarAlumnos(listaSky);
                        ins.darClaseSky();      //asignar al instructor el tipo de clase va a dar.
                        es.despertarProfesor(ins);
                        limpiarLista(listaSky); //solo eliminar los elementos de la lista -no despierta a nadie-
                        cantProfesDisponibles--;
                        consolaLog("profesky--");

                        if(turnoProfe == 4)
                            turnoProfe = 0;
                        else
                            turnoProfe++;

                        numOrden = 1;
                    }
                    notifyAll();

                    while(!ins.terminoClase()){
                        wait();
                    }
                }else{
                    Log(turnoProfe+" turno profe ");
                    Log(cantProfesDisponibles+" cantidad profes disponibles");
                    Log("profe NULL para alumno "+ es.getNombre());	}
            }
        }else{
            //Log("No hay profes disponibles, el esquiador se va!");
            consolaLog("sky3 No hay profes disponibles, el esquiador se va!");
        }
    }

    public synchronized void llegaSnow(Esquiador es) throws InterruptedException{
        long horaDeEntrada = System.currentTimeMillis();
        long horaSalida = horaDeEntrada + 20000; //si en 20 min no se armó el grupo, se irá el esquiador
        boolean seCansoDeEsperar = false;

        if(cantAlumnosEsperandoSnow == 4){
            wait();     //queda en espera un momento porque se está armando la clase/despertando al profe
        }

        if(cantProfesDisponibles > 0){
            cantAlumnosEsperandoSnow++;
            //Log("SNOW- llego un alumno, van "+cantAlumnosEsperandoSnow);
            listaSnow.add(es);
            es.ordenarSnow(cantAlumnosEsperandoSnow);
            consolaLog("snow++");
            notifyAll();

            while(!seCansoDeEsperar & cantAlumnosEsperandoSnow != 4){ //hasta que lleguen 4
                if(es.verOrdenSnow() == 1){
                    wait(1000);      //cuando pase este tiempo va a salir y tiene que desarmar todo
                    if(horaSalida < System.currentTimeMillis())
                        seCansoDeEsperar = true;
                }else{
                    wait();
                }
            }

            if(seCansoDeEsperar){
                //Log("SNOW-El esquiador "+es.getNombre()+" se cansó de esperar por la clase de SNOW");
                consolaLog("snow2 El esquiador "+es.getNombre()+" se cansó de esperar y desarma el grupo de snowboard");
                limpiarListaNoHayClase(listaSnow); //liberar los hilos
                cantAlumnosEsperandoSnow = 0;
                notifyAll();
            }else{
                while(es.verOrdenSnow() != numOrdenSnow){
                    wait();	//los voy despertando por orden
                }
                numOrdenSnow++;
                Instructor ins = es.buscarProfesor(turnoProfe);

                if(ins!= null){
                    Log("SNOW "+es.getNombre()+ " registrandose en la clase del profe "+ins.name());

                    if(es.verOrdenSnow() % 4 == 0){
                        ins.agregarAlumnos(listaSnow);
                        ins.darClaseSnow();         //Asignar al instructor clase de snowboard
                        es.despertarProfesor(ins);
                        limpiarLista(listaSnow);    //solo eliminar los elementos de la lista -no despertar-
                        cantProfesDisponibles--;
                        consolaLog("profesnow--");
                        if(turnoProfe == 4)
                            turnoProfe = 0;
                        else
                            turnoProfe++;

                        numOrdenSnow = 1;
                    }
                    notifyAll();

                    while(!ins.terminoClase()){
                        wait();
                    }
                }else{
                    Log("SNOW "+turnoProfe+" turno profe ");
                    Log("SNOW "+cantProfesDisponibles+" cantidad profes disponibles");
                    Log("SNOW "+"profe NULL para alumno "+ es.getNombre());	}
                //			Log("Terminó la clase, se va "+es.name());
            }
        }else{
            //Log("SNOW "+"No hay profes disponiblesu, chau!");
            consolaLog("snow3 No hay profes disponibles, el esquiador se va!");
        }
    }


    private void limpiarListaNoHayClase(ArrayList array){
        //Log("No hay clase - se limpia la lista SKY");
        Esquiador esq = null;
        while(array.size() > 0){
            synchronized (array.get(0)) {
                esq = (Esquiador) array.get(0);
                esq.notify(); 	//despierto el hilo esquiador para que se vaya de la espera por una clase
                //Log("despertando a "+esq.getNombre()+"");
                array.remove(0);
            }
        }
    }
    private void limpiarLista(ArrayList array){
        Esquiador esq = null;
        while(array.size() > 0){
            esq = (Esquiador) array.get(0);
            Log("limpiando de la lista a "+esq.getNombre()+"");
            array.remove(0);
        }
    }

    /*notifica a la interfaz gráfica cambios que debe imprimir en los textArea*/
    private void consolaLog(String mjs){
        if(listener != null){
            this.listener.cadenaCambio(this, mjs);
        }
    }
    private void Log(String msj) {
        System.out.println(msj);
    }

    public void addMiEventListener (MiEventoListener listen){
        this.listener = listen;
    }
}

















