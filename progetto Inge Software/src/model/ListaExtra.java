package model;

public class ListaExtra extends Lista<GenereExtra>{
	
	private static ListaExtra lista=null;
	private ListaExtra() {
		super();
		
	}
	
	public static ListaExtra getListaExtra() {
		if(lista==null)
			lista=new ListaExtra();
		return lista;
	}
}
