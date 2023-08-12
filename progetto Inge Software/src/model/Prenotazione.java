package model;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

public class Prenotazione {
	
	private String nomeCliente;
	private int numCoperti;
	private LocalDate data;
	private HashMap<Selezionabile,Integer> selezioni = new HashMap<Selezionabile, Integer>();
	
	public HashMap<Selezionabile, Integer> getSelezioni() {
		return selezioni;
	}

	public void setSelezioni(HashMap<Selezionabile, Integer> selezioni) {
		this.selezioni = selezioni;
	}

	private static ArrayList<Prenotazione> listaPrenotazioni=new ArrayList<Prenotazione>();
	
	private Prenotazione(String nome, int num, LocalDate data, HashMap<Selezionabile,Integer> sel) {
		nomeCliente=nome;
		numCoperti=num;
		this.data=data;
		selezioni=sel;
	}
	
	public static void creaPrenotazione(String nome, int num, LocalDate data, HashMap<Selezionabile,Integer> sel) {
		Prenotazione p= new Prenotazione(nome, num,data, sel);
		listaPrenotazioni.add(p);
	}

	public static void rimuoviPrenotazione(Prenotazione p) {
		listaPrenotazioni.remove(p);
	}
	
	public static Prenotazione getPrenotazione(int num) {
		return listaPrenotazioni.get(num);
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getNumCoperti() {
		return numCoperti;
	}

	public void setNumCoperti(int numCoperti) {
		this.numCoperti = numCoperti;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
