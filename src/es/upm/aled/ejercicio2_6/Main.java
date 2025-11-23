package es.upm.aled.ejercicio2_6;

public class Main {
	private static final int CAPACIDAD_MARMITA = 5;
	private static final int NUM_CANIBALES = 4;

	public static void main(String[] args) {

		Marmita marmita = new Marmita(CAPACIDAD_MARMITA);
		Cocinero cocinero = new Cocinero(marmita);
		Thread t = new Thread(cocinero);
		t.start();

		for (int i = 0; i < NUM_CANIBALES; i++) {
			Canibal canibal = new Canibal(marmita, "Canibal" + (i + 1));
			Thread f = new Thread(canibal);
			f.start();
		}
	}
}
