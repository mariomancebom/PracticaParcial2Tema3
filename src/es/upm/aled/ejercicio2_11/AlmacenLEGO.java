package es.upm.aled.ejercicio2_11;

public class AlmacenLEGO {

	private static final int CAPACIDAD_MAXIMA = 50;
	private int piezasRojasActuales = 0;
	private int piezasAzulesActuales = 0;

	public synchronized void depositarPiezaRoja() throws InterruptedException {
		while (CAPACIDAD_MAXIMA == piezasRojasActuales) {
			wait();
		}
		piezasRojasActuales++;
		System.out.println("Línea Roja depositó. Total: " + piezasRojasActuales);
		notifyAll();
	}

	public synchronized void depositarPiezaAzul() throws InterruptedException {
		while (CAPACIDAD_MAXIMA == piezasAzulesActuales) {
			wait();
		}
		piezasAzulesActuales++;
		System.out.println("Línea Azul depositó. Total: " + piezasAzulesActuales);
		notifyAll();
	}

	// Extrae de ambas cestas las piezas necesarias para cumplir con el pedido.
	public synchronized void extraerPiezas(int rojas, int azules) throws InterruptedException {
		while (rojas > piezasRojasActuales || azules > piezasAzulesActuales) {// No puede haber piezas negativas
			wait();
		}
		piezasRojasActuales = piezasRojasActuales - rojas;
		piezasAzulesActuales = piezasAzulesActuales - azules;
		System.out.println("Gestor extrajo " + rojas + "R y " + azules + "A. R: " + piezasRojasActuales + ", A: "
				+ piezasAzulesActuales);
		notifyAll();
	}
}
