package es.upm.aled.ejercicio2_9;

public class Hacienda {

	private boolean servicioTerminado = false;
	private int genteCola1=0;
	private int genteCola2=0;

	public synchronized void esperarVentanilla1() throws InterruptedException {
		genteCola1++;
		System.out.println("Ciudadano llega a Ventanilla 1. Total: " + genteCola1);
		if (genteCola1 + genteCola2 == 1) {
			System.out.println("Ciudadano carraspea, despertando a Andrés.");
			notifyAll();
			Thread.sleep((long) (Math.random() * 500));// Simula el tiempo que tarda en atenderles
			servicioTerminado = true;
		}
		while (!servicioTerminado) {
			wait();
		}
	}

	public synchronized void esperarVentanilla2() throws InterruptedException {
		genteCola2++;
		System.out.println("Ciudadano llega a Ventanilla 2. Total: " + genteCola2);
		if (genteCola1 + genteCola2 == 1) {
			System.out.println("Ciudadano carraspea, despertando a Andrés.");
			notifyAll();
			Thread.sleep((long) (Math.random() * 500));// Simula el tiempo que tarda en atenderles
			servicioTerminado = true;
		}
		while (!servicioTerminado) {
			wait();
		}
	}

	public synchronized void atenderCiudadano() throws InterruptedException {
		while (genteCola1 + genteCola2 == 0) {
			wait();// Manda a dormir a Andres
		}
		if (genteCola1 > genteCola2) {
			Thread.sleep((long) (Math.random() * 500));// Simula el tiempo que tarda en atenderles
			genteCola1--;
			notifyAll();
		}
		if (genteCola2 > genteCola1) {
			Thread.sleep((long) (Math.random() * 500));// Simula el tiempo que tarda en atenderles
			genteCola2--;
			notifyAll();
		}
		if (genteCola1 == genteCola2) {
			Thread.sleep((long) (Math.random() * 500));// Simula el tiempo que tarda en atenderles
			genteCola1--;// Se atienda en la ventanilla 1 si hay el mismo numero de clientes
			notifyAll();
		}
	}

}
