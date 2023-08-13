package controller;
import model.Utente;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import model.Gestore;
import model.Magazziniere;
import model.AddettoPrenotazioni;

public class RegistrazioneUtente {
	
	private String nome;
	private String password;
	private String ruolo;
	
	
	public RegistrazioneUtente(String nome, String pswd, String ruolo) {
		
		this.nome=nome;
		password=pswd;
		this.ruolo=ruolo;
	}
	
	private Utente creaUtente(){
		Utente res=null;
		if(ruolo.toLowerCase().equals("gestore"))
			res=Gestore.creaGestore(nome, password);
		if(ruolo.toLowerCase().equals("magazziniere"))
			res=new Magazziniere(nome, password);
		if(ruolo.toLowerCase().matches("addetto .*"))
				res= new AddettoPrenotazioni(nome, password);
		return res;
	}
	
	
	public void registra(Connection c)throws Exception {
		Utente u=creaUtente();
		String cl=u.getClass().toString();
		Statement st=c.createStatement();
		ResultSet res=st.executeQuery("insert into utente set nome=\""+nome+"\", password=\""+password+"\", ruolo=\""+cl+"\";" );
		res.close();
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
