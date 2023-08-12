package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import model.RegistroMagazzino;
import model.GenereAlimentare;
import model.Magazziniere;
import model.Causa;
import java.time.LocalDateTime;


public class LogicaRegistroMagazzino {
	
	private static LogicaRegistroMagazzino gestore=null;
	private RegistroMagazzino registro;
	private ArrayList<String> log;
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
	
	private GenereAlimentare[] estraiChiavi(HashMap<GenereAlimentare, Double> arg) {
		GenereAlimentare[] res;
		Set<GenereAlimentare> set=arg.keySet();
		res=set.toArray(new GenereAlimentare[set.size()]);
		return res;
		
	}
	public void preleva(Magazziniere m, GenereAlimentare alim, Double qta, Causa c) {
		registro.getGiacenze()
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
	
	
	private String creaLogPrelievo(String nome, String alimento, String unita, double qta, String causa,String data) {
		String res=data+": "+nome+" ha prelevato "+ qta+ " "+unita+ " per "+causa+"\n" ;
		return res;
	}
	
	private String creaLogAggiunta(String nome, String alimento, String unita, double qta, String causa, String data) {
		String res=data+": "+nome+" ha aggiunto "+ qta+ " "+unita+ " per "+causa+"\n" ;
		return res;
	}

	private synchronized void annotaPrelievo(Magazziniere m, GenereAlimentare[] alim, Double[] qta, Causa c) {
		StringBuilder sb= new StringBuilder();
		String nome=m.getNome();
		String causa=c.name();
		String data=LocalDateTime.now().toString();
		for(int i=0; i<alim.length;i++) {
			sb.append(creaLogPrelievo(nome,alim[i].getNome(), alim[i].getUniMisura(), qta[i],causa,data));
		}
		log.add(sb.toString());	
		
	}
	
	private synchronized void annotaAggiunta(Magazziniere m, GenereAlimentare[] alim, Double[] qta, Causa c) {
		StringBuilder sb= new StringBuilder();
		String nome=m.getNome();
		String causa=c.name();
		String data=LocalDateTime.now().toString();
		for(int i=0; i<alim.length;i++) {
			sb.append(creaLogAggiunta(nome,alim[i].getNome(), alim[i].getUniMisura(), qta[i],causa,data));
		}
		log.add(sb.toString());	
		
	}
}
