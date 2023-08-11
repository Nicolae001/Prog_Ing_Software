package model;


public class Gestore extends Utente {
	
	private static Gestore istanza=null;
	
	private Gestore(String nome, String password) {
		super(nome,password);
	
	}
	
	public static Gestore creaGestore(String nome, String pswd) {
		if(istanza==null) 
			istanza=new Gestore(nome, pswd);
		return istanza;
	}
	
	public void modificaNome(String nome) {
		setNome(nome);
	}
	
	
}
