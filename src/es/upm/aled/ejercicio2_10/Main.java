package es.upm.aled.ejercicio2_10;

public class Main {

	public static void main(String[] args) {
		PuenteMonitor monitor = new PuenteMonitor();
		try {

			for (int i = 0; i < 15; i++) {
				Vehiculo vehiculo = new Vehiculo(monitor, "V" + (i + 1), (int) (Math.random() * 2500), false);
				vehiculo.start();
				Thread.sleep((long) (Math.random() * 2500));
			}
			Vehiculo ambulancia = new Vehiculo(monitor, "AMB-Urg", 1500, true);
			ambulancia.start();
			Thread.sleep((long) (Math.random() * 2500));

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
