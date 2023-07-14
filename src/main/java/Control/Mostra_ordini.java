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
 * Servlet implementation class Ordine
 */
@WebServlet("/Mostra_ordini")
public class Mostra_ordini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mostra_ordini() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ordini = "ordini";	
		String admin = "profilo_admin.jsp";

		OrdineDAO odao = new OrdineDAO();
		ArrayList<OrdineBean> order = null;  
		String action = request.getParameter("action");
		
		if(action.equals("mostra")) {
			try {
				order = (ArrayList<OrdineBean>) odao.doRetrieveAll(null);
				request.setAttribute(ordini, order);
				RequestDispatcher rs = request.getRequestDispatcher(admin);
				rs.include(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(action.equals("data")) {
			
			try {
				order = (ArrayList<OrdineBean>) odao.doRetrieveAll("data_ordine");
				request.setAttribute(ordini, order);
				RequestDispatcher rs = request.getRequestDispatcher(admin);
				rs.include(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			
		}else if(action.equals("utente")) {
			
			try {
				order = (ArrayList<OrdineBean>) odao.doRetrieveAll("cod_utente");
				request.setAttribute(ordini, order);
				RequestDispatcher rs = request.getRequestDispatcher(admin);
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
