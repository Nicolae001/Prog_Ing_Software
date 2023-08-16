package controller;
import model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class OperazioniGestore {
	
	private UtenteAutenticato utente;
	private static OperazioniGestore gestore=null;
	private static CompilatoreRicette compilatore;
	private OperazioniGestore(UtenteAutenticato u) {
		utente=u;
	}
	
	public static OperazioniGestore initGestore(UtenteAutenticato u) {
		if(gestore==null) {
			gestore=new OperazioniGestore(u);
			compilatore=CompilatoreRicette.initCompilatore(u);
		}
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
		if(ControlloreModifiche.okModCarico(caricoComplessivo))
			CaricoSostenibile.initCarico().setValoreCarico(caricoComplessivo);
		else
			System.out.println("Impossible modificare carico sostenibile, causa carico attuale delle prenotazioni\n");
		
		
	}
	
	public void assegnaPosti(int num, Locale l) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		//se con il nuovo numero di posti si va a superare il caricoSostenibile non aggiornare
		l.setPosti(num);
		
	}
		
	
	public void initListaBevande(Bevanda [] bevande) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		ListaBevande lista=ListaBevande.initListaBevande();
		for(int i=0;i<bevande.length;i++)
			lista.aggiungiElem(bevande[i]);
		
		
	}
	
	public void initListaExtra(GenereExtra[] extra) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		ListaExtra lista=ListaExtra.getListaExtra();
		for(int i=0;i<extra.length;i++)
			lista.aggiungiElem(extra[i]);
	}
	
	public void initConsumoBevande(Bevanda[] lista,Double[] consumi) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		ConsumoBevande consumo=ConsumoBevande.initConsumi();
		if(lista.length!=consumi.length) {
			System.out.println("Impossibile inizializzare lista bevande. Le lunghezze "
					+ "degli argomenti devono essere uguali\n");
			return;
		}
		for(int i=0;i<lista.length;i++)
			consumo.aggiungiElem(lista[i], consumi[i]);
			
		
	}
	
	public void initConsumoExtra(GenereExtra[] lista, Double[] consumi) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		ConsumoExtra consumo=ConsumoExtra.initConsumi();
		if(lista.length!=consumi.length) {
			System.out.println("Impossibile inizializzare lista bevande. Le lunghezze "
					+ "degli argomenti devono essere uguali\n");
			return;
		}
		for(int i=0;i<lista.length;i++)
			consumo.aggiungiElem(lista[i], consumi[i]);
	}
	
	public void assegnaRiccettaPiatto(Ricetta ric, Piatto p) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		//se oggi viene servito almeno un piatto p e mancano gli ingredienti neccessari non procedere al cambio
		p.setRicetta(ric);
	}
	
	public Piatto creaPiatto(String nome, String inizio, String fine) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate i=LocalDate.parse(inizio, formatter);
		LocalDate f=LocalDate.parse(fine,formatter);
		if(i.isBefore(f) || i.isEqual(f)) {
			Piatto p=new Piatto(nome,i,f);
			ListaPiatti.getPiatti().aggiungiElem(p);
			return p;
		}
		else {
			System.out.println("Non si possono creare piatti con date di inizio e fine non ordinate\n");
			return null;
		}
		
	}
	
	public void creaRiecetta(Ingrediente[] ingr, Double[] qta, int porzioni, int[] caricoPorz) {
		if(ingr.length!=qta.length) {
			System.out.println("Non posso registrare questa ricetta. Ci deve essere corrispondenza"
					+ " tra gli ingredienti e le rispettive quantità.\n");
			return;
		}
		for(int i=0;i<ingr.length;i++) {
			compilatore.aggiungiIngredienti(ingr[i], qta[i]);
		}
		try {
			compilatore.aggiungiRicettario(compilatore.creaRicetta(porzioni, caricoPorz));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public MenuTematico creaMenuTema(String nome, String inizio, String fine, Piatto[] piatti) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato()) 
			throw new PermissionException();
		MenuCarta carta=GeneratoreMenu.initGenMenu().genera();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		 LocalDate ini = LocalDate.parse(inizio, formatter);
		 LocalDate fin = LocalDate.parse(fine, formatter);
		 MenuTematico res=new MenuTematico(nome,ini,fin);
		 if(ControlloreCoerenza.coerenzaMenuTema(piatti,carta, ini,fin)) {
			for(int i=0;i<piatti.length;i++)
				res.aggiungiElem(piatti[i]);
		 }
		 else {
			 System.out.println("Impossibile creare menu tematico. I parametri passati non sono coerenti\n");
			 return null;
		 }
		return res;
	}
}
