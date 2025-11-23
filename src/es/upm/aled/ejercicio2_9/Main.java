package es.upm.aled.ejercicio2_9;

public class Main {
	public static void main(String[] args) {

		// El Monitor
		Haciendav2 hacienda = new Haciendav2();

		// 1. Crear y lanzar la hebra de Andrés
		Thread andres = new Thread(new Funcionario(hacienda));
		andres.start();

		// Pequeña pausa para que Andrés entre en modo "siesta" y espere el primer aviso
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		// 2. Crear hebras de Ciudadanos

		// Escenario 1: Probar la siesta (llega un ciudadano, debe despertar a Andrés)
		new Thread(new Ciudadano(hacienda, 1, "C-Siesta")).start();

		// Escenario 2: Probar la prioridad y el FIFO

		// 5 Ciudadanos V2 (Cola más larga)
		for (int i = 1; i <= 5; i++) {
			new Thread(new Ciudadano(hacienda, 2, "C2-" + i)).start();
		}

		// 3 Ciudadanos V1 (Cola más corta/Empate)
		for (int i = 1; i <= 3; i++) {
			new Thread(new Ciudadano(hacienda, 1, "C1-" + i)).start();
		}
	}
}