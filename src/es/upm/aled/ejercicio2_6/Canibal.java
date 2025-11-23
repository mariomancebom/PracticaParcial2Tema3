package es.upm.aled.ejercicio2_6;

public class Canibal implements Runnable {
	private final Marmita marmita;
	private final String nombre;

	private static final int MAX_COMIDAS = 5;
	private int comidasConsumidas = 0;

	public Canibal(Marmita marmita, String nombre) {
		this.marmita = marmita;
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println("El canibal " + nombre + " se ha sentado a comer");
		try {
			while (comidasConsumidas < MAX_COMIDAS) {
				marmita.comer();
				comidasConsumidas++;
				Thread.sleep((long) (Math.random() * 500));
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();

		}
	}
}
