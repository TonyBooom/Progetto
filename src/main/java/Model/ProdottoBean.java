package Model;

public class ProdottoBean {
	
	int codProdotto;
	String nome;
	float prezzo;
	String descrizione;
	ImmagineBean immagine;
	int rimosso;
	CategoriaBean categoria;
	int quantita;
	double iva;
	
	
	public ProdottoBean() {
		super();
	}
	
	
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	
	public int getCodProdotto() {
		return codProdotto;
	}
	public void setCodProdotto(int codProdotto) {
		this.codProdotto = codProdotto;
	}
	public CategoriaBean getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaBean categoria) {
		this.categoria = categoria;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public ImmagineBean getImmagine() {
		return immagine;
	}
	public void setImmagine(ImmagineBean immagine) {
		this.immagine = immagine;
	}
	public int getRimosso() {
		return rimosso;
	}
	public void setRimosso(int rimosso) {
		this.rimosso = rimosso;
	}
	
}
