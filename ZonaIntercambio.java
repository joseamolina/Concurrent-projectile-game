package lab5;

import java.util.concurrent.LinkedBlockingQueue;

class ZonaIntercambio  {
	private LinkedBlockingQueue<Nuevodisparo> cola;
	
	public ZonaIntercambio(){
		this.cola = new LinkedBlockingQueue<Nuevodisparo>();
	}
	
	public synchronized void pon(Nuevodisparo nD){
		try {
			cola.put(nD);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean hayDisparos(){
		return !cola.isEmpty();
		
	}
	
	
	public synchronized Nuevodisparo recoge(){
		Nuevodisparo nDa = null;
		try {
			nDa = cola.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return nDa;
		
	}
}
