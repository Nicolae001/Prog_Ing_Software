package controller;
import model.*;
import java.util.HashMap;
import java.time.LocalDate;

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
	
	
	public void registra(Prenotazione p, ListaPrenotazioni lista) throws Exception {
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		for(LocalDate data:GiorniFestivi.getDate()) {
			if(data.isEqual(p.getData())) {
				System.out.println("Impossible procedere alla registrazione causa data festiva\n");
				return;
			}
		}
		if(ControlloreCoerenza.coerenzaNuovaPrenot(p, lista))
			lista.aggiungiElem(p);
	}
	
	public void modificaData(Prenotazione p, ListaPrenotazioni lista, LocalDate data) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		if(data.isBefore(LocalDate.now())) {
			System.out.println("Impossibile modificare data. La data inserita è incorretta\n");
			return;
		}
		long diff=p.getData().toEpochDay()-LocalDate.now().toEpochDay();
		if(diff<1) {
			System.out.println("Impossibile modificare data. Giorni di preavviso insufficienti\n");
			return;
		}
		
		if(ControlloreModifiche.okModPrenotazione(p,lista,data)) {
			System.out.println("Impossibile modificare data. La data specificata è già impegnata\n");
			return;
		}
		if(lista.getLista().contains(p)) {
			p.setData(data);	
		}
		else {
			System.out.println("Prenotazione non trovata\n");
		}
	}
	
	public void modificaNrCoperti(Prenotazione p, ListaPrenotazioni lista, int num) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		if(ControlloreModifiche.okModCoperti(p,lista,num)) {
			p.setNumCoperti(num);
		}
		else {
			System.out.println("Impossibile modificare il numero coperti a causa del carico previsto per la giornata");
		}
	}
	
	public void modificaSelezioni(Prenotazione p, HashMap<Selezionabile, Integer> selezioni) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		p.setSelezioni(selezioni);
	}
	
	public void cancella(Prenotazione p, ListaPrenotazioni lista) throws Exception{
		if(!autenticato())
			throw new AuthException();
		if(!autorizzato())
			throw new PermissionException();
		//da implementare
	}
}

