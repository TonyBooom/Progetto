package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

/**

Servlet implementation class Cart
*/
@WebServlet("/Cart")
public class Cart extends HttpServlet {
private static final long serialVersionUID = 1L;

/**

@see HttpServlet#HttpServlet()
*/
public Cart() {
super();
}
/**

@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String carrello = "carrello";
	Carrello cart = (Carrello) request.getSession().getAttribute(carrello);

	if(cart == null) {
		cart = new Carrello();
		request.getSession().setAttribute(carrello, cart);
	}

	String action = request.getParameter("action");
	if(action != null) {
		switch (action.toLowerCase()) {
			case "add":
				handleAddAction(request, response, cart, carrello);
				break;
			case "view":
				handleViewAction(request, response, cart);
				break;
			case "sub":
				handleSubAction(request, response, cart, carrello);
				break;
			case "rmv":
				handleRmvAction(request, response, cart, carrello);
				break;
			case "rmvall":
				handleRmvAllAction(request, response, cart, carrello);
				break;
		}
}
}

/**

@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}


private void handleAddAction(HttpServletRequest request, HttpServletResponse response, Carrello cart, String carrello) throws ServletException, IOException {
	Integer id = Integer.parseInt(request.getParameter("id"));
	Integer qnt = Integer.parseInt(request.getParameter("qnt"));

		
	if(qnt == 0) {
		cart.updateCart(id, 0); // Rimuovi l'elemento se la quantità inserita è 0
		}

	if(cart.getCart().containsKey(id)) {
		cart.updateCart(id, qnt + cart.getCart().get(id)); // Modifica la quantità se l'oggetto è già presente nel carrello
		}
	
	else {
		cart.addToCart(id, qnt); // Aggiungi l'oggetto al carrello
	}
 
	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : "/Catalog.jsp";
	request.getSession().setAttribute(carrello, cart);
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
	dispatcher.forward(request, response);
}


private void handleViewAction(HttpServletRequest request, HttpServletResponse response, Carrello cart) throws ServletException, IOException {
	HashMap<ProdottoBean, ArrayList<Double>> ogg = new HashMap<>();
	ProdottoDAO pdao = new ProdottoDAO();


	for(Entry<Integer, Integer> entry : cart.getCart().entrySet()) {
		Double dou = (double) entry.getValue();
		ProdottoBean prod = new ProdottoBean();
 	
 	try {
 		prod = pdao.doRetrieveByKey(entry.getKey());
 	} catch (SQLException e) {
 		e.printStackTrace();
 	}
 	
 	ArrayList<Double> arr = new ArrayList<>();
 	
 	arr.add(dou);
 	arr.add(prod.getIva());
 	arr.add((double)prod.getPrezzo());
 	
 	ogg.put(prod, arr);
	}
 
	request.setAttribute("carrello_view", ogg);
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
 	dispatcher.forward(request, response);
}


private void handleSubAction(HttpServletRequest request, HttpServletResponse response, Carrello cart, String carrello) throws ServletException, IOException {
	Integer id = Integer.parseInt(request.getParameter("id"));
	Integer result = cart.getCart().get(id);
	
	result--;  //Rimozione dal carrello
	cart.updateCart(id, result);

	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
	if(destination != null) {
		request.getSession().setAttribute(carrello, cart);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}

private void handleRmvAction(HttpServletRequest request, HttpServletResponse response, Carrello cart, String carrello) throws ServletException, IOException {
	Integer id = Integer.parseInt(request.getParameter("id"));
	Integer qnt = 0;
	cart.updateCart(id, qnt);

	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
	
	if(destination != null) {
 	request.getSession().setAttribute(carrello, cart);
 	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
 	dispatcher.forward(request, response);
 }
}


private void handleRmvAllAction(HttpServletRequest request, HttpServletResponse response, Carrello cart, String carrello) throws ServletException, IOException {
	
	cart = new Carrello();


	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
	if(destination != null) {
		request.getSession().setAttribute(carrello, cart);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
  }
}