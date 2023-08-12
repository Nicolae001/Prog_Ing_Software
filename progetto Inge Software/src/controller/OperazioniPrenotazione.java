package controller;
import model.Prenotazione;
import model.AddettoPrenotazioni;
import model.ListaPrenotazioni;

public class OperazioniPrenotazione {
		
	private UtenteAutenticato utente=null;
	
	public OperazioniPrenotazione(UtenteAutenticato u) {
		utente=u;
	}
	
	private boolean autenticato() {
		if(utente!=null )
			return utente.getStatoAutenticzione();
		return false;
	}
	
	private boolean autorizzato() {
		if(utente!=null && utente.getUtente() instanceof AddettoPrenotazioni)
			return true;
		return false;
	}
	
	
	public void registra(Prenotazione p, ListaPrenotazioni lista ) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		if(ControlloreCoerenza.coerenzaNuovaPrenot(p, lista))
			lista.aggiungiElem(p);
	}
	
	public void modifica() throws Exception{
		//da implementare
	}
	
	public void cancella() throws Exception{
		//da implementare
	}
}

