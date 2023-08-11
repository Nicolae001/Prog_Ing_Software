package model;
import java.time.LocalDate;

public class Piatto implements Selezionabile {

	private Ricetta ricetta;
	private String denominazione;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	public Piatto(String nome, LocalDate ini, LocalDate fine ) {
		denominazione=nome;
		dataInizio=ini;
		dataFine=fine;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Ricetta getRicetta() {
		return ricetta;
	}

	public void setRicetta(Ricetta ricetta) {
		this.ricetta = ricetta;
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
	
	
}
