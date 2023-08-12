package controller;
import java.util.HashMap;
import model.GenereAlimentare;

public class GeneratoreListaSpesa {
	
	private static GeneratoreListaSpesa generatore=null;
	private HashMap<GenereAlimentare,Integer> lista=new HashMap<GenereAlimentare, Integer>();
	
	private GeneratoreListaSpesa() {}
	
	public static GeneratoreListaSpesa initGeneratore() {
		if(generatore==null)
			generatore=new GeneratoreListaSpesa();
		return generatore;
	}
	
	private void acquistaDatiPrenotazioni() {
		//da implementare
	}
	
	private void acquistaDatiGiacenze() {
		//da implementare
	}
	
	public HashMap<GenereAlimentare, Integer> genera(){
		acquistaDatiPrenotazioni();
		acquistaDatiGiacenze();
		HashMap<GenereAlimentare, Integer> res=null;
		//da implementare
		return res;
	}
}
