package es.upm.aled.ejercicio2_5;

public class RecursoCompartido {
	private final int id;
	private boolean libre=true;
	
	public RecursoCompartido(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	public synchronized void coger() throws InterruptedException {
		while(!libre) {
			wait();
		}
		libre=false;
	}
	public synchronized void soltar() {
		libre=true;
		notifyAll();
	}
}
