package es.upm.aled.ejercicio2_7;

public class Fumador extends Thread {

	private String nombre;
	private int LoQueTiene;
	private Mesa mesa;

	public Fumador(Mesa mesa, int LoQueTiene, String nombre) {
		this.mesa = mesa;
		this.LoQueTiene = LoQueTiene;
		this.nombre = nombre;
	}

	@Override
	public void run() {
		try {
			while (true) {
				mesa.cogerIngredientes(LoQueTiene);
				Thread.sleep((long) (Math.random() * 500));// Simulacion de tiempo de fumar
				System.out.println("El fumador "+ nombre+ " está fumando");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}