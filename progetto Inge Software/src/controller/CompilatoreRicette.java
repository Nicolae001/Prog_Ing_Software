package controller;
import model.Ricetta;
import model.Gestore;
import model.Ingrediente;
import java.util.HashMap;
import model.ListaRicette;

public class CompilatoreRicette {
	
	private UtenteAutenticato utente;
	private HashMap<Ingrediente, Double> ingredienti= new HashMap<Ingrediente, Double>();
	private static CompilatoreRicette compil=null;
	private CompilatoreRicette(UtenteAutenticato u) {
		utente=u;
	}
	
	private boolean autenticato() {
		if(utente!=null)
			return utente.getStatoAutenticzione();
		return false;
					
	}
	
	private boolean autorizzato() {
		if(utente.getUtente() instanceof Gestore)
			return true;
		return false;
	}
	
	public static CompilatoreRicette initCompilatore(UtenteAutenticato u){
		if(compil==null) 
			compil=new CompilatoreRicette(u);
		return compil;
	}
	public void aggiungiIngredienti(Ingrediente ingr, Double val) {
		ingredienti.put(ingr, val);
	}
	
	public Ricetta creaRicetta(int porzioni, int[] caricoPorzione) throws Exception{
		Ricetta res=null;
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		if(!ingredienti.isEmpty())
			res= new Ricetta(ingredienti, porzioni,caricoPorzione);
		ingredienti.clear();
		return res; 
	}
	
	public void aggiungiRicettario(Ricetta ric) {
		ListaRicette.getRicette().aggiungiElem(ric);
	}
	
	
}
