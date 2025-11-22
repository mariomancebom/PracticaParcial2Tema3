package es.upm.aled.ejercicio2_1;

public class HebraEscritora extends Thread {
    private final RecursoCompartido recurso;
    private final String nombre;

    public HebraEscritora(RecursoCompartido recurso, String nombre) {
        this.recurso = recurso;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            // Intenta escribir en el recurso
            recurso.escribir(nombre);
        } catch (InterruptedException e) {}
    }
}