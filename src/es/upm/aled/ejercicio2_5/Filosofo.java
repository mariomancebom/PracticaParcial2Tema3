package es.upm.aled.ejercicio2_5;

public class Filosofo implements Runnable {

	private final int id;
	private final RecursoCompartido primerTenedor;
	private final RecursoCompartido segundoTenedor;

	public Filosofo(int id, RecursoCompartido izquierda, RecursoCompartido derecha) {
		this.id = id;

		if (izquierda.getId() < derecha.getId()) {
			this.primerTenedor = izquierda;
			this.segundoTenedor = derecha;
		} else {
			this.primerTenedor = derecha;
			this.segundoTenedor = izquierda;
		}
	}

	private void pensar() throws InterruptedException {
		System.out.println("Filósofo " + id + " está pensando.");
		Thread.sleep((long) (Math.random() * 500));
	}

	private void comer() throws InterruptedException {
		// 1. Coge el PRIMER tenedor (ID más bajo)
		primerTenedor.coger();
		System.out.println("F" + id + " cogió T" + primerTenedor.getId());

		// 2. Coge el SEGUNDO tenedor (ID más alto)
		// Como todos siguen la misma regla, se rompe la espera circular.
		segundoTenedor.coger();
		System.out.println("F" + id + " cogió T" + segundoTenedor.getId() + " --> COMIENDO");

		// Simular el acto de comer
		Thread.sleep((long) (Math.random() * 500));

		// 3. Soltar ambos tenedores
		segundoTenedor.soltar();
		primerTenedor.soltar();
		System.out.println("F" + id + " terminó de comer.");
	}

	@Override
	public void run() {
		try {
			pensar();
			comer();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
