package es.upm.aled.ejercicio2_10;

public class PuenteMonitor {

	private int numVehiculos = 0;// El puente empieza vacio
	private int pesoTotal = 0;
	private int ambulanciasEsperando = 0;

	public synchronized void entrarPuente(int peso, boolean ambulancia) throws InterruptedException {
		if (ambulancia) {
			ambulanciasEsperando++;
			System.out.println(">>> AMBULANCIA llega. Esperando turno.");
		}
		while (
		// Condición A: El puente está lleno o sobrecargado
		numVehiculos >= 10 || pesoTotal + peso > 15000 ||
		// Condición B: Si NO soy ambulancia, espero si hay alguna ambulancia esperando.
				(!ambulancia && ambulanciasEsperando > 0)) {
			// Si una ambulancia duerme aquí, es por capacidad (A) o por una ambulancia
			// anterior.
			wait();
		}

		// 3. Si es ambulancia y ha pasado, reducir el contador de espera
		if (ambulancia) {
			ambulanciasEsperando--;
		}
		pesoTotal = pesoTotal + peso;
		numVehiculos++;
		notifyAll();
	}

	public synchronized void salirPuente(int peso) {
		numVehiculos--;
		pesoTotal=pesoTotal-peso;
		notifyAll();

	}
}
