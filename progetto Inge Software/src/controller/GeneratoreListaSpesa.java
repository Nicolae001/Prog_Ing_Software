package controller;
import java.util.*;
import java.time.LocalDate;
import model.*;


public class GeneratoreListaSpesa {
	
	private static GeneratoreListaSpesa generatore=null;

	
	private GeneratoreListaSpesa() {}
	
	public static GeneratoreListaSpesa initGeneratore() {
		if(generatore==null)
			generatore=new GeneratoreListaSpesa();
		return generatore;
	}
	
	private HashMap<Ingrediente, Double> estraiDatiRicette(Selezionabile s){
		HashMap<Ingrediente, Double> res;
		if(s instanceof MenuTematico) {
			Piatto[] piatti=s.getPiatti();
			ArrayList<Ingrediente>ingredienti=new ArrayList<Ingrediente>();
			ArrayList<Double> quantita=new ArrayList<Double>();
			Ingrediente[] ingr;
			Double[] qta;
			Set<Ingrediente> set;
			Collection<Double> col;
			for(int i=0;i<piatti.length;i++) {
				res=piatti[i].getRicetta().getIngredienti();
				set=res.keySet();
				ingr=set.toArray(new Ingrediente[set.size()]);
				col=res.values();
				qta=col.toArray(new Double[col.size()]);
				for(int j=0;j<ingr.length;j++) {
					if(ingredienti.contains(ingr[j])) {
						int pos=ingredienti.indexOf(ingr[j]);
						Double q=quantita.get(pos)+qta[j];
						quantita.set(pos, q);
					}
					else {
					ingredienti.add(ingr[j]);
					quantita.add(qta[j]);
					}
						
				}
				
			}
			res=new HashMap<Ingrediente, Double>();
			for(Ingrediente in: ingredienti) {
				res.put(in, quantita.get(ingredienti.indexOf(in)));
			}
			return res;
		}
		res=s.getPiatto().getRicetta().getIngredienti();
		
		return res;
	}
	
	private HashMap<Ingrediente, Double> datiIngredienti(ListaPrenotazioni lista, LocalDate data) {
		HashMap<Ingrediente, Double> res=null;
		ArrayList<Prenotazione> prenotazioni=new ArrayList<Prenotazione>();
		for(int i=0;i<lista.getDimLista();i++) {
			if(lista.getElem(i).getData().isEqual(data))
				prenotazioni.add(lista.getElem(i));
		}
		HashMap<Selezionabile,Integer> selezioni;
		ArrayList<Ingrediente> ingredienti=new ArrayList<Ingrediente>();
		ArrayList<Double>quantita=new ArrayList<Double>();
		Set<Ingrediente> set;
		Collection<Double> col;
		Ingrediente[] ingr;
		Double[] qta;
		for(Prenotazione p:prenotazioni) {
			selezioni=p.getSelezioni();
			for(Selezionabile s:selezioni.keySet()) {
				res=estraiDatiRicette(s);
				set=res.keySet();
				col=res.values();
				ingr=set.toArray(new Ingrediente[set.size()]);
				qta=col.toArray(new Double[col.size()]);
				for(int i=0;i<ingr.length;i++) {
					if(ingredienti.contains(ingr[i])) {
						int pos=ingredienti.indexOf(ingr[i]);
						Double q=quantita.get(pos)+qta[i]*selezioni.get(s);
						quantita.set(pos, q);
					}
					else {
					ingredienti.add(ingr[i]);
					quantita.add(qta[i]*selezioni.get(s));
					}
				}
			}
			
		}
		res=new HashMap<Ingrediente, Double>();
		for(Ingrediente in: ingredienti) 
			res.put(in, quantita.get(ingredienti.indexOf(in)));
		return res;
	}
	
	private Set<GenereAlimentare> getSet(HashMap<Bevanda, Double> arg){
		Set<GenereAlimentare> res=Collections.<GenereAlimentare>emptySet();
		Set<Bevanda> temp=arg.keySet();
		Bevanda[] bevande=temp.toArray(new Bevanda[temp.size()]);
		for(int i=0;i<bevande.length;i++)
			res.add(bevande[i]);
		return res;
	}
	
	private Set<GenereAlimentare> getOtherSet(HashMap<GenereExtra, Double> arg){
		Set<GenereAlimentare> res=Collections.<GenereAlimentare>emptySet();
		Set<GenereExtra> temp=arg.keySet();
		Bevanda[] bevande=temp.toArray(new Bevanda[temp.size()]);
		for(int i=0;i<bevande.length;i++)
			res.add(bevande[i]);
		return res;
	}
	
	private HashMap<GenereAlimentare, Double> datiResto(ListaPrenotazioni lista, LocalDate data){
		HashMap<GenereAlimentare, Double> res=new HashMap<GenereAlimentare, Double>();
		ArrayList<Prenotazione> prenotazioni=new ArrayList<Prenotazione>();
		Set<GenereAlimentare> bevande=getSet(ConsumoBevande.initConsumi().getConsumi());
		Set<GenereAlimentare> extra=getOtherSet(ConsumoExtra.initConsumi().getConsumi());
		for(GenereAlimentare alim : bevande)
			res.put(alim, 0.0);
		for(GenereAlimentare alim : extra)
			res.put(alim, 0.0);
		for(int i=0;i<lista.getDimLista();i++) {
			if(lista.getElem(i).getData().isEqual(data))
				prenotazioni.add(lista.getElem(i));
		}
		for(Prenotazione p: prenotazioni) {
			int num=p.getNumCoperti();
			for(GenereAlimentare alim: bevande) {
				Double val=res.get(alim);
				val=val+ConsumoBevande.initConsumi().getConsumi().get(alim)*num;
				res.put(alim, val);
			}
		}
		
		return res;
	}
	
	private HashMap<GenereAlimentare, Double> acquistaDatiPrenotazioni(ListaPrenotazioni lista, LocalDate data){
		HashMap<GenereAlimentare, Double> resto= datiResto(lista,data);
		HashMap<Ingrediente, Double> ingredienti=datiIngredienti(lista,data);
		HashMap<GenereAlimentare, Double> res=new HashMap<GenereAlimentare, Double>();
		for(Ingrediente alim: ingredienti.keySet())
			res.put(alim, res.get(alim));
		for(GenereAlimentare alim:resto.keySet())
			res.put(alim, res.get(alim));
		return res;
	}
	

	
	public ListaSpesa genera(){
		HashMap<GenereAlimentare, Double>necessario=acquistaDatiPrenotazioni(ListaPrenotazioni.initLista(),LocalDate.now());
		HashMap<GenereAlimentare, Double>disponibile=RegistroMagazzino.creaRegistro().getGiacenze();
		HashMap<GenereAlimentare, Integer> diff=new HashMap<GenereAlimentare, Integer>();
		for(GenereAlimentare key:necessario.keySet()) {
			Double qta=necessario.get(key)-disponibile.get(key);
			if(qta>0)
				diff.put(key, qta.intValue()+1);
		}
		ListaSpesa res=new ListaSpesa();	
		res.setLista(diff);
		return res;
	}
	
	public void eliminaGeneratore() {
		generatore=null;
	}
	
	
}

