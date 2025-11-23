package es.upm.aled.ejercicio2_9;

public class Funcionario implements Runnable {

    private final Haciendav2 hacienda;
    private final String nombre = "Andrés Trozado";

    public Funcionario(Haciendav2 h) {
        this.hacienda = h;
    }

    @Override
    public void run() {
        System.out.println(nombre + " comienza su jornada laboral.");
        
        // Andrés trabaja continuamente
        while (true) {
            try {
                // Llama al monitor para atender al próximo ciudadano según la prioridad
                hacienda.atenderCiudadano();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nombre + " ha sido interrumpido y termina su jornada (¡posiblemente por Francisco Rupto!).");
                return;
            }
        }
    }
}