package model;

 public abstract class Utente {
	
	private String nome;
	public String getNome() {
		return nome;
	}

	 void setNome(String nome) {
		this.nome = nome;
	}

	 String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	private String password;
	
	 Utente(String nome, String password) {
		this.nome=nome;
		this.password=password;
	}
	 
	
}
