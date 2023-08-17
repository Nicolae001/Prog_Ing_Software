package controller;
import model.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class ControlloreCoerenza {
	
	private ControlloreCoerenza() {}
	
	private static boolean trovaInMenu(Piatto p, MenuCarta menu) {
		for(Piatto key:menu.getLista()) {
			boolean nomeOk=p.getDenominazione().equals(key.getDenominazione());
			boolean inizioOk=p.getDataInizio().isEqual(key.getDataInizio())||p.getDataInizio().isAfter(key.getDataInizio());
			boolean fineOk=p.getDataFine().isBefore(key.getDataFine())||p.getDataFine().isEqual(key.getDataFine());
			if(nomeOk&&inizioOk&&fineOk) 
				return true;
		}
		
		return false;
	}
	
	
	public static boolean coerenzaMenuTema(Piatto[] piatti, MenuCarta menu, LocalDate ini, LocalDate fine) {;
		double caricoTotale=0;
		double caricoPorzione;
		for(int i=0;i<piatti.length;i++) {
			caricoPorzione=piatti[i].getRicetta().getCaricoLavoroPorz()[0]/piatti[i].getRicetta().getCaricoLavoroPorz()[1];
			caricoTotale+=caricoPorzione;
			if(!trovaInMenu(piatti[i], menu)||caricoTotale>4/3)
				return false;
		}
		return true;
	}
	
	
	private static boolean coerenzaOrdine(Prenotazione p) {
		int num=p.getNumCoperti();
		HashMap<Selezionabile,Integer> sel=p.getSelezioni();
		Set<Selezionabile> set=sel.keySet();
		Integer [] val=set.toArray(new Integer[set.size()]);
		int tot=0;
		for(Integer v:val)
			tot+=v;
		if(num<=tot)
			return true;
		return false;
	}
	
	private static ArrayList<Prenotazione> inData(ListaPrenotazioni lista, LocalDate data) {
		ArrayList<Prenotazione> lst= new ArrayList<Prenotazione>();
		
		for(int i=0; i<lista.getDimLista();i++) {
			LocalDate dt=lista.getElem(i).getData();
			if(dt==data)
				lst.add(lista.getElem(i));
		}
		
		return lst;
	}
	
	private static int caricoPrenotazioni(ArrayList<Prenotazione> lista) {
		int num=0;
		for(Prenotazione p:lista)
			num=p.getNumCoperti();
		int res=num*CaricoPersona.initCarico().getValoreCarico();
		return res;
			
	}
	
	public static boolean coerenzaNuovaPrenot(Prenotazione p, ListaPrenotazioni lista) {
		if(!coerenzaOrdine(p))
			return false;
		int caricoAggiunto=caricoPrenotazioni(inData(lista,p.getData()))+p.getNumCoperti()*CaricoPersona.initCarico().getValoreCarico();
		if(caricoAggiunto>CaricoSostenibile.initCarico().getValoreCarico())
			return false;
		return true;
	}
	
	
	
}
