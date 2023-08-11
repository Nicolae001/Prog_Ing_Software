package model;

public class Locale {
	private static Locale loc=null;
	private int posti;
	
	private Locale(int posti) {
		this.posti=posti;
	}
	
	public int getPosti() {
		return this.posti;
	}
	
	public void setPosti(int posti) {
		this.posti=posti;
	}
	
	public static Locale getLocale() {
		if(loc==null)
			loc=new Locale(0);
		else
			System.out.println("Locale già creato");
		return loc;
	}
}
