package model;


public class ListaRicette extends Lista<Ricetta>{
	
	private static ListaRicette ricette=null;
	
	private ListaRicette() {};
		
	public static ListaRicette getRicette() {
		if(ricette==null)
			ricette=new ListaRicette();
		return ricette;
	}
	
}
