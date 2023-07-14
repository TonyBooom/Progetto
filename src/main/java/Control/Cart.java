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
	Carrello obj = (Carrello) request.getSession().getAttribute(carrello);

	if(obj == null) {
		obj = new Carrello();
		request.getSession().setAttribute(carrello, obj);
	}

	String action = request.getParameter("action");
	if(action != null) {
		switch (action.toLowerCase()) {
			case "add":
				handleAddAction(request, response, obj, carrello);
				break;
			case "view":
				handleViewAction(request, response, obj);
				break;
			case "sub":
				handleSubAction(request, response, obj, carrello);
				break;
			case "rmv":
				handleRmvAction(request, response, obj, carrello);
				break;
			case "rmvall":
				handleRmvAllAction(request, response, obj, carrello);
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


private void handleAddAction(HttpServletRequest request, HttpServletResponse response, Carrello obj, String carrello) throws ServletException, IOException {
	Integer id = Integer.parseInt(request.getParameter("id"));
	Integer qnt = Integer.parseInt(request.getParameter("qnt"));

		
	if(qnt == 0) {
		obj.updateCart(id, 0); // Rimuovi l'elemento se la quantità inserita è 0
		}

	if(obj.getCart().containsKey(id)) {
		obj.updateCart(id, qnt + obj.getCart().get(id)); // Modifica la quantità se l'oggetto è già presente nel carrello
		}
	
	else {
		obj.addToCart(id, qnt); // Aggiungi l'oggetto al carrello
	}
 
	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : "/Catalog.jsp";
	request.getSession().setAttribute(carrello, obj);
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
	dispatcher.forward(request, response);
}


private void handleViewAction(HttpServletRequest request, HttpServletResponse response, Carrello obj) throws ServletException, IOException {
	HashMap<ProdottoBean, ArrayList<Double>> ogg = new HashMap<>();
	ProdottoDAO pdao = new ProdottoDAO();


	for(Entry<Integer, Integer> entry : obj.getCart().entrySet()) {
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


private void handleSubAction(HttpServletRequest request, HttpServletResponse response, Carrello obj, String carrello) throws ServletException, IOException {
	Integer id = Integer.parseInt(request.getParameter("id"));
	Integer result = obj.getCart().get(id);
	
	result--;  //Rimozione dal carrello
	obj.updateCart(id, result);

	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
	if(destination != null) {
		request.getSession().setAttribute(carrello, obj);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
}

private void handleRmvAction(HttpServletRequest request, HttpServletResponse response, Carrello obj, String carrello) throws ServletException, IOException {
	Integer id = Integer.parseInt(request.getParameter("id"));
	Integer qnt = 0;
	obj.updateCart(id, qnt);

	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
	
	if(destination != null) {
 	request.getSession().setAttribute(carrello, obj);
 	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
 	dispatcher.forward(request, response);
 }
}


private void handleRmvAllAction(HttpServletRequest request, HttpServletResponse response, Carrello obj, String carrello) throws ServletException, IOException {
	
	obj = new Carrello();


	String destination = request.getParameter("provenienza").equals(carrello) ? "/Carrello.jsp" : null;
	if(destination != null) {
		request.getSession().setAttribute(carrello, obj);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destination);
		dispatcher.forward(request, response);
	}
  }
}