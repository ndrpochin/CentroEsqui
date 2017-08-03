package UI;

import java.util.Observable;
import java.util.Random;

public class Observado extends Observable{

        private Delegado del;
    public void Cambiar(){
        del = new Delegado();

        Random n = new Random();
        int aux = n.nextInt(3);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String mensaje ="";
        if(aux==0)
            mensaje ="> "+ del.getCadena() +" *Exito* \n";
        else
            mensaje ="> "+ del.getNumero() +" Fracaso\n";

        setChanged();
        notifyObservers(mensaje);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mensaje = "$$$ \n";
        setChanged(); notifyObservers(mensaje);
    }
}
