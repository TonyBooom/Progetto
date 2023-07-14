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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email;
		String password;
		
		email= request.getParameter("username");
		password=request.getParameter("password");
		
		User utente = new User();
		UserDao udao = new UserDao();
		
		try {
			utente = udao.doRetrieveByKey(email);
			if(utente != null && utente.getEmail().compareTo("") != 0) {
				if(password.compareTo(utente.getPassword()) == 0) {
					
					boolean isAdmin = utente.isAdmin();

					if(isAdmin) {
						request.getSession().setAttribute("secure", "Admin");
						request.getSession().setAttribute("Utente loggato" , utente );       //Per motivi di sicurezza 
						response.sendRedirect("./CatalogAdmin.jsp");
					}
					else {
						request.getSession().setAttribute("secure", "Utente");
						request.getSession().setAttribute("Utente loggato" , utente );       //Per motivi di sicurezza
						response.sendRedirect("./Catalog.jsp");
					}
					
					
				}
				else {
					response.getWriter().append("Password non corretta");
				}
			}
			else {
				response.getWriter().append("Utente non registrato");
			}
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