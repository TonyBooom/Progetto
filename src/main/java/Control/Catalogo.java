package Control;

import java.io.File;   
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig config) throws ServletException 
    {
        super.init(config);
        try 
        {
            LoadImage();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
	
    public Catalogo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDAO pdao = new ProdottoDAO();
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equalsIgnoreCase("read")) {
                try {
                    Collection<ProdottoBean> obj = pdao.doRetrieveAll(null);
                    request.setAttribute("prodotti", obj);

                    String secure = (String) request.getSession().getAttribute("secure");
                    String destination = "/Catalog.jsp";
                    
                    if (secure != null && secure.equalsIgnoreCase("Admin")) {
                        destination = "/CatalogAdmin.jsp";
                    }

                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(destination);
                    dispatcher.forward(request, response);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("view")) {
                int prod = Integer.parseInt(request.getParameter("id"));

                try {
                    ProdottoBean obj = pdao.doRetrieveByKey(prod);
                    request.setAttribute("description", obj);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Description.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void LoadImage() throws IOException {
	    String savePath = getServletContext().getRealPath("") + File.separator + "images/";

	    String uri = "C:/Immagine";	
	    File folder = new File(uri);
	    File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	        if (listOfFiles[i].isFile()) {
	            try (OutputStream out = new FileOutputStream(new File(savePath + File.separator + listOfFiles[i].getName()));
	                 InputStream filecontent = new FileInputStream(listOfFiles[i])) {

	                final byte[] bytes = new byte[1024];
	                int read;
	                while ((read = filecontent.read(bytes)) != -1) {
	                    out.write(bytes, 0, read);
	                }
	            } catch (Exception e) {
	                System.err.println("Error: " + e.getMessage()); //Tipologia di eccezione rilevata
	                throw new IOException(e);
	            }
	        }
	    }
	}

}
