package es.upm.aled.ejercicio2_9;

import java.util.LinkedList;
import java.util.Queue;

//Esta vez lo haremos con colas, que es lo que pide el ejercicio
public class Haciendav2 {
	private Queue<String> cola1 = new LinkedList<>();
	private Queue<String> cola2 = new LinkedList<>();

	public synchronized void esperarVentanilla1(String c) throws InterruptedException {
		cola1.add(c);
		System.out.println("Ciudadano llega a V1. Total: " + (cola1.size() + cola2.size()));

		if (cola1.size() + cola2.size() == 1) {
			notify();// Despertamos a andres
		}
		while (cola1.peek() != null && cola1.peek().equals(c)) {
			wait();
		}
	}

	public synchronized void esperarVentanilla2(String c) throws InterruptedException {
		cola2.add(c);
		System.out.println("Ciudadano llega a V1. Total: " + (cola1.size() + cola2.size()));

		if (cola1.size() + cola2.size() == 1) {
			notify();// Despertamos a andres
		}

		while (cola2.peek() != null && cola2.peek().equals(c)) {
			wait();
		}
	}

	public synchronized void atenderCiudadano() throws InterruptedException {

		// 1. Lógica de Siesta
		while (cola1.isEmpty() && cola2.isEmpty()) {
			System.out.println("Andrés duerme la siesta. Zzz...");
			wait();
		}

		Queue<String> colaAAtender; // Usamos String para los nombres/tokens
		String nombreVentanilla;
		String nombreAtendido;

		// 2. Lógica de Prioridad y Selección (Usando if/else if limpio)
		if (cola1.size() >= cola2.size() && !cola1.isEmpty()) {
			colaAAtender = cola1;
			nombreVentanilla = "Ventanilla 1";
		} else { // genteCola2 > genteCola1 y !cola2.isEmpty()
			colaAAtender = cola2;
			nombreVentanilla = "Ventanilla 2";
		}

		nombreAtendido = colaAAtender.peek(); // Obtiene el nombre/token del frente

		System.out.println(">>> Andrés atiende en " + nombreVentanilla + ". Atendiendo a: " + nombreAtendido);

		// 3. Simular el tiempo de atención
		Thread.sleep((long) (Math.random() * 500));

		// 4. Acción clave: Retirar al ciudadano de la cola.
		// ESTA ACCIÓN CAMBIA LA CONDICIÓN DE ESPERA DEL HILO ATENDIDO.
		colaAAtender.remove();

		// 5. Notificar
		// notifyAll() libera al hilo que acaba de ser removido de la cola (que sale del
		// wait())
		// y al siguiente hilo de la misma cola (que puede reevaluar su posición).
		notifyAll();
	}
}
