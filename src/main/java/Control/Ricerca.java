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

@WebServlet("/Ricerca")
public class Ricerca extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    	String prodotto = request.getParameter("prodotto");
    	ProdottoDAO pdao = new ProdottoDAO();
   
    	try {
    					
            ArrayList<ProdottoBean> obj = pdao.doRetrieveLike(prodotto);
            request.setAttribute("description", obj);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalog.jsp");
            dispatcher.forward(request, response);
            
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
}