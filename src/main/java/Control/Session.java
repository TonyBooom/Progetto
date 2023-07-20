package Control;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/Session")
public class Session implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // Non crea una nuova sessione se non esiste

        // Controlla se la sessione esiste e se è ancora valida
        if (session != null && !sessionIsValid(session)) {
            session.invalidate(); // Invalida la sessione
            httpResponse.sendRedirect("erorr500.jsp"); // Reindirizza all'URL di login o una pagina di avviso
            return;
        }

        // Procedi con la richiesta
        chain.doFilter(request, response);
    }

    // Aggiungi qui la logica per verificare se la sessione è valida, ad esempio controllando se l'utente è autenticato
    private boolean sessionIsValid(HttpSession session) {
        // Esempio: verifica se l'utente è autenticato nella sessione
        return session.getAttribute("Utente loggato") != null;
    }
}