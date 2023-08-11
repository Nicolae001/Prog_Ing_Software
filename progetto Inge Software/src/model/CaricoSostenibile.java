package model;

public final class CaricoSostenibile {
	
	private int valoreCarico;
private static CaricoSostenibile carico=null;
	
	private CaricoSostenibile(int val) {
		valoreCarico=val;
	}
	
	public static CaricoSostenibile impostaCarico(int val) {
		if(carico==null)
			carico=new CaricoSostenibile(val);
		return carico;
	}
	
	public int getValoreCarico() {
		return valoreCarico;
	}
	
	public void setValoreCarico(int val) {
		valoreCarico=val;
	}

}
