package es.upm.aled.ejercicio2_9;

public class Ciudadano implements Runnable {

    private final Haciendav2 hacienda;
    private final int ventanilla;
    private final String nombre; // Usaremos el nombre como token en la cola

    public Ciudadano(Haciendav2 h, int v, String n) {
        this.hacienda = h;
        this.ventanilla = v;
        this.nombre = n; 
    }

    @Override
    public void run() {
        try {
            if (ventanilla == 1) {
                hacienda.esperarVentanilla1(nombre);
            } else {
                hacienda.esperarVentanilla2(nombre);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(nombre + " ha sido interrumpido.");
        }
    }
}