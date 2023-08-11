package model;
import java.util.HashMap;

public class ListaSpesa {
	
	private HashMap<GenereAlimentare,Integer> lista = new HashMap<GenereAlimentare,Integer>();
	public ListaSpesa() {
		
	}
	
	public HashMap<GenereAlimentare,Integer> getLista(){
		return lista;
	}
	
	public void setLista(HashMap<GenereAlimentare,Integer> arg) {
		lista=arg;
	}
}
