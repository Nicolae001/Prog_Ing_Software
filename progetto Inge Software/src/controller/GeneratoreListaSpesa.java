package controller;
import java.util.HashMap;
import model.GenereAlimentare;
import model.RegistroMagazzino;

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
		HashMap<GenereAlimentare, Double>
	
	}
	
	private void acquistaDatiGiacenze() {
		//da implementare
	}
	
	public void genera(){
		acquistaDatiPrenotazioni();
		acquistaDatiGiacenze();
		HashMap<GenereAlimentare, Integer> res=null;
		//da implementare
		lista=res;
	}
	
	public void eliminaGeneratore() {
		generatore=null;
	}
}
