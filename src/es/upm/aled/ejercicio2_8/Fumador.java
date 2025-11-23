package es.upm.aled.ejercicio2_8;

public class Fumador extends Thread {

	private String nombre;
	private int LoQueTiene;
	private Mesa mesa;
	private boolean miIngredienteDisponible = true;

	private Fumador fumadorParaPedir;

	public Fumador(Mesa mesa, int LoQueTiene, String nombre) {
		this.mesa = mesa;
		this.LoQueTiene = LoQueTiene;
		this.nombre = nombre;
	}

	public void setVecino(Fumador vecino) {
		this.fumadorParaPedir = vecino;
	}

	// Logica del prestamo
	public synchronized void darMiIngrediente(Fumador quienPide) throws InterruptedException {
		while (!miIngredienteDisponible) {
			System.out.println("--- FUMADOR " + nombre + " no puede dar su ingrediente, esperando...");
			wait();
		}
		miIngredienteDisponible = false; // Se esta prestando
		System.out.println("... FUMADOR " + nombre + " PRESTA su ingrediente a " + quienPide.nombre + ".");
	}

	public synchronized void devolverMiIngrediente() {
		miIngredienteDisponible = true;
		notifyAll();
	}

	@Override
	public void run() {
		System.out.println("El fumador " + nombre + " está en el estanco.");
		try {
			while (true) {
				// 1. Coger el ingrediente de la mesa
				mesa.cogerIngredientes(LoQueTiene);
				System.out.println("FUMADOR " + nombre + " cogió ingrediente de la MESA.");

				// 2. Pedir el ingrediente que falta
				fumadorParaPedir.darMiIngrediente(this);

				// 3. FUMAR (Acción de negocio)
				System.out.println("FUMADOR " + nombre + " tiene los 3 y está FUMANDO.");
				Thread.sleep((long) (Math.random() * 500));

				// 4. DEVOLVER los ingredientes prestados
				fumadorParaPedir.devolverMiIngrediente();
				System.out.println("FUMADOR " + nombre + " devuelve ingrediente.");

				// 5. Pausa antes de querer fumar de nuevo
				Thread.sleep((long) (Math.random() * 500));
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

	