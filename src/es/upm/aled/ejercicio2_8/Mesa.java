package es.upm.aled.ejercicio2_8;

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
	public synchronized void ponerIngrediente(int tipo1) throws InterruptedException {
		while(mesaLlena) {
			wait();
		}
		if(tipo1==0) {
			tabaco=true;			
		}
		if(tipo1==1) {
			papel=true;
		}
		if(tipo1==2) {
			cerilla=true;
		}
		mesaLlena=true;
		System.out.println("El estanco ya ha aportado su material");
		notifyAll();
	}

	public synchronized void cogerIngredientes(int ingredienteQueTengo) throws InterruptedException {
		while (!mesaLlena || (ingredienteQueTengo == 0 && tabaco)
				|| (ingredienteQueTengo == 1 && papel)
				|| (ingredienteQueTengo == 2 && cerilla)) {
			wait();
		}
		tabaco=false;
		papel=false;
		cerilla=false;
		mesaLlena=false;
		System.out.println("El fumador ya tiene los ingredientes para fumar");
		notifyAll();
	}
}