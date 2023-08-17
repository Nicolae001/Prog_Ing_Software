package controller;
import model.Magazziniere;
import model.ListaSpesa;
import model.Ingrediente;
import model.GenereAlimentare;

import java.util.Set;
import java.util.HashMap;



public class OperazioniMagazziniere {
	
	private UtenteAutenticato utente=null;
	
	public OperazioniMagazziniere(UtenteAutenticato u) {
		utente=u;
	}
	
	private boolean autenticato() {
		if(utente!=null )
			return utente.getStatoAutenticzione();
		return false;
	}
	
	private boolean autorizzato() {
		if(utente!=null && utente.getUtente() instanceof Magazziniere)
			return true;
		return false;
	}
	
	public  void acquista(ListaSpesa lista, LogicaRegistroMagazzino gestore) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		Magazziniere m=(Magazziniere)utente.getUtente();
		for(GenereAlimentare alim : lista.getLista().keySet())
			gestore.aggiungi(m, alim, lista.getLista().get(alim).doubleValue(), Causa.ACQUISTO);
		
	}
	
	public synchronized void portaIngredientiCucina(Ingrediente[] ingr, Double[] qta, SimulazioneCucina cucina, LogicaRegistroMagazzino gestore) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		Magazziniere m=(Magazziniere)utente.getUtente();
		gestore.preleva(m, ingr, qta, Causa.INGR_PER_CUCINA);
		cucina.prendiIngredienti(ingr,qta);
		
	}
	
	private Ingrediente[] estraiIngred(HashMap<Ingrediente, Double> arg) {
		Set<Ingrediente> set=arg.keySet();
		Ingrediente[] res= set.toArray(new Ingrediente[set.size()]);
		return res;
	}
	
	private Double[] estraiQta(HashMap<Ingrediente, Double> arg) {
		Set<Ingrediente> set=arg.keySet();
		Double[] res=set.toArray(new Double[set.size()]);
		return res;
	}
	
	
	public synchronized void portaIngredientiMag(SimulazioneCucina cucina, LogicaRegistroMagazzino gestore) throws Exception{
		
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		Magazziniere m=(Magazziniere)utente.getUtente();
		HashMap<Ingrediente, Double>inut=cucina.ritornaIngrendienti();
		Ingrediente[] ingr=estraiIngred(inut);
		Double[] qta= estraiQta(inut);
		gestore.aggiungi(m, ingr, qta,Causa.INGR_DALLA_CUCINA);
	

	}
	
	public synchronized void  altriPrelievi(GenereAlimentare alim, Double qta, LogicaRegistroMagazzino gestore, Causa c) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		Magazziniere m=(Magazziniere)utente.getUtente();
		gestore.preleva(m, alim, qta, c);
		
	}
	
	
}
