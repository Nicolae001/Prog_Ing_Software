package model;
import java.util.HashMap;

public class Ricetta {
	
	private HashMap<Ingrediente, Double> ingredienti=new HashMap<Ingrediente, Double>();
	private int porzioni;
	private int[] caricoLavoroPorz;
	
	public Ricetta(HashMap<Ingrediente,Double> ingr, int por,int[] fraz ) throws Exception {
		if (fraz.length!=2)
			throw new Exception("La frazione deve essere costituita da due interi");
		ingredienti=ingr;
		porzioni=por;
		caricoLavoroPorz=fraz;
	}

	public HashMap<Ingrediente, Double> getIngredienti() {
		return ingredienti;
	}

	public void aggiungiIngrediente(Ingrediente ingr, Double qta ) {
		ingredienti.put(ingr, qta);
	}
	
	public void rimouviIngrediente(Ingrediente ingr) {
		ingredienti.remove(ingr);
	}
	

	public int getPorzioni() {
		return porzioni;
	}

	public void setPorzioni(int porzioni) {
		this.porzioni = porzioni;
	}

	public int[] getCaricoLavoroPorz() {
		return caricoLavoroPorz;
	}

	public void setCaricoLavoroPorz(int[] caricoLavoroPorz) {
		this.caricoLavoroPorz = caricoLavoroPorz;
	}
	
	
	
}
