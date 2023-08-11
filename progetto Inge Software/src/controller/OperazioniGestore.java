package controller;
import model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class OperazioniGestore {
	
	private UtenteAutenticato utente;
	private static OperazioniGestore gestore=null;
	
	private OperazioniGestore(UtenteAutenticato u) {
		utente=u;
	}
	
	public static OperazioniGestore initGestore(UtenteAutenticato u) {
		if(gestore==null)
			gestore=new OperazioniGestore(u);
		return gestore;
	}
	
	private boolean autenticato(){
		if(utente!=null )
			return utente.getStatoAutenticzione();
		return false;
	}
	
	private boolean autorizzato() {
		if(utente.getUtente() instanceof Gestore)
			return true;
		return false;
	}
	
	public void impostaCaricoLavoro(int carico) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) {
			throw new PermissionException();
		}
		CaricoPersona c=CaricoPersona.initCarico();
		c.setValoreCarico(carico);
		int caricoComplessivo=c.getValoreCarico()*Locale.getLocale().getPosti()*120/100;
		CaricoSostenibile.impostaCarico(caricoComplessivo);
		
		
	}
	
	public void assegnaPosti(int num) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		Locale l=Locale.getLocale();
		l.setPosti(num);
		
	}
		
	
	public void initListaBevande() throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		//da implementare
	}
	
	public void initListaExtra() throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		//da implementare
	}
	
	public void initConsumoBevande() throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		// da implementare
	}
	
	public void initConsumoExtra() throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		// da implementare
	}
	
	public void assegnaRiccettaPiatto(Ricetta ric, Piatto p) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		p.setRicetta(ric);
	}
	
	public Piatto creaPiatto(String nome, String inizio, String fine) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate i=LocalDate.parse(inizio, formatter);
		LocalDate f=LocalDate.parse(fine,formatter);
		if(i.isBefore(f) || i.isEqual(f))
			return new Piatto(nome,i,f);
		else {
			System.out.println("Non si possono creare piatti con date di inizio e fine non ordinate");
			return null;
		}
	}
}
