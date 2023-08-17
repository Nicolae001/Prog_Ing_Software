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
	
	public void cucina(Piatto p, int porzioni){
		//da implementare
	}
	
	public synchronized void prendiIngredienti(Ingrediente[] ingr, Double[] qta) {
		for(int i=0;i<ingr.length;i++) {
			if(ingredienti.get(ingr[i])==null)
				ingredienti.put(ingr[i], qta[i]);
			else {
				Double q=ingredienti.get(ingr[i]);
				q=q+qta[i];
				ingredienti.replace(ingr[i], q);
			}
		}
	}
	
	public synchronized HashMap<Ingrediente, Double> ritornaIngrendienti() {
		HashMap<Ingrediente, Double> res=ingredienti;
		ingredienti=new HashMap<Ingrediente, Double>();
		return res;
	}
	
	private void generaScarti(Piatto p, int ) {
		//da implementare
	}
	
	public void eliminaCucina() {
		cucina=null;
	}
}
