package model;
import java.util.HashMap;

public class RegistroMagazzino {

	private HashMap<GenereAlimentare, Double> giacenze= new HashMap<GenereAlimentare, Double>();
	private static RegistroMagazzino registro=null;
	
	private RegistroMagazzino() {}
	
	public static RegistroMagazzino creaRegistro() {
		if(registro==null)
			registro=new RegistroMagazzino();
		return registro;
	}

	public HashMap<GenereAlimentare, Double> getGiacenze() {
		return giacenze;
	}

	public void setGiacenze(HashMap<GenereAlimentare, Double> giacenze) {
		this.giacenze = giacenze;
	}
	
	public void eliminaRegistro() {
		registro=null;
	}
	
	
}
