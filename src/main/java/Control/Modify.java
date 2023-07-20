package Control;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.*;

/**
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")

public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDAO pdao = new ProdottoDAO();
        String action = request.getParameter("action");
        
        if (action == null) {
            return;
        }

        if (action.equalsIgnoreCase("mod")) {
            int imm = Integer.parseInt(request.getParameter("id"));
            try {
                ProdottoBean obj = pdao.doRetrieveByKey(imm);
                request.setAttribute("modify", obj);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Modify.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("update")) {
        	
            int id = Integer.parseInt(request.getParameter("id"));
            
            System.out.println(id);
            ProdottoBean obj = null;

            try {
                obj = pdao.doRetrieveByKey(id);
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }

            obj.setNome((String) request.getParameter("Nome"));
            obj.setPrezzo(Float.parseFloat(request.getParameter("Prezzo")));
            obj.setPrezzoSilver(Float.parseFloat(request.getParameter("PrezzoS")));
            obj.setPrezzoGold(Float.parseFloat(request.getParameter("PrezzoG")));
            obj.setDescrizione((String) request.getParameter("Descrizione"));

            
            obj.setRimosso(Integer.parseInt(request.getParameter("Rimosso")));

            try {
                pdao.doUpdate(obj);
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CatalogAdmin.jsp");
            dispatcher.forward(request, response);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
