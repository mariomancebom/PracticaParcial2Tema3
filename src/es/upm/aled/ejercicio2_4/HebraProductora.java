package es.upm.aled.ejercicio2_4;

public class HebraProductora implements Runnable {

    private final RecursoCompartido buffer; // Referencia al recurso compartido
    private final String nombre;

    // Constructor que recibe la instancia del buffer
    public HebraProductora(RecursoCompartido buffer, String nombre) {
        this.buffer = buffer;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Hebrra Productora " + nombre + " iniciada.");
        
        // El productor produce indefinidamente
        try {
            while (true) {
                // 1. Genera el dato: un entero aleatorio entre 0 y 10,000
        		int nuevoValor = (int) (Math.random() * 1001);

                // 2. Llama al método producir del buffer
                System.out.println(">>> Productor " + nombre + " va a producir: " + nuevoValor);
                buffer.producir(nuevoValor); // Llama al método sincronizado del monitor
                
                // 3. Espera un tiempo aleatorio para simular el trabajo
                Thread.sleep(500); 
            }
        } catch (InterruptedException e) {
            System.out.println("Hebrra Productora " + nombre + " interrumpida y finalizada.");
            Thread.currentThread().interrupt(); // Restablecer el estado de interrupción
        }
    }
}