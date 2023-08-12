package controller;
import java.util.HashMap;
import model.Piatto;
import model.Ingrediente;

public class SimulazioneCucina {
	
	private HashMap<Piatto, Integer> porzioni= new HashMap<Piatto, Integer>();
	private HashMap<Ingrediente, Double> ingredienti=new HashMap<Ingrediente, Double>();
	private HashMap<Ingrediente, Double> scarti=new HashMap<Ingrediente, Double>();
	private static SimulazioneCucina cucina=null;
	
	public static SimulazioneCucina initCucina() {
		if(cucina==null)
			cucina=new SimulazioneCucina();
		return cucina;
	}
	
	public void cucina(){
		//da implementare
	}
	
	public synchronized void prendiIngredienti(Ingrediente[] ingr, Double[] qta) {
		//da implementare
	}
	
	public synchronized HashMap<Ingrediente, Double> ritornaIngrendienti() {
		HashMap<Ingrediente, Double> res=null;
		//da implementare
		return res;
	}
	
	private void generaScarti() {
		//da implementare
	}
}
