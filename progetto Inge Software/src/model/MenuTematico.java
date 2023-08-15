package model;
import java.time.LocalDate;
public class MenuTematico extends Lista<Piatto> implements Selezionabile{

	private String nome;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	public MenuTematico(String nome, LocalDate inizio, LocalDate fine) {
		super();
		this.nome=nome;
		dataInizio=inizio;
		dataFine=fine;
	}
	
	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public Piatto[] getPiatti() {
		return lista.toArray(new Piatto[lista.size()]);
	}
	
	public Piatto getPiatto() {
		return null;
	}
}
