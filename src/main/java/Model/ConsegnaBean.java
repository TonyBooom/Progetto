package Model;

public class ConsegnaBean {
	
	int idconsegna;
	String via;
	int cap; 
	int numero;
	String citta;
	User utente;
	
	
	public User getUtente() {
		return utente;
	}
	public void setUtente(User utente) {
		this.utente = utente;
	}
	
	public int getIdconsegna() {
		return idconsegna;
	}
	public void setIdconsegna(int idconsegna) {
		this.idconsegna = idconsegna;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public int getCap() {
		return cap;
	}
	public void setCap(int cap) {
		this.cap = cap;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public ConsegnaBean() {
		super();
	}
	
	
	
	
}
