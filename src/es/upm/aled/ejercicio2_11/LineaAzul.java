package es.upm.aled.ejercicio2_11;

public class LineaAzul extends Thread {
	private AlmacenLEGO almacen;

	public LineaAzul(AlmacenLEGO almacen) {
		this.almacen = almacen;
	}

	@Override
	public void run() {
		System.out.println("Línea Azul iniciada.");
		try {
			while (true) {
				Thread.sleep((long) (Math.random() * 1000));// Simula el tiempo de produccion
				almacen.depositarPiezaAzul();
				System.out.println("Línea Azul produjo una pieza.");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}