package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCompra;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int permissoes =1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int idCliente=0;idCliente<300;idCliente++){
			Thread tIngresso= new ThreadCompra(idCliente,semaforo);
			tIngresso.start();
		}
	}
}

