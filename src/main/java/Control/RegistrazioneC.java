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
 * Servlet implementation class RegistrazioneC
 */
@WebServlet("/RegistrazioneC")
public class RegistrazioneC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneC() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		UserDao udao = new UserDao();
		User bean = new User();		
		
		
		
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		bean.setNome(request.getParameter("nome"));
		bean.setCognome(request.getParameter("cognome"));
		bean.setCodiceFiscale(request.getParameter("codicefiscale"));
		
		
		try {
				udao.doSave(bean);
				response.sendRedirect("Catalog.jsp");
			
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
