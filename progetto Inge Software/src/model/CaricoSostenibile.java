package model;

public final class CaricoSostenibile {
	
	private int valoreCarico;
private static CaricoSostenibile carico=null;
	
	private CaricoSostenibile() {}
	
	public static CaricoSostenibile initCarico() {
		if(carico==null)
			carico=new CaricoSostenibile();
		return carico;
	}
	
	public int getValoreCarico() {
		return valoreCarico;
	}
	
	public void setValoreCarico(int val) {
		valoreCarico=val;
	}
	
	public void eliminaCarico() {
		carico=null;
	}
}
