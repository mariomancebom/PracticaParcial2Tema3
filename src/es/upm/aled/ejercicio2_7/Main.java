package es.upm.aled.ejercicio2_7;

public class Main {

	private static int fumadores = 3;

	public static void main(String[] args) {

		Mesa mesa = new Mesa(fumadores);
		Thread estanquero = new Estanquero(mesa);
		estanquero.start();
		
		for(int i=0;i<fumadores;i++) {
			Thread fumador=new Fumador(mesa,i,"F"+(i+1));
			fumador.start();
		}

	}
}