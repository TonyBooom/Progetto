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


@WebServlet("/Price")
public class Price extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String selectedValue = request.getParameter("selectedValue");
    double newPrice = 0.0;
    

    switch (selectedValue) {
      case "Base":
        newPrice = var.getPrezzo();
        break;
      case "Silver":
        newPrice = var.getPrezzo() * 0.02 * 100;
        break;
      case "Gold":
        newPrice = var.getPrezzo() * 0.03 * 100;
        break;
      default:
        break;
    }

    // Aggiorna il prezzo nella variabile var
    var.setPrezzo(newPrice);

    // Restituisci il nuovo prezzo come risposta AJAX
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write("&euro; " + String.format("%.02f", newPrice) + " <span>iva inc.</span>");
  }
}