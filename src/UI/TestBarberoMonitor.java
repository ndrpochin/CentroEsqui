package UI;

import java.util.ArrayList;
import java.util.Random;

public class TestBarberoMonitor {

    public static void main(String args[]) {
        // El monitor debe ser un hilo?
        // El monitor debe ser static?

        int numeroSillas = 5;
        Cabina pelu = new Cabina(numeroSillas);

        ArrayList profesArray = new ArrayList(5);
        for (int i = 0; i < 5; i++) {
            Profesor profesor = new Profesor(pelu);
            profesArray.add(profesor);
            new Thread(profesor).start();
        }


        int numCliente = 0;
        Random r = new Random();

        Thread t =  new Thread();
        try {
            Thread.sleep(r.nextInt(2000)); // cada 2000 llega un cliente
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int j = 0; j < 10 ; j++) {
            try {
                if (pelu.getCuantosClientes() < numeroSillas) {
                    Cliente nuevo = new Cliente(pelu, profesArray, ++numCliente);
                    t = new Thread(nuevo);
                    t.start();
                } else {
                    System.out.println("Llego un cliente y se fue");
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {                e.printStackTrace();            }


        }

        for (int i = 0; i < 10; i++) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
