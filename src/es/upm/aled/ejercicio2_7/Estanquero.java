package es.upm.aled.ejercicio2_7;

public class Estanquero extends Thread {
	private final String nombre= "Jenny";
	private Mesa mesa;
	
	public Estanquero(Mesa mesa) {
		this.mesa=mesa;
	}

	@Override
	public void run() {
		System.out.println("El estanquero " + nombre + " ya está en el estanco");
		try {
			while (true) {
				int tipo1 = (int) (Math.random() * 3);
				int tipo2 = (int) (Math.random() * 3);
				if (tipo1 != tipo2) {
					mesa.ponerIngredientes(tipo1, tipo2);
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
