package es.upm.aled.ejercicio2_10;

public class Main {
	
	public static void main(String[] args) {
		PuenteMonitor monitor=new PuenteMonitor();
		for(int i=0;i<15;i++) {
			Thread vehiculo= new Vehiculo(monitor,"V"+(i+1),(int)(Math.random()*2500),false);
			vehiculo.start();
		}
		Thread ambulancia=new Vehiculo(monitor, "AMB-Urg", 1500, true);
		ambulancia.start();
	}
}
