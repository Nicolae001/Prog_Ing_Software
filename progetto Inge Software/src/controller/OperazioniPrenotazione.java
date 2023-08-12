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
	
	
	public void registra() {
		//da impelemetare
	}
	
	public void modifica() {
		//da implementare
	}
	
	public void cancella() {
		//da implementare
	}
}

