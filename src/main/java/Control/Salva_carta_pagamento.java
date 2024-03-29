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
 * Servlet implementation class Salva_carta_pagamento
 */

@WebServlet("/Salva_carta_pagamento")
public class Salva_carta_pagamento extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Salva_carta_pagamento() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			PagamentoBean bean = new PagamentoBean();
			PagamentoDAO pdao = new PagamentoDAO();
			
			bean.setNominativo(request.getParameter("intestatario"));
			bean.setCodiceCarta(request.getParameter("cod_carta"));
			bean.setCVV(Integer.parseInt(request.getParameter("cod_cvv")));
			bean.setMeseScadenza(Integer.parseInt(request.getParameter("month").substring(5, 7)));
			bean.setAnnoScadenza(Integer.parseInt(request.getParameter("month").substring(0, 4)));
			
			try {
				
				
				User ut = (User) request.getSession().getAttribute("Utente loggato");
				pdao.doSave(bean, ut);
				
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
