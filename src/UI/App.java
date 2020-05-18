package UI;

import com.company.*;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class App {
    public static void main(String [] args){

        Marco ventana =  new Marco();

        Confiteria confiteria = new Confiteria(new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1));
        CabinaInstructores cabina = new CabinaInstructores();
        Medio [] medio = crearMediosElevacion(ventana);

        confiteria.addMiEventListener(ventana);
        cabina.addMiEventListener(ventana);

        ComplejoInvernal complejo = new ComplejoInvernal(confiteria, medio, cabina);
        complejo.addMiEventListener(ventana);

        Thread arregloEsquiadores []= new Thread[150];
        Esquiador v = null;
        Thread [] hilosInstructores = new Thread [5];
        Instructor [] arregloInstructores = crearInstructores(cabina, complejo, hilosInstructores, ventana);

        Random r = new Random();
        Thread aux;
        for(int i = 0; i < 150; i++){      //Creción de visitantes y .start
//            v = new Esquiador(arregloNombres[i+5],complejo, r.nextInt(2)+1);//el random es para el textFieldMenu
            v = new Esquiador("Juan", complejo, i, cabina, arregloInstructores);//el random es para el textFieldMenu

            v.addMiEventListener(ventana);
            aux = new Thread(v);
            arregloEsquiadores[i] = aux;
            try {
                Thread.sleep(1000*r.nextInt(5)); //no lanzo todos los hilos en simultaneo
            } catch (InterruptedException e) {                e.printStackTrace();            }
            aux.start(); //v.start();
        }

        complejo.cierreDiario();

    } //fin del main

    private static Instructor[] crearInstructores(CabinaInstructores cabina, ComplejoInvernal complejo, Thread [] hilos, Marco vent){
        Instructor [] array = new Instructor [5];

        Instructor profeBela = new Instructor("Bela" ,0 , cabina, complejo);
        profeBela.addMiEventListener(vent);
        Thread belaHilo =  new Thread(profeBela);

        Instructor profePablo = new Instructor("Pablo" ,1 , cabina, complejo);
        Thread pabloHilo =  new Thread(profePablo);
        profePablo.addMiEventListener(vent);

        Instructor profePaco = new Instructor("Paco" ,2 , cabina, complejo);
        Thread pacoHilo =  new Thread(profePaco);
        profePaco.addMiEventListener(vent);

        Instructor profeSanyo = new Instructor("Sanyo" ,3 , cabina, complejo);
        Thread sanyoHilo =  new Thread(profeSanyo);
        profeSanyo.addMiEventListener(vent);

        Instructor profeStupa = new Instructor("Stupa" ,4 , cabina, complejo);
        Thread stupaHilo =  new Thread(profeStupa);
        profeStupa.addMiEventListener(vent);

        array[0] = profeBela;	array[1] = profePablo;	array[2] = profePaco; array[3] = profeSanyo;	array[4] = profeStupa;
        hilos[0] = belaHilo;	hilos[1] = pabloHilo;	hilos[2] = pacoHilo; hilos[3] = sanyoHilo;	hilos[4] = stupaHilo;
        belaHilo.start(); pabloHilo.start(); pacoHilo.start(); sanyoHilo.start(); stupaHilo.start();

        return array;
    }

    private  static  Medio[] crearMediosElevacion(Marco vent){
        Medio [] mediosDeElev = new Medio[4];
        for(int i = 0; i < 4; i++){

            mediosDeElev[i] = new Medio(i,1+i); //(i+1)s
            mediosDeElev[i].addMiEventListener(vent);
            //El Medio[0] tiene silla de solo 1 pasajero
            //El Medio[3] tiene sillas de 4 pasajeros
        }
        return mediosDeElev;
    }

}

