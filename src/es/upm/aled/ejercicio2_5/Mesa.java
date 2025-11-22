package es.upm.aled.ejercicio2_5;

public class Mesa {
	public static void main(String[] args) {
		final int numFilosofos=5;
		//Crear los 5 tenedores
		RecursoCompartido[] tenedores= new RecursoCompartido[numFilosofos];
		for(int i=0;i<numFilosofos;i++) {
			tenedores[i]=new RecursoCompartido(i);
		}
		//Crear y lanzar los 5 filosofos 
		for(int i=0;i<numFilosofos;i++) {
			RecursoCompartido tenedorIzquierda= tenedores[i];
			RecursoCompartido tenedorDerecha=tenedores[(i+1)%numFilosofos];
			Filosofo f=new Filosofo(i+1, tenedorIzquierda, tenedorDerecha);
			Thread t= new Thread(f);
			t.start();
		}
	}
}
