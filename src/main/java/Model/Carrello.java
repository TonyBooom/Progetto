package Model;


import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private List<CarrelloItem> items;

    public Carrello() {
        items = new ArrayList<>();
    }

    public List<CarrelloItem> getItems() {
        return items;
    }

    public void addToCart(ProdottoBean prodotto, int quantita) {
        for (CarrelloItem item : items) {
            if (item.getProdotto().getCodProdotto() == prodotto.getCodProdotto()) {
                item.setQuantita(item.getQuantita() + quantita);
                return;
            }
        }

        // Se il prodotto non è ancora nel carrello, lo aggiungiamo
        items.add(new CarrelloItem(prodotto, quantita));
    }

    public void removeFromCart(int codProdotto) {
        items.removeIf(item -> item.getProdotto().getCodProdotto() == codProdotto);
    }

    public void updateCart(ProdottoBean prodotto, int quantita) {
        for (CarrelloItem item : items) {
            if (item.getProdotto().getCodProdotto() == prodotto.getCodProdotto()) {
                item.setQuantita(quantita);
                return;
            }
        }
    }
    
    public int getQuantityById(int productId) {
        for (CarrelloItem item : items) {
            if (item.getProdotto().getCodProdotto() == productId) {
                return item.getQuantita();
            }
        }
        return 0; // Restituisce 0 se il prodotto non è presente nel carrello
    }
}