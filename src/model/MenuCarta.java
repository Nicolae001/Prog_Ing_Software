package model;
import java.time.LocalDate;

public class MenuCarta extends Lista<Piatto>{
	private LocalDate data;
	public MenuCarta(LocalDate data) {
		super();
		this.data=data;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data=data;
	}
	

}

