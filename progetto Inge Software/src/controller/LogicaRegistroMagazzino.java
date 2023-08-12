package controller;
import model.RegistroMagazzino;
import model.GenereAlimentare;
import model.Magazziniere;
import model.Causa;

public class LogicaRegistroMagazzino {
	
	private static LogicaRegistroMagazzino gestore=null;
	private RegistroMagazzino registro;
	private String log;
	private LogicaRegistroMagazzino(RegistroMagazzino r) {
		registro=r;
	}
	
	
	public static LogicaRegistroMagazzino initRegistro(RegistroMagazzino r) {
		if(gestore==null)
			gestore=new LogicaRegistroMagazzino(r);
		return gestore;
	}
	
	public void eliminaRegistro() {
		gestore=null;
	}
	
	public void preleva(Magazziniere m, GenereAlimentare alim, Double qta, Causa c) {
		//da implementare
	}
	
	public void preleva(Magazziniere m, GenereAlimentare[] alim, Double[] qta,Causa c) {
		//da implementare
	}
	
	public synchronized void aggiungi(Magazziniere m,GenereAlimentare alim, Double qta,Causa c){
		//da implementare
	}
	
	public synchronized void aggiungi(Magazziniere m,GenereAlimentare[] alim, Double[] qta, Causa c){
		//da implementare
	}
	
	private synchronized void annota(Magazziniere m, Causa c) {
		//da implementare
	}
}
