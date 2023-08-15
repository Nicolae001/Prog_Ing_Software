package model;

public class ListaPiatti extends Lista<Piatto>{
private static ListaPiatti ricette=null;
	
	private ListaPiatti() {};
		
	public static ListaPiatti getPiatti() {
		if(ricette==null)
			ricette=new ListaPiatti();
		return ricette;
	}
}
