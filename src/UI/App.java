package UI;

import com.company.*;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class App {
    public static void main(String [] args){
        //ObservadorTemporal ventana = new ObservadorTemporal();
        Marco ventana =  new Marco();

        Confiteria confiteria = new Confiteria(new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1));
        MedioElevacion medio = new MedioElevacion();

        String[] arregloNombres = {"MAGALI","NORBERTO","JOAQUIN","ANDRES","SANDRA","Ruben G.","Mariana D.","Romina N.",
                "Lucas N.","Roxana S.","Miguel S.","Gustavo P.","Ciro G.","Nelida L.","Ximena R.","Sandra L.","Alejandra U.","Julian G.","Ricardo R.","Miriam L."};

        ComplejoInvernal complejo = new ComplejoInvernal(confiteria, medio);
        Thread arregloVisi[]= new Thread[15];
        Esquiador v = null;

        complejo.horarioCierre();//Calculo horario de cierredel complejo
        //CREACIÓN DE VISITANTES
        Random r=new Random();
        Thread aux;
        for(int i=0; i<15;i++)//Creción de visitantes y .start
        {
            v = new Esquiador(arregloNombres[i+5],complejo, r.nextInt(2)+1);//el random es para el menu
            v.addObserver(ventana);
            aux= new Thread(v);
            arregloVisi[i] = aux;
            aux.start(); //v.start();
        }

        for(int i=0;i<15;i++)//.join a esquiadores
        {
            try {
                arregloVisi[i].join();
            } catch (InterruptedException ex) {
                System.out.println("ERROR CON JOIN DE VISITANTE");
            }
        }
        complejo.cierreDiario();

        //agregar los abservadores a los observers
        //confiteria.addObserver(ventana);
        //medio.addObserver(ventana);
        //complejo.addObserver(ventana);
    }

}

