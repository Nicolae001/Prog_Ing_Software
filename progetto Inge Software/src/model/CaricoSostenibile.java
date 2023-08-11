package model;

public final class CaricoSostenibile {
	
	private int valoreCarico;
private static CaricoSostenibile carico=null;
	
	private CaricoSostenibile(int val) {
		valoreCarico=val;
	}
	
	public static CaricoSostenibile impostaCarico() {
		if(carico==null)
			carico=new CaricoSostenibile(0);
		return carico;
	}
	
	public int getValoreCarico() {
		return valoreCarico;
	}
	
	public void setValoreCarico(int val) {
		valoreCarico=val;
	}

}
