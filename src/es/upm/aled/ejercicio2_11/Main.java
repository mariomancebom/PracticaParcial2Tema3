package es.upm.aled.ejercicio2_11;

public class Main {
	private static final int NUM_LINEAS_ROJAS = 2;
    private static final int NUM_LINEAS_AZULES = 1;
    private static final int NUM_GESTORES = 3; 

    public static void main(String[] args) {
    	// 1. Crear el Monitor (Recurso Compartido)
        AlmacenLEGO almacen = new AlmacenLEGO();
        System.out.println("Fábrica LEGO: Almacén iniciado con capacidad 50 por cesta.");
        
        //Lanzar las lineas de produccion 
        for (int i=0;i<NUM_LINEAS_ROJAS;i++) {
        	Thread lineaR=new LineaRoja(almacen);
        	lineaR.start();
        }
        //Linea roja
        for (int i = 0; i < NUM_LINEAS_AZULES; i++) {
        	Thread lineaA=new LineaAzul(almacen);
        	lineaA.start();
        }
        
        //Gestor de pedidos 
        for (int i = 0; i < NUM_GESTORES; i++) {
            Thread gestor = new GestorPedidos(almacen);
            gestor.start();
        }
    }
}
