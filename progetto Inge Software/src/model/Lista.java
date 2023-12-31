package model;
import java.util.ArrayList; 

public abstract class Lista<P> {
	protected ArrayList<P> lista=new ArrayList<P>();
	
	public synchronized void aggiungiElem(P elem) {
		lista.add(elem);
	}
	
	public synchronized void rimuoviElem(P elem) {
		lista.remove(elem);
	}
	
	public P getElem(int num) {
		return lista.get(num);
	}
	
	public int getDimLista() {
		return lista.size();
	}
	
	public void setElem(P elem, int pos) {
		lista.set(pos, elem);
	}
	
	public ArrayList<P> getLista(){
		return lista;
	}
}
