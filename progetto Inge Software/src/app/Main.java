package app;
import controller.*;
import model.*;
import java.time.LocalDate;


public class Main {
	
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		OperazioniGestore op=OperazioniGestore.initGestore(UtenteAutenticato.autenticaUtente(Gestore.creaGestore("Nicolae", "Password")));
		op.impostaCaricoLavoro(4);
		
		
		}
	
}
