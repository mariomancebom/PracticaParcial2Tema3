package es.upm.aled.ejercicio2_12;

public class Main {
	private static final int NUM_PERSONAS = 20;
	private static final int NUM_JUBILADOS = 5;

	public static void main(String[] args) {

		// 1. Crear el Monitor (Recurso Compartido)
		GestorMuseo museo = new GestorMuseo();
		System.out.println("Sistema de Gestión del Museo iniciado.");

		Thread medidor = new Termometro(museo);
		medidor.start();

		for (int i = 0; i < NUM_PERSONAS; i++) {
			String nombre = "Persona-" + (i + 1);
			Thread persona = new Persona(museo, nombre, false);
			persona.setName(nombre);
			persona.start();
		}
		for (int i = 0; i < NUM_JUBILADOS; i++) {
			String nombre = "Jubilado-" + (i + 1);
			Thread jubilado = new Persona(museo, nombre, true); // true = SÍ jubilado
			jubilado.setName(nombre);
			jubilado.start();
		}
		System.out.println("Simulación lanzada. Se espera tráfico y cambios de temperatura.");

	}
}
