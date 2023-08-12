package model;
import java.util.ArrayList; 

abstract class Lista<P> {
	private ArrayList<P> lista=new ArrayList<P>();
	
	public synchronized void aggiungiElem(P elem) {
		lista.add(elem);
	}
	
	public synchronized void rimuoviElem(P elem) {
		lista.remove(elem);
	}
	
	public synchronized P getElem(int num) {
		return lista.get(num);
	}
	
	public int getDimLista() {
		return lista.size();
	}
	

}
