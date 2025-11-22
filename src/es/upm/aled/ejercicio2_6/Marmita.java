package es.upm.aled.ejercicio2_6;

public class Marmita {

	private final int CAPACIDAD_MAX;
	private int raciones;
	private boolean cocineroAvisado = false;

	public Marmita(int capacidad) {
		this.CAPACIDAD_MAX = capacidad;
		this.raciones = capacidad;// Al principio está llena
	}

	public synchronized void comer() throws InterruptedException {
		while (raciones == 0) {
			if (!cocineroAvisado) {
				notifyAll();
				cocineroAvisado = true;
			}
			wait();
		}
		raciones--;
		System.out.println("El canibal ha comido, raciones restantes: " + raciones);
	}

	public synchronized void rellenar() throws InterruptedException {
		if (raciones > 0 || !cocineroAvisado) {
			wait();
		}
		Thread.sleep((long) (Math.random() * 501));
		raciones = CAPACIDAD_MAX;
		cocineroAvisado = false;
		notifyAll();
	}

}
