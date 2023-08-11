package model;
import java.util.ArrayList; 

abstract class Lista<P> {
	private ArrayList<P> lista=new ArrayList<P>();
	
	public void aggiungiElem(P elem) {
		lista.add(elem);
	}
	
	public void rimuoviElem(P elem) {
		lista.remove(elem);
	}
	
	public P getElem(int num) {
		return lista.get(num);
	}
	
	public int getDimLista() {
		return lista.size();
	}
	

}
