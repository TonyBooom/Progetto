package Control;

import java.io.IOException; 
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

/**
 * Servlet implementation class Salva_ind_consegna
 */
@WebServlet("/Salva_ind_consegna")
public class Salva_ind_consegna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Salva_ind_consegna() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ConsegnaBean cbean = new ConsegnaBean();
			ConsegnaDAO cdao = new ConsegnaDAO();
			
			cbean.setVia(request.getParameter("via"));
			cbean.setNumero(Integer.parseInt(request.getParameter("number")));
			cbean.setCap(Integer.parseInt(request.getParameter("cap")));
			cbean.setCitta(request.getParameter("citta"));
			
			User ut = (User) request.getSession().getAttribute("Utente loggato");
			try {
				cdao.doSave(cbean, ut);
				
				UserDao udao = new UserDao();
				ut = udao.doRetrieveByKey(ut.getEmail());
				request.getSession().setAttribute("Utente loggato", ut); // Salvo l'utente nella sessione per poter vedere subito il cambiamento
				response.sendRedirect("Profilo_utente.jsp");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
