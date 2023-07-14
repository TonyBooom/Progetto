package Model;

public class PagamentoBean {
	
	int idPagamento;
	String nominativo;
	int CVV;
	int meseScadenza;
	String codiceCarta;
	int annoScadenza;
	User utente;
	

	public PagamentoBean() {
		super();
	}
	
	
	public User getUtente() {
		return utente;
	}
	public void setUtente(User utente) {
		this.utente = utente;
	}
	
	public int getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	public int getCVV() {
		return CVV;
	}
	public void setCVV(int CVV) {
		this.CVV = CVV;
	}
	public int getMeseScadenza() {
		return meseScadenza;
	}
	public void setMeseScadenza(int meseScadenza) {
		this.meseScadenza = meseScadenza;
	}
	public String getCodiceCarta() {
		return codiceCarta;
	}
	public void setCodiceCarta(String codiceCarta) {
		this.codiceCarta = codiceCarta;
	}
	public int getAnnoScadenza() {
		return annoScadenza;
	}
	public void setAnnoScadenza(int annoScadenza) {
		this.annoScadenza = annoScadenza;
	}
	
}
