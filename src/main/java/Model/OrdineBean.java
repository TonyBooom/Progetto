package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdineBean {
	
	PagamentoBean codPagamento;
	ConsegnaBean codConsegna;
	User codUtente;
	int idOrdine;
	Date dataOrdine;
	String statoOrdine;
	HashMap<ProdottoBean, ArrayList<Double>> composizione = new HashMap<>();  // Posizione 0 ci mettiamo quantit�, Posizione 1 ci mettiamo Iva, Posizione 2 ci mettiamo Prezzo
	Double prezzoTotale;
	
	//come gestiamo l'iva: insieme al prodotto salvato, salviamo anche la quantit�, l'iva ed il prezzo al momento dell'acquisto
	
	public OrdineBean() {
		super();
	}
	
	
	
	public Double getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setPrezzoTotale(Double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	public HashMap<ProdottoBean, ArrayList<Double>> getComposizione() {
		return composizione;
	}
	public void setComposizione(HashMap<ProdottoBean, ArrayList<Double>> composizione) {
		this.composizione = composizione;
	}
	
	public PagamentoBean getCodPagamento() {
		return codPagamento;
	}
	public void setCodPagamento(PagamentoBean codPagamento) {
		this.codPagamento = codPagamento;
	}
	public ConsegnaBean getCodConsegna() {
		return codConsegna;
	}
	public void setCodConsegna(ConsegnaBean codConsegna) {
		this.codConsegna = codConsegna;
	}
	public User getCodUtente() {
		return codUtente;
	}
	public void setCodUtente(User codUtente) {
		this.codUtente = codUtente;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public Date getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public String getStatoOrdine() {
		return statoOrdine;
	}
	public void setStatoOrdine(String statoOrdine) {
		this.statoOrdine = statoOrdine;
	}
	
	
}
