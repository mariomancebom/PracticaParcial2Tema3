package es.upm.aled.ejercicio2_10;

public class Vehiculo extends Thread {
	private boolean ambulancia;
	private int peso;
	private String id;
	private PuenteMonitor monitor;

	public Vehiculo(PuenteMonitor monitor, String id, int peso, boolean ambulancia) {
		this.monitor = monitor;
		this.id = id;
		this.peso = peso;
		this.ambulancia = ambulancia;
	}

	@Override
	public void run() {
		System.out.println("El vehiculo " + id + " quiere entrar en el puente");
		try {
			monitor.entrarPuente(peso, ambulancia);
			System.out.println("El vehículo " + id + " ha entrado en el puente");
			Thread.sleep(10000);// Tiempo que tarda en cruzar el puente
			monitor.salirPuente(peso);
			System.out.println("El vehículo " + id + " ha salido del puente");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
