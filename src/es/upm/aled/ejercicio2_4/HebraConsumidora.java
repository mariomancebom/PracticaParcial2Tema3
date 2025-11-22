package es.upm.aled.ejercicio2_4;

public class HebraConsumidora implements Runnable {

	private final RecursoCompartido buffer;
	private final String name;

	public HebraConsumidora(RecursoCompartido buffer, String name) {
		this.buffer = buffer;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Hebra consumidora" + name + "iniciada");
		try {
			buffer.consumir();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
