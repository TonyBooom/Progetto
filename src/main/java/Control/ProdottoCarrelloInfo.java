

package Control;

public class ProdottoCarrelloInfo {
    private double prezzo;
    private String dataEvento;
    private String tipologia;

    // Costruttore
    public ProdottoCarrelloInfo() {
    	super();
    }
    
    public ProdottoCarrelloInfo(double prezzo, String dataEvento) {
        this.prezzo = prezzo;
        this.dataEvento = dataEvento;
    }

    // Metodi get e set
    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
}