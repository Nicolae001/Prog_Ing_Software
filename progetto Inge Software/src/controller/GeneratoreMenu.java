package controller;
import model.*;
import java.time.LocalDate;


public class GeneratoreMenu {
	
	private static GeneratoreMenu generatore=null;
	private MenuCarta allaCarta;
	
	private GeneratoreMenu() {}
	
	public static GeneratoreMenu initGenMenu() {
		if(generatore==null)
			generatore=new GeneratoreMenu();
		return generatore;
	}
	
	
	public MenuCarta genera() {
		LocalDate oggi=LocalDate.now();
		for(Piatto p:ListaPiatti.getPiatti().getLista()) {
			if(p.getDataInizio().isBefore(oggi) && p.getDataFine().isAfter(oggi))
				allaCarta.aggiungiElem(p);
		}
		
		return allaCarta;
	}
	
	
}
