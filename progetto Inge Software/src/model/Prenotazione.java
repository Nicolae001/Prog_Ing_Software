package model;
import java.util.HashMap;
import java.time.LocalDate;

public class Prenotazione {
	
	private String nomeCliente;
	private int numCoperti;
	private LocalDate data;
	private HashMap<Selezionabile,Integer> selezioni = new HashMap<Selezionabile, Integer>();
	
	
	public Prenotazione(String nome, int num, LocalDate data, HashMap<Selezionabile,Integer> sel) {
		nomeCliente=nome;
		numCoperti=num;
		this.data=data;
		selezioni=sel;
	}
	

	
	public HashMap<Selezionabile, Integer> getSelezioni() {
		return selezioni;
	}

	public void setSelezioni(HashMap<Selezionabile, Integer> selezioni) {
		this.selezioni = selezioni;
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
