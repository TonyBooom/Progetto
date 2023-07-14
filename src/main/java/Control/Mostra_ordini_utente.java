package Control;

import java.io.IOException; 
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

/**
 * Servlet implementation class Mostra_ordini_utente
 */
@WebServlet("/Mostra_ordini_utente")
public class Mostra_ordini_utente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mostra_ordini_utente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		OrdineDAO odao = new OrdineDAO();
		ArrayList<OrdineBean> orders = null;  
		String action = request.getParameter("action");
		
		if(action.equals("mostra")) {
			try {
				User ubean = (User)request.getSession().getAttribute("Utente loggato");
				orders = (ArrayList<OrdineBean>) odao.doRetrieveAllByUtente(ubean.getEmail());
				request.setAttribute("ordini", orders);
				RequestDispatcher rs = request.getRequestDispatcher("Profilo_utente.jsp");
				rs.include(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
