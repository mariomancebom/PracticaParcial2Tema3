package es.upm.aled.ejercicio2_11;

public class GestorPedidos extends Thread {
	
	private AlmacenLEGO almacen;
	
	public GestorPedidos(AlmacenLEGO almacen) {
				this.almacen=almacen;
	}
	@Override
	public void run() {
		try {
			while(true) {
				System.out.println("Se esta tramitando un pedido");
				//Simulacion de la gestion del pedido 
				int piezasRojas=((int)(Math.random() * 25))+1;
				int piezasAzules=((int)(Math.random() * 25))+1;
				Thread.sleep((long) (Math.random() * 1000));
				almacen.extraerPiezas(piezasRojas, piezasAzules);
				System.out.println("Se va a realizar el pedido");
			}
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
