package Model;

import java.util.ArrayList; 

public class User {
	
	private String email;
	private String password;
	private String nome;
	private String cognome;
	Boolean admin;
	ArrayList<ConsegnaBean> consegna = new ArrayList<>();
	ArrayList<PagamentoBean> pagamento = new ArrayList<>();

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<ConsegnaBean> getConsegna() {
		return consegna;
	}
	public void setConsegna(ArrayList<ConsegnaBean> consegna) {
		this.consegna = consegna;
	}
	public ArrayList<PagamentoBean> getPagamento() {
		return pagamento;
	}
	public void setPagamento(ArrayList<PagamentoBean> pagamento) {
		this.pagamento = pagamento;
	}
	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public String getCodiceFiscale() {
		return Codice_fiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		Codice_fiscale = codiceFiscale;
	}
	String Codice_fiscale;

	
	

}
