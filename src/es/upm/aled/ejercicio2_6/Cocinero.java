package es.upm.aled.ejercicio2_6;

public class Cocinero implements Runnable {

	public final String nombre = "Official Chef";
	public final Marmita marmita;

	public Cocinero(Marmita marmita) {
		this.marmita = marmita;
	}

	@Override
	public void run() {
		System.out.println("El cocinero está cocinando");
		try {
			while (true) {
				marmita.rellenar();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
