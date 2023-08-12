package controller;

import model.Magazziniere;

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
		if(utente!=null && utente.getUtente() instanceof Magazziniere)
			return true;
		return false;
	}
	
	
	public void registra() throws Exception {
		//da impelemetare
	}
	
	public void modifica() throws Exception{
		//da implementare
	}
	
	public void cancella() throws Exception{
		//da implementare
	}
}

