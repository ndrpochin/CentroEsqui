package UI;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Profesor implements Runnable {

    private boolean duerme;
    private Random r;
    private Cabina m;

    public Profesor(Cabina cabina){
        duerme = false; //arranca dormido
        r = new Random();
        m = cabina;
    }

    public void dormir(){
        try{
            Log("P ** El Profesor esta durmiendo");
            synchronized(this){
                wait();         // El barbero se pone a dormir hasta que lo despierte un cliente
            }
            m.darClase();
        } catch (InterruptedException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean estaDurmiendo(){
        return this.duerme;
    }
    @Override
    public void run() {

        while(true){
            dormir();
        }
    }

    public void Log(String mensaje){
        System.out.println(mensaje);
    }

}

