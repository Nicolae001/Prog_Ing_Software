package model;
import java.util.HashMap;

abstract class ConsumoProcapite<G> {
	
	protected HashMap<G,Double> consumi=new HashMap<G,Double>();
	
	public ConsumoProcapite() {
		
	}
	
	public Double getValore(G elem) {
		return consumi.get(elem);
	}
	
	public int getDim() {
		return consumi.size();
	}
	
	public void aggiungiElem (G elem, Double val) {
		consumi.put(elem, val);
	}
	
	
	public void rimuoviElem(G elem) {
		consumi.remove(elem);
	}
	
	public HashMap<G,Double> getConsumi(){
		return consumi;
	}
}
