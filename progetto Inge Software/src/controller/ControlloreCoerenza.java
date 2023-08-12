package controller;
import model.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class ControlloreCoerenza {
	
	private ControlloreCoerenza() {}
	
	public static boolean coerenzaMenuTema(MenuTematico menu) {
		boolean res=false;
		//da implementare
		return res;
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
