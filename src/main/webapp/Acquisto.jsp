<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,Model.*"%>
    
<% 
	Map<ProdottoBean, ArrayList<Double>> obj = (Map<ProdottoBean, ArrayList<Double>>) request.getSession().getAttribute("carrello_view");
	if(obj == null){
		response.sendRedirect("./Carrello.jsp");
		return;
	}
	
	User u = (User)request.getSession().getAttribute("Utente loggato");
	if (u == null){
		response.sendRedirect("./Login.jsp");
		return;
	}
	
%>    
    
<!DOCTYPE html>
<html>
	
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="style/acquisto.css">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

		<title>ABD Studio|Checkout</title>
	</head>
	
	<body>
		<jsp:include page="header.jsp"/>
		
		<div class="container">
			<div class="py-5 text-center">
        		<h2>Checkout</h2>
    		</div>
    		<div class="row">
	    		<div class="col-md-4 order-md-2 mb-4">
		            <h4 class="d-flex justify-content-between align-items-center mb-3">
		                <span class="text-muted">Il tuo carrello</span>
		                <span class="badge badge-secondary badge-pill">3</span>
		            </h4>
		            <ul class="list-group mb-3 sticky-top" style="position:sticky; top:20%"> 
	    		
		    			<% 
						if(obj != null && !obj.isEmpty()){
						double prezzoFinale = 0; 
						for(Map.Entry<ProdottoBean, ArrayList<Double>> entry : obj.entrySet()){
							ProdottoBean prodotto = entry.getKey();
						double prezzoTotale =(entry.getValue().get(0) * prodotto.getPrezzo());
						%>
		    		
		    			<li class="list-group-item d-flex justify-content-between lh-condensed">
		                    <div style="display:flex">
		                        <div>	
		                        	<h6 class="my-0"><%=obj.get(prodotto).get(0) + "x " + prodotto.getNome()%></h6>
		                    	</div>
		                    </div>
		                    <span class="text-muted"><%= String.format("%.02f",prezzoTotale)%> &euro;</span>
		                </li>
		                
		    			<%prezzoFinale = prezzoFinale + prezzoTotale; } %>
		              
		            
		                <li class="list-group-item d-flex justify-content-between" class = "totale">
		                    <span>Totale (EUR)</span>
		                    <strong><%= String.format("%.02f",prezzoFinale) %> &euro;</strong>
		                </li>
	            	</ul>
    			</div>
			</div>
		                <form action="Salvataggio_ordine" method="post"> 
		                <input type="hidden" value="<%= prezzoFinale%>" name="pf">
						<div class="metodi">
						
								
							<span class="text-muted">Seleziona il tuo metodo di pagamento</span>
								<% 
									if (u.getPagamento() != null){
									for(PagamentoBean pbean : u.getPagamento()){
										
								%>	
								
										<div class="choose">
											<input type="radio" name="Pagamento" required value="<%= pbean.getIdPagamento()%>">
											<p><strong>Nome intestatario:</strong> <%=  pbean.getNominativo() %></p>
											<p><strong>CVV:</strong> <%=  pbean.getCVV() %></p>
											<p><strong>Mese Scadenza:</strong> <%=  pbean.getMeseScadenza() %></p>
											<p><strong>Anno Scadenza:</strong> <%=  pbean.getAnnoScadenza() %></p>
											<p><strong>Codice Carta:</strong> <%=  pbean.getCodiceCarta() %></p>
										</div>
								<%}}
								
								else System.out.println("Aggiungere inserimento dati pagamento e indirizzo fatturazione");%>
						</div>
									
									
								<div class="metodi">
								<span class="text-muted">Seleziona l'indirizzo di spedizione</span>
									<% 
									if (u.getConsegna() != null){
									for(ConsegnaBean cbean : u.getConsegna()){
								%>	
								
						
									<div class="choose">
										<input type="radio" name="Indirizzo" required value="<%= cbean.getIdconsegna()%>">
										<p><strong>Via:</strong> <%=  cbean.getVia() %></p>
										<p><strong>Numero:</strong> <%=  cbean.getNumero() %></p>
										<p><strong>CAP:</strong> <%=  cbean.getCap() %></p>
										<p><strong>Città:</strong> <%=  cbean.getCitta() %></p>
									</div>
								
									
								<%}}%>
						</div>
						<%}%> 
						
		<div class="completo">
			<% if (u.getConsegna().isEmpty() || u.getPagamento().isEmpty()){ %>
					<input type="submit" value="Completa ordine" class="acquista" disabled>
	
				<% } else { %>
					<input type="submit" value="Completa ordine" class="acquista">
					
				<% } %>	
		</div> 			
			</form>
		</div>
		

		
		<jsp:include page="footer.jsp"/>
	</body>
	
</html>