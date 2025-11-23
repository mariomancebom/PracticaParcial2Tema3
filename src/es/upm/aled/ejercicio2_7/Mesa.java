package es.upm.aled.ejercicio2_7;

public class Mesa {
	private boolean tabaco=false;
	private boolean papel=false;
	private boolean cerilla=false;
	private boolean mesaLlena=false;
	private int numeroFumadores;
	
	public Mesa(int numeroFumadores) {
		this.numeroFumadores=numeroFumadores;
	}
	
	//Los ingredientes tienen orden 0:tabaco, 1:papel, 2:cerilla
	public synchronized void ponerIngredientes(int tipo1, int tipo2) throws InterruptedException {
		while(mesaLlena) {
			wait();
		}
		//Se supone que los ingredientes tipo1 y tipo2 tienen que ser distintos
		if(tipo1==0 || tipo2==0) {
			tabaco=true;			
		}
		if(tipo1==1||tipo2==1) {
			papel=true;
		}
		if(tipo1==2||tipo2==2) {
			cerilla=true;
		}
		mesaLlena=true;
		System.out.println("El estanco ya ha aportado sus materiales");
		notifyAll();
	}

	public synchronized void cogerIngredientes(int ingredienteQueTengo) throws InterruptedException {
		while (!mesaLlena || (ingredienteQueTengo == 0 && !(papel && cerilla))
				|| (ingredienteQueTengo == 1 && !(tabaco && cerilla))
				|| (ingredienteQueTengo == 2 && !(papel && tabaco))) {
			wait();
		}
		tabaco=false;
		papel=false;
		cerilla=false;
		mesaLlena=false;
		notifyAll();
		System.out.println("El fumador ya tiene los ingredientes para fumar");
	}
}