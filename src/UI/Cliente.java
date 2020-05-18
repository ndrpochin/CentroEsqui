package UI;

import java.util.ArrayList;

public class Cliente implements Runnable{

    private Cabina cab;
    private Profesor profesor;
    private int posInList;
    private boolean enEspera;
    private ArrayList profesores; //los conoce a todos

    /*public Cliente (Cabina cab, Profesor b, int numCliente){

        Log("C -- Bienvenido, nuevo cliente");
        profesor = b;
        enEspera = true;
        this.cab = cab;
        posInList = numCliente;
        cab.llegoCliente();
        //Log("C -- Hay "+ cab.getCuantosClientes() + " clientes esperando");
    } */
    public Cliente (Cabina cab, ArrayList b, int numCliente){

        Log("C -- Bienvenido, nuevo cliente");
        profesores = b;
        enEspera = true;
        this.cab = cab;
        posInList = numCliente;
        cab.llegoCliente();
        //Log("C -- Hay "+ cab.getCuantosClientes() + " clientes esperando");
    }
    public Profesor elegirProfe(){
        Profesor aux = null;            //devuelvo null si no habia ningun elemento disponible
        if(profesores.size() > 0) {
            aux = (Profesor) profesores.get(0); //obtengo el primer profe
            profesores.remove(0);       //lo saco de la lista, para tener claro cuantos tengo disponible
        }
        return aux;
    }
    public void dormirProfe(Profesor p){
        profesores.add(p);
        Log("profe dormido con exito");
    }
    @Override
    public void run() {
        try {
            this.cab.tomarClase(this); //entra a la clase y se va a quedar en espera hasta que llegue el 4to
            Log("C -- terminó la clase y el alumno se fue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void despertarProfe(Profesor profesor) {

        Log("C -- Despertando al profesor");
        synchronized (profesores) {
            if (profesores.size() > 0) {
                Profesor aux = (Profesor) profesores.get(0); //obtengo el primer profe
                profesores.remove(0);       //lo saco de la lista, para tener claro cuantos tengo disponible
                profesor.notify();
            } else {
                Log("No hay profes disponibles para despertar");
            }
        }
    }
    public void Log(String mensaje){
        System.out.println(mensaje);
    }

}

