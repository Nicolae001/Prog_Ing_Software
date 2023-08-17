package controller;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import model.*;

public class ControlloreModifiche {
	
	
	private static double getCaricoPren(Prenotazione pren) {
		double res=0;
		int qta;
		double carico;
		Piatto[] piatti;
		for(Selezionabile key:pren.getSelezioni().keySet()) {
			if(key instanceof Piatto) {
				carico= key.getPiatto().getRicetta().getCaricoLavoroPorz()[0]*key.getPiatto().getRicetta().getCaricoLavoroPorz()[1];
				qta=pren.getSelezioni().get(key);
				res+=carico * qta;
			}
			else {
				piatti=key.getPiatti();
				for(int i=0;i<piatti.length;i++) {
					carico= piatti[i].getRicetta().getCaricoLavoroPorz()[0]*piatti[i].getRicetta().getCaricoLavoroPorz()[1];
					qta=pren.getSelezioni().get(key);
					res+=carico * qta;
				}
			}
		}
		return res;
	}
	
	public static boolean okModCarico(int carico) {
		double acc=0;
		for(Prenotazione key:ListaPrenotazioni.initLista().getLista()) {
			if(key.getData().isEqual(LocalDate.now()))
				acc+=getCaricoPren(key);
		}
		
		if(carico>acc)
			return true;
		return false;
	}
	
	public static boolean okModPosti(int val, Locale l) {
		int acc=0;
		for(Prenotazione key:ListaPrenotazioni.initLista().getLista()) {
			if(key.getData().isEqual(LocalDate.now()))
				acc+=key.getNumCoperti();
		}
		if(val<acc)
			return false;
		return true;
	}
	
	private static int getNumero(Piatto p) {
		int res=0;
		Set<Selezionabile> selez;
		for(Prenotazione key:ListaPrenotazioni.initLista().getLista()) {
			if(key.getData().isEqual(LocalDate.now())) {
				selez=key.getSelezioni().keySet();
				for(Selezionabile sel:selez) {
					if(sel instanceof Piatto) {
						if(sel.equals(p)) 
							res+=key.getSelezioni().get(sel);
					}
					else {
						for(int i=0;i<sel.getPiatti().length;i++) {
							if(sel.getPiatti()[i].equals(p))
								res+=key.getSelezioni().get(sel);
						}
					}
						
				}
			}
		}
		return res;
	}
	
	public static boolean okModRicetta(Piatto p,Ricetta ric){
		RegistroMagazzino reg=RegistroMagazzino.creaRegistro();
		HashMap<Ingrediente, Double> lista=ric.getIngredienti();
		int num=getNumero(p);
		for(Ingrediente ingr:lista.keySet()) {
			boolean presente=reg.getGiacenze().get(ingr)!=null;
			boolean sufficiente=reg.getGiacenze().get(ingr)>lista.get(ingr)*num;
			if(!presente||!sufficiente) 
				return false;
		}
		return true;
	}
	
	public static boolean okModPrenotazione(Prenotazione p, ListaPrenotazioni lista, LocalDate data) {
		double carico=getCaricoPren(p);
		for(Prenotazione key:lista.getLista()) {
			if(key.getData().isEqual(data))
				carico+=getCaricoPren(key);
		}
		if(carico>CaricoSostenibile.initCarico().getValoreCarico())
			return false;
		return true;
	}
	
	public static boolean okModCoperti(Prenotazione p, ListaPrenotazioni lista, int num) {
		if(num<p.getNumCoperti())
			return true;
		double carico=getCaricoPren(p)+CaricoPersona.initCarico().getValoreCarico()*12/10*(num-p.getNumCoperti());
		for(Prenotazione key:lista.getLista()) {
			if(key.getData().isEqual(p.getData())&& !key.equals(p))
				carico+=getCaricoPren(key);
		}
		if(carico>CaricoSostenibile.initCarico().getValoreCarico())
		return false;
	return true;
		
	}
}
