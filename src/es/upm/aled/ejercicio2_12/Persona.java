package es.upm.aled.ejercicio2_12;

public class Persona extends Thread {

	private GestorMuseo museo;
	private String nombre;
	private boolean jubilado; // True si esta jubilado

	public Persona(GestorMuseo museo, String nombre, boolean jubilado) {
		this.museo = museo;
		this.nombre = nombre;
		this.jubilado = jubilado;
	}

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(nombre + " quiere entrar en el museo.");
				if (!jubilado) {
					museo.entrarSala();
				} else {
					museo.entrarSalaJubilado();
				}
				System.out.println(nombre + " ha entrado en el museo");
				// Tiempo de visita
				Thread.sleep(10000);
				museo.salirSala();
				System.out.println(nombre + " ha salido del museo");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
