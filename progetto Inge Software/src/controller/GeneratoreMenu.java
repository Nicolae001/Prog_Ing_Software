package controller;
import model.Piatto;
import java.util.ArrayList;
import model.MenuCarta;

public class GeneratoreMenu {
	
	private static GeneratoreMenu generatore=null;
	private ArrayList<Piatto> piatti=new ArrayList<Piatto>();
	
	private GeneratoreMenu() {}
	
	public static GeneratoreMenu initGenMenu() {
		if(generatore==null)
			generatore=new GeneratoreMenu();
		return generatore;
	}
	
	public void aggiungiPiatto(Piatto p) {
		piatti.add(p);
	}
	
	public void aggiungiPiatto(Piatto[] p) {
		for(int i=0;i<p.length;i++)
			piatti.add(p[i]);
	}
	
	public MenuCarta genera() {
		MenuCarta res=null;
		//da implementare
		return res;
	}
	
	
}
