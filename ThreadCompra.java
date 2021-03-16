package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCompra extends Thread {
	private static int ingressosTotal=100;
	private int ingressos;
	private int idThread;
	private Semaphore semaforo;
	private int tempo;
	Random gerador = new Random();
	public ThreadCompra(int idThread, Semaphore semaforo) {
		// TODO Auto-generated constructor stub
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		logSis();
	}

	private void logSis() {
		// TODO Auto-generated method stub
		ingressos=gerador.nextInt(4)+1;
		tempo = (int) ((Math.random() * 1951) + 50);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#" + idThread + " demorou " + tempo + " milissegundos (Login Sistema)");
		if (tempo > 1000) {
			System.out.println("#" + idThread + " excedeu o tempo limite - Impossível realizar compra");
		} else {
			processoCompra();
		}
	}

	private void processoCompra() {
		tempo = (int) ((Math.random() * 2001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#" + idThread + " demorou " + tempo + " milissegundos (Processo Compra)");
		if (tempo > 2500) {
			System.out.println("#" + idThread + " sessão estourada - Impossível realizar compra");
		} else {
			try {
				semaforo.acquire();
				validaCompra();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}

	private void validaCompra() {
		// TODO Auto-generated method stub
		if(ingressosTotal>=ingressos){
			ingressosTotal-=ingressos;
			System.out.println("#" + idThread + " Comprou "+ingressos+" ingressos, restam "+ingressosTotal+" ingressos para compra");
		}else{
			System.out.println("#" + idThread + " não pode comprar "+ingressos+" ingressos, restam "+ingressosTotal+" ingressos para compra");
		}
	}
}
