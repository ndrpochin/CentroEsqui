package com.company;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class TestAerosillas {

	public static void main(String [] args){

		//AeroSilla [] coleccion = crearSillas(10);
		//prenderSillas(coleccion);
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(r.nextInt(17));
		}
	}

	public static AeroSilla [] crearSillas(int cantSillas){
		AeroSilla [] col = new AeroSilla[cantSillas];

		for (int i = 0; i < cantSillas; i++) {
			col[i] = new AeroSilla(cantSillas, i);
		}
		return col;
	}


	public static void prenderSillas(AeroSilla [] col){
		int i = 0;
		try {
			while (true){
				Thread.sleep(500);
				AeroSilla actual = col [i]; //actual deberia ser la silla a la que se suben los esquiadores
				if(i == 9)
					i = 0; //reinicio el ciclo.
				else
					i++;
				System.out.println("Silla Actual "+actual.getIdSilla());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
