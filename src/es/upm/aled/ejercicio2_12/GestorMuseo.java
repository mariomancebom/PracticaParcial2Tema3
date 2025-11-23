package es.upm.aled.ejercicio2_12;

import java.util.LinkedList;
import java.util.Queue;

public class GestorMuseo {

	private static final int AFORO_NORMAL = 50;
	private static final int AFORO_REDUCIDO = 35;
	private static final int LIMITE_TEMPERATURA = 30;
	private int temperaturaActual = 25;
	private int genteDentro = 0;
	private int jubiladosEsperando = 0;// Para establecer la prioridad
	private int aforoMaximo = AFORO_NORMAL;
	private Queue<String> cola = new LinkedList<>();

	// Se invoca cuando una persona quiere entrar en el museo.
	public synchronized void entrarSala() throws InterruptedException {
		String miNombre = Thread.currentThread().getName();
		cola.add(miNombre);
		while (genteDentro >= aforoMaximo || jubiladosEsperando > 0 || !cola.peek().equals(miNombre)) {
			wait();
		}
		cola.remove();
		genteDentro++;
		System.out.println(">>> " + miNombre + " (Normal) entra. Aforo: " + genteDentro + "/" + aforoMaximo);
		notifyAll();
	}

	// Se invoca cuando una persona jubilada quiere entrar en el museo.
	public synchronized void entrarSalaJubilado() throws InterruptedException {
		String miNombre = Thread.currentThread().getName();
		System.out.println(">>> " + miNombre + " (Jubilado) llega. Jubilados esperando: " + jubiladosEsperando);
		jubiladosEsperando++;
		while (genteDentro >= aforoMaximo) {
			System.out.println(miNombre + " (Jubilado) espera por AFORO. Actual: " + genteDentro);
			wait();
		}
		jubiladosEsperando--;
		genteDentro++;
		System.out.println(">>> " + miNombre + " (JUBILADO) ENTRA. Aforo: " + genteDentro + "/" + aforoMaximo);
		notifyAll();
	}

	// Se invoca cuando una persona, jubilada o no, quiere salir del museo.
	public synchronized void salirSala() {
		genteDentro--;
		notifyAll();
	}

	// Lo invoca la hebra que mide la temperatura indicar el último valor medido.
	public synchronized void notificarTemperatura(int temperatura) {
		temperaturaActual = temperatura;
		if (temperaturaActual >= LIMITE_TEMPERATURA) {
			aforoMaximo = AFORO_REDUCIDO;
		} else if (temperaturaActual < LIMITE_TEMPERATURA) {
			aforoMaximo = AFORO_NORMAL;

		}
		notifyAll();
	}
}
