package controller;
import model.Utente;

public class RegistrazioneUtente {
	
	private String nome;
	private String password;
	private String ruolo;
	
	
	private RegistrazioneUtente(String nome, String pswd, String ruolo) {
		
		this.nome=nome;
		password=pswd;
		this.ruolo=ruolo;
	}
	
	private Utente creaUtente(){
		Utente res=null;
		//da implementare
		return res;
	}
	
	public void registraUtente() {
		creaUtente();
		//da implementare
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
}
