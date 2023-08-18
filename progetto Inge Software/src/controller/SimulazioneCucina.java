package controller;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import model.Piatto;
import model.Ingrediente;

public class SimulazioneCucina {
	
	private ArrayList<Piatto> piatti= new ArrayList<Piatto>();
	private HashMap<Ingrediente, Double> ingredienti=new HashMap<Ingrediente, Double>();
	private HashMap<Ingrediente, Double> scarti=new HashMap<Ingrediente, Double>();
	private static SimulazioneCucina cucina=null;
	
	public static SimulazioneCucina initCucina() {
		if(cucina==null)
			cucina=new SimulazioneCucina();
		return cucina;
	}
	
	public void cucina(Piatto p){
		generaScarti(p);
		HashMap<Ingrediente, Double> ingr=p.getRicetta().getIngredienti();
		Double qta;
		for(Ingrediente key:ingr.keySet()) {
			qta=ingredienti.get(key);
			qta=qta-ingr.get(key);
			ingredienti.replace(key, qta);
		}
		piatti.add(p);
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
	
	private void inserisciScarti(Ingrediente ingr, Double qta) {
		if(scarti.get(ingr)==null)
			scarti.put(ingr, qta);
		else {
			Double q=scarti.get(ingr);
			q+=qta;
			scarti.replace(ingr, q);
		}
	}
	
	private void generaScarti(Piatto p) {
		HashMap<Ingrediente, Double> ingr=p.getRicetta().getIngredienti();
		Random r=new Random();
		int lancio;
		Double qta;
		for(Ingrediente key:ingr.keySet()) {
			lancio=r.nextInt(100);
			if(lancio<50) {
				qta=ingredienti.get(key);
				qta=qta-ingr.get(key)/10;
				ingredienti.replace(key, qta);
				inserisciScarti(key,ingr.get(key)/10);
			}
		}
	}
	
	public void eliminaCucina() {
		cucina=null;
	}
}
