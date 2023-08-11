package model;
import java.time.LocalTime;

public class Piatto implements Selezionabile {

	private Ricetta ricetta;
	private LocalTime dataInizio;
	private LocalTime dataFine;
	
	public Piatto(Ricetta ric, LocalTime ini, LocalTime fine ) {
		ricetta=ric;
		dataInizio=ini;
		dataFine=fine;
	}

	public Ricetta getRicetta() {
		return ricetta;
	}

	public void setRicetta(Ricetta ricetta) {
		this.ricetta = ricetta;
	}

	public LocalTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalTime dataFine) {
		this.dataFine = dataFine;
	}
	
	
}
