package es.upm.aled.ejercicio2_8;

public class Main {
	private static final int NUM_FUMADORES = 3;

	public static void main(String[] args) {

		Mesa mesa = new Mesa(NUM_FUMADORES);
		Thread estanquero = new Estanquero(mesa);
		Fumador[] fumadores = new Fumador[NUM_FUMADORES]; // 1. Almacenar referencias

		// --- Creación de las Hebras ---
		for (int i = 0; i < NUM_FUMADORES; i++) {
			fumadores[i] = new Fumador(mesa, i, "F" + (i + 1));
		}

		// --- Conexión Anti-Deadlock (Romper la Simetría) ---
		// El Fumador i necesita el ingrediente del Fumador i+1.
		// El Fumador 2 (F3) es el que rompe el ciclo para evitar el deadlock.
		for (int i = 0; i < NUM_FUMADORES; i++) {
			
			// El fumador actual (i) pide al fumador siguiente (i+1)
			Fumador fumadorParaPedir = fumadores[(i + 1) % NUM_FUMADORES];
			
			// F3 (índice 2) pide a F1 (índice 0)
			
			// **El problema es complejo, la conexión más sencilla para evitar deadlock es:**
			// F1 pide a F2
			// F2 pide a F3
			// F3 pide a F1
			
			// Para evitar el deadlock, haremos que el último fumador (F3) no espere a F1,
			// sino que pida a F2, creando una conexión A -> B <- C (rompe la espera circular)
			
			if (i == 2) { // Si es F3 (el último)
				// F3 pide a F2 (índice 1) para romper el ciclo A->B->C->A
				fumadorParaPedir = fumadores[1]; 
			}
			
			fumadores[i].setVecino(fumadorParaPedir); //  2. Asignar el vecino
		}

		// --- Lanzamiento ---
		estanquero.start();
		for (Fumador f : fumadores) {
			f.start();
		}
	}
}
