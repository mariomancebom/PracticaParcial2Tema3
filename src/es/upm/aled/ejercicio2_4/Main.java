package es.upm.aled.ejercicio2_4;

public class Main {

	public static void main(String[] args) {
		// Puedes configurar aquí el número de hebras que quieres lanzar
		final int NUM_PRODUCTORES = 3;
		final int NUM_CONSUMIDORES = 3;

		// 1. Crear el Recurso Compartido (ÚNICA instancia del Monitor)
		RecursoCompartido buffer = new RecursoCompartido();
		System.out.println("Buffer Compartido (Capacidad 10) creado.");

		// --- 2. Crear las Hebras ---

		// Crear Productores
		for (int i = 0; i < NUM_PRODUCTORES; i++) {
			// Se crea una instancia de la clase Productor, pasándole el mismo 'buffer'
			Runnable productorRunnable = new HebraProductora(buffer, "P" + (i + 1));
			Thread t = new Thread(productorRunnable);
			t.start();
		}

		// Crear Consumidores
		for (int i = 0; i < NUM_CONSUMIDORES; i++) {
			// Se crea una instancia de la clase Consumidor, pasándole el mismo 'buffer'
			Runnable consumidorRunnable = new HebraConsumidora(buffer, "C" + (i + 1));
			Thread t = new Thread(consumidorRunnable);
			t.start();
		}
		System.out.println("Simulación de Productor-Consumidor iniciada.");
	}
}
