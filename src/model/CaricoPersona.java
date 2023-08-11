package model;

public final class CaricoPersona {
	
	private int valoreCarico;
	private static CaricoPersona carico=null;
	
	private CaricoPersona(int val) {
		valoreCarico=val;
	}
	
	public static CaricoPersona initCarico() {
		if(carico==null)
			carico=new CaricoPersona(0);
		return carico;
	}
	
	public int getValoreCarico() {
		return valoreCarico;
	}
	
	public void setValoreCarico(int val) {
		valoreCarico=val;
	}
}
