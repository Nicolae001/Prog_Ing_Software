package model;

abstract class GenereAlimentare {
	private String nome;
	private String uniMisura;
	private int qta;
	
	GenereAlimentare(String nome, String unita, int qta){
		this.nome=nome;
		uniMisura=unita;
		this.qta=qta;
	}

	public String getUniMisura() {
		return uniMisura;
	}

	public void setUniMisura(String uniMisura) {
		this.uniMisura = uniMisura;
	}

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	public String getNome() {
		return nome;
	}

	
}
