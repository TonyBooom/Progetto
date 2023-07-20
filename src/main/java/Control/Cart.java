package Control;

import java.io.IOException;   
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Cart() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String carrello = "carrello";
        Carrello cart = (Carrello) request.getSession().getAttribute(carrello);


        if (cart == null) {
            cart = new Carrello();
            request.getSession().setAttribute(carrello, cart);
        }

        String action = request.getParameter("action");
        if (action != null) {
            switch (action.toLowerCase()) {
                case "add":
                    handleAddAction(request, response, cart, carrello);
                    break;
                case "view":
                    handleViewAction(request, response, cart);
                    break;
                case "sub":
                    try {
                        handleSubAction(request, response, cart, carrello);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendRedirect("error.jsp"); 
                    }
                    break;
                case "rmv":
                    try {
                        handleRmvAction(request, response, cart, carrello);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendRedirect("error.jsp"); 
                    }
                    break;
                case "rmvall":
                    handleRmvAllAction(request, response, cart, carrello);
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void handleAddAction(HttpServletRequest request, HttpServletResponse response, Carrello cart,
            String carrello) throws ServletException, IOException {
    	
        String idParam = request.getParameter("codprod");
        String qntParam = request.getParameter("qnt");
        String favorite = request.getParameter("favorite");
        String dataEvento = request.getParameter("dataEvento");

      

        try {
            Integer id = Integer.parseInt(idParam);
            Integer qnt = Integer.parseInt(qntParam);

            ProdottoBean prodotto = new ProdottoDAO().doRetrieveByKey(id);
            
            ProdottoCarrelloInfo carrelloInfo = new ProdottoCarrelloInfo();
            carrelloInfo.setDataEvento(dataEvento);
            carrelloInfo.setTipologia(favorite);
            
            
            
            prodotto.setCarrelloInfo(carrelloInfo);
            
            
            
            // Aggiorna il prezzo del prodotto in base alla scelta "favorite"
            if (favorite != null) {
                if (favorite.equalsIgnoreCase("Silver")) {
                    prodotto.setPrezzo((prodotto.getPrezzoSilver())); 
                } else if (favorite.equalsIgnoreCase("Gold")) {
                    prodotto.setPrezzo((prodotto.getPrezzoGold())); 
                }
            }
            

            if(qnt == 0) {
				cart.updateCart(prodotto, 0);; // Se la quantit� che viene inserita sar� ugugale a 0 verr� implciata la sua eliminazione
			}
			
			// Se l'oggetto esiste allora sar� una modifica del carrello fatta da catalogo, cambiando la quantit� 
			if(cart.getQuantityById(id) > 0) {
				
				cart.updateCart(prodotto, qnt + cart.getQuantityById(id)); }
			else {
				cart.addToCart(prodotto, qnt);
			}

            String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : "/Catalog.jsp";
            request.getSession().setAttribute(carrello, cart);
            
            request.getSession().setAttribute("favorite", favorite);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
            dispatcher.forward(request, response);

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
        }
    }
    
   

    private void handleViewAction(HttpServletRequest request, HttpServletResponse response, Carrello cart)
            throws ServletException, IOException {
    	
        List<CarrelloItem> carrelloItems = cart.getItems();
        HashMap<ProdottoBean, ArrayList<Double>> ogg = new HashMap<>();

        for (CarrelloItem item : carrelloItems) {
            ProdottoBean prod = item.getProdotto();
            int qnt = item.getQuantita();

            ArrayList<Double> arr = new ArrayList<>();
            float price = prod.getPrezzo();

            // Calcola il prezzo scontato se necessario in base al tipo "favorite"
            String favorite = (String) request.getSession().getAttribute("favorite");
            if (favorite != null) {
                if (favorite.equalsIgnoreCase("Silver")) {
                    price = (float) (prod.getPrezzo() * 0.02 * 100);
                } else if (favorite.equalsIgnoreCase("Gold")) {
                    price = (float) (prod.getPrezzo() * 0.03 * 100);
                }
            }

            arr.add((double) qnt);
            arr.add(prod.getIva());
            arr.add((double) price);
            ogg.put(prod, arr);
        }

        request.setAttribute("carrello_view", ogg);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
        dispatcher.forward(request, response);
    }
    
    private void handleSubAction(HttpServletRequest request, HttpServletResponse response, Carrello cart,
            String carrello) throws ServletException, IOException, SQLException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        ProdottoBean prod = new ProdottoDAO().doRetrieveByKey(id);

        // Controlla se è stato selezionato un nuovo pacchetto e/o una nuova data di prenotazione
        String newPackage = request.getParameter("newPackage");
        String newDataEvento = request.getParameter("newDataEvento");
        
        if (newPackage != null) {
            prod.getCarrelloInfo().setTipologia(newPackage);
        }
        if (newDataEvento != null) {
            prod.getCarrelloInfo().setDataEvento(newDataEvento);
        }

        // Incrementa la quantità del prodotto nel carrello
        int newQuantity = cart.getQuantityById(prod.getQuantita()) + 1;
        cart.updateCart(prod, newQuantity);

        String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
        if (destination != null) {
            request.getSession().setAttribute(carrello, cart);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
            dispatcher.forward(request, response);
        }
    }

    private void handleRmvAction(HttpServletRequest request, HttpServletResponse response, Carrello cart,
            String carrello) throws ServletException, IOException, SQLException {
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Prodotto da rimuovere "+id);
        
        cart.removeFromCart(id);

        if (request.getParameter("provenienza").equals("carrello")) {
            request.getSession().setAttribute("carrello", cart);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
            dispatcher.forward(request, response);
        }
    }
    

    private void handleRmvAllAction(HttpServletRequest request, HttpServletResponse response, Carrello cart,
            String carrello) throws ServletException, IOException {
    	
        cart = new Carrello();

        String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
        if (destination != null) {
            request.getSession().setAttribute(carrello, cart);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
            dispatcher.forward(request, response);
        }
    }
}