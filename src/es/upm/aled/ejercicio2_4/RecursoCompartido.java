package es.upm.aled.ejercicio2_4;

public class RecursoCompartido {

	private static final int CAPACIDAD_MAXIMA = 10;
	private final Integer[] buffer = new Integer[CAPACIDAD_MAXIMA];
	private int count = 0;// Numero de elementos en el buffer
	private int putIndex = 0;
	private int deleteIndex = 0;

	public synchronized void producir(int valor) throws InterruptedException {
		while (count == CAPACIDAD_MAXIMA) {
			System.out.println("El productor debe esperar");
			wait();
		}
		putIndex = (putIndex + 1) % CAPACIDAD_MAXIMA; // Para que cuando llegue al final no se salga y vuelva al
														// principio
		count++;
		System.out.println("Valor introducido");
		notifyAll();
	}
	public synchronized void consumir()throws InterruptedException{
		while(count==0) {
			System.out.println("El consumidor no puede consumir");
			wait();
		}
		System.out.println("El valor que se va a borrar es "+ buffer[deleteIndex]);
		buffer[deleteIndex]=null;
		deleteIndex=(deleteIndex+1)%CAPACIDAD_MAXIMA;
		count--;
		System.out.println("Valor eliminado");
		notifyAll();
	}
}
