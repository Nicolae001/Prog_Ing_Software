package model;

public class ListaPrenotazioni extends Lista<Prenotazione>{
	
	private static ListaPrenotazioni lista=null;
	
	private ListaPrenotazioni() {}
	
	public static ListaPrenotazioni initLista() {
		if(lista==null)
			lista=new ListaPrenotazioni();
		return lista;
	}
	
	public void eliminaLista() {
		lista=null;
	}
}
