package model;

public class ListaBevande extends Lista<Bevanda>{
	
	private static ListaBevande lista=null;
	private ListaBevande() {
		super();
		
	}
	
	public static ListaBevande getListaBevande() {
		if(lista==null)
			lista=new ListaBevande();
		return lista;
	}
	
	
}
