package controller;
import java.util.*;
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
	
	private Double[] estraiValori(HashMap<GenereAlimentare, Double> arg) {
		Double[] res;
		Collection<Double> list=arg.values();
		res=list.toArray(new Double[list.size()]);
		return res;
	}
	
	private int getPosizione(GenereAlimentare[] lista, String arg) {
		int res=0;
		for(int i=0;i<lista.length;i++) {
			if(lista[i].getNome().equals(arg))
				break;
			res++;
		}
		return res;
	}
	
	public void preleva(Magazziniere m, GenereAlimentare alim, Double qta, Causa c) {
		GenereAlimentare[] alimenti=estraiChiavi(registro.getGiacenze());
		Double[] disponibilita=estraiValori(registro.getGiacenze());
		int pos=getPosizione(alimenti,alim.getNome());
		disponibilita[pos]=disponibilita[pos]-qta;
		HashMap<GenereAlimentare, Double> giacenze=new HashMap<GenereAlimentare, Double>();
		for(int i=0;i<alimenti.length;i++) {
			giacenze.put(alimenti[i],disponibilita[i]);
		}
		registro.setGiacenze(giacenze);
		annotaPrelievo(m,alim,qta,c);
		
	}
	
	public void preleva(Magazziniere m, GenereAlimentare[] alim, Double[] qta,Causa c) {
		if(alim.length!=qta.length) {
			System.out.println("Impossibile procedere alla registrazione prelievo. Le"
					+ "lunghezze degli argomenti differiscono");
			return;
		}
		
		for(int i=0;i<alim.length;i++)
			preleva(m,alim[i],qta[i],c);
		
	}
	
	public synchronized void aggiungi(Magazziniere m,GenereAlimentare alim, Double qta,Causa c){
		GenereAlimentare[] alimenti=estraiChiavi(registro.getGiacenze());
		Double[] disponibilita=estraiValori(registro.getGiacenze());
		int pos=getPosizione(alimenti,alim.getNome());
		disponibilita[pos]=disponibilita[pos]+qta;
		HashMap<GenereAlimentare, Double> giacenze=new HashMap<GenereAlimentare, Double>();
		for(int i=0;i<alimenti.length;i++) {
			giacenze.put(alimenti[i],disponibilita[i]);
		}
		registro.setGiacenze(giacenze);
		annotaAggiunta(m,alim,qta,c);
	}
	
	public synchronized void aggiungi(Magazziniere m,GenereAlimentare[] alim, Double[] qta, Causa c){
		if(alim.length!=qta.length) {
			System.out.println("Impossibile procedere alla registrazione aggiunta. Le"
					+ "lunghezze degli argomenti differiscono");
			return;
		}
		
		for(int i=0;i<alim.length;i++)
			preleva(m,alim[i],qta[i],c);
	}
	
	
	private String creaLogPrelievo(String nome, String alimento, String unita, double qta, String causa,String data) {
		String res=data+": "+nome+" ha prelevato "+ qta+ " "+unita+ " per "+causa+"\n" ;
		return res;
	}
	
	private String creaLogAggiunta(String nome, String alimento, String unita, double qta, String causa, String data) {
		String res=data+": "+nome+" ha aggiunto "+ qta+ " "+unita+ " per "+causa+"\n" ;
		return res;
	}

	private synchronized void annotaPrelievo(Magazziniere m, GenereAlimentare alim, Double qta, Causa c) {
		StringBuilder sb= new StringBuilder();
		String nome=m.getNome();
		String causa=c.name();
		String data=LocalDateTime.now().toString();
		sb.append(creaLogPrelievo(nome,alim.getNome(), alim.getUniMisura(), qta,causa,data));
		log.add(sb.toString());	
		
	}
	
	private synchronized void annotaAggiunta(Magazziniere m, GenereAlimentare alim, Double qta, Causa c) {
		StringBuilder sb= new StringBuilder();
		String nome=m.getNome();
		String causa=c.name();
		String data=LocalDateTime.now().toString();
		sb.append(creaLogAggiunta(nome,alim.getNome(), alim.getUniMisura(), qta,causa,data));
		log.add(sb.toString());	
		
	}
}
