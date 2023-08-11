package controller;
import model.Utente;
public class UtenteAutenticato {
	
	private Utente utente;
	private boolean autenticato=false;
	
	private UtenteAutenticato(Utente u) {
		utente=u;
	}
	
	public static synchronized UtenteAutenticato autenticaUtente(Utente u) {
		UtenteAutenticato res=null;
		UtenteAutenticato user=new UtenteAutenticato(u);
		if(verificaUtente(u)) {
			res=user;
			res.autenticato=true;
		}
		return res;
	}
	
	private static synchronized boolean verificaUtente (Utente u) {
		boolean res=true;
		//da implementare
		return res;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public boolean getStatoAutenticzione() {
		return autenticato;
	}
	
	
}
