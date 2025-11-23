package es.upm.aled.ejercicio2_12;

public class Termometro extends Thread {

	private GestorMuseo museo;

	public Termometro(GestorMuseo museo) {
		this.museo = museo;
	}

	@Override
	public void run() {
		while (true) {
			int nuevaTemperatura = (int) (Math.random() * 40);
			museo.notificarTemperatura(nuevaTemperatura);
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}
	}
}
