package es.upm.aled.ejercicio2_8;

public class Estanquero extends Thread {
	private final String nombre = "Jenny";
	private Mesa mesa;

	public Estanquero(Mesa mesa) {
		this.mesa = mesa;
	}

	@Override
	public void run() {
		System.out.println("El estanquero " + nombre + " ya está en el estanco");
		try {
			while (true) {
				int tipo1 = (int) (Math.random() * 3);
				mesa.ponerIngrediente(tipo1);
			}

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}