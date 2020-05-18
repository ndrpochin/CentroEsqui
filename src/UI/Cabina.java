package UI;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cabina { //implementaba runnable
    //clase Cabina es la que controla el acceso de los clientes a las sillas

    private int contadorDeGiles;        //Contiene numero de sillas
    private int clientesEspera;
    private boolean masterChair; //silla de barbero
    private int profesDurmiendo;
    //constructor de la clase

    /*deberé crear 5 profesores antes, en el main, y dejarlos todos durmiendo*/
    Cabina(int num){
        contadorDeGiles = 0;
        masterChair = false; //false = desocupada.-
        clientesEspera = 0;
        profesDurmiendo = 5;
    }


    //metodo solo para los clientes
    public synchronized void tomarClase(Cliente cliente) throws InterruptedException {
        if(profesDurmiendo > 0){
            contadorDeGiles++; //
            Log("giles en espera "+contadorDeGiles);
            if(contadorDeGiles != 3){
                wait(); //dejo al cliente en espera hasta que se junten los 4, o el profe notifique.
            }else{
                Profesor aux = cliente.elegirProfe();
                if(aux != null){
                    cliente.despertarProfe(aux);
                }
                profesDurmiendo--;
                wait();                 //al 4to gil tambien lo dejo en espera para que el profesor lo despierte después
                this.contadorDeGiles = 0;
                cliente.dormirProfe(aux);
            }
        }else {
            Log("No habia profesores disponibles, asi que el cliente se tomó el palo");
        }
    }
    private void despertarVago(){

    }

    public synchronized void darClase() throws InterruptedException {
        Log("P ** Desperto el Profe");
        Thread.sleep(5000);
        Log("P ** ya dio la clase");
        Thread.sleep(1500);
        notifyAll(); //notifica a todos para que se tomen el palo.
    }


    /*Este solo lo usa el Cliente*/
    public synchronized void cortarPelo(int posInList){
        try {
            ocuparSilla(); //masterChair = true;
            clientesEspera--;               //quitar 1 cliente de la lista de espera

            Log("+++Profesor cortando el pelo...");
            Thread.sleep(3000);  //corta el pelo...

            Log("+++Terminó de cortar el pelo, chau!");
            Log("+++Clientes esperando: "+ clientesEspera);
            Log("");
            desocuparSilla();

        } catch (InterruptedException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized int getCuantosClientes(){
        return clientesEspera;
    }
    public synchronized void  llegoCliente(){
        this.clientesEspera++;
    }
    public synchronized boolean sillaOcupada(){
        return this.masterChair;
    }
    public synchronized void ocuparSilla() throws InterruptedException {
        Thread.sleep(200); this.masterChair = true;
    }
    public synchronized void desocuparSilla() throws InterruptedException {
        Thread.sleep(200);
        this.masterChair = false;
    }

    /*
    public void run() {
        while(true){
                        //solo necesita estar vivo para que puedan acceder a la masterChair.
        }
    }*/

    private void Log(String mensaje){
        System.out.println(mensaje);
    }

}
