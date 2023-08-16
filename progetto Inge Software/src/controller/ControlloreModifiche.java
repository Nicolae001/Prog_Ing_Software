package controller;
import java.time.LocalDate;

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
			if(key.getData().isEqual(LocalDate.now()));
				acc+=getCaricoPren(key);
		}
		
		if(carico>acc)
			return true;
		return false;
	}
}
