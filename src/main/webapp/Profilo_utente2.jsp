<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,Model.*"%>
    
    <%User obj = (User)request.getSession().getAttribute("Utente loggato");
    	if(obj == null){%>
    		
    		<jsp:forward page="Login.jsp"/>
    	<%}
    	if (obj.isAdmin()){ %>
    		<jsp:forward page="profilo_admin.jsp" />
    		
    	<% }  %>
    	
    	<% 
    	ArrayList<OrdineBean> arr = (ArrayList<OrdineBean>) request.getAttribute("ordini");	
    	if(arr == null){
    		response.sendRedirect("Mostra_ordini_utente?action=mostra");
    		return;
    	}
    	%>
 
    
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
 		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="./style/profilo_utente.css">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />	
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
	    
	    <script src="https://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<title>Sito TSW|Area utente</title>
	</head>
	
	<body>
		<jsp:include page="header.jsp" />
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">Edogaru</span><span class="text-black-50">edogaru@mail.com.my</span><span> </span></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Dettagli Profilo</h4>
                </div>
                <div class="row mt-2">
                	<div class="col-md-12"><label class="labels">Email ID</label>
                				<p><%=obj.getEmail() %></p>
                	</div>
                	
                
                    <div class="col-md-6"><label class="labels">Nome</label>
			                    <p><%=obj.getNome() %></p>
			        </div>

                    <div class="col-md-6"><label class="labels">Cognome</label>
                    			<p><%=obj.getCognome() %></p>
                    </div>
                    
                    <form action ="Reindirizzamento" method="get">
						<input type="submit" class="Modifica" value="Modifica dati">
					</form>		
				
		
				
					<form action ="Logout_servlet" method="get" >
						<input type="submit" class="Modifica2" value="Logout">
					</form>	
					
                </div>
                <div class="row mt-3">
                    <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" class="form-control" placeholder="enter phone number" value=""></div>
                    <div class="col-md-12"><label class="labels">Address Line 1</label><input type="text" class="form-control" placeholder="enter address line 1" value=""></div>
                    <div class="col-md-12"><label class="labels">Address Line 2</label><input type="text" class="form-control" placeholder="enter address line 2" value=""></div>
                    <div class="col-md-12"><label class="labels">Postcode</label><input type="text" class="form-control" placeholder="enter address line 2" value=""></div>
                    <div class="col-md-12"><label class="labels">State</label><input type="text" class="form-control" placeholder="enter address line 2" value=""></div>
                    <div class="col-md-12"><label class="labels">Education</label><input type="text" class="form-control" placeholder="education" value=""></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><label class="labels">Country</label><input type="text" class="form-control" placeholder="country" value=""></div>
                    <div class="col-md-6"><label class="labels">State/Region</label><input type="text" class="form-control" value="" placeholder="state"></div>
                </div>
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">Save Profile</button></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center experience"><span>Edit Experience</span><span class="border px-3 p-1 add-experience"><i class="fa fa-plus"></i>&nbsp;Experience</span></div><br>
                <div class="col-md-12"><label class="labels">Experience in Designing</label><input type="text" class="form-control" placeholder="experience" value=""></div> <br>
                <div class="col-md-12"><label class="labels">Additional Details</label><input type="text" class="form-control" placeholder="additional details" value=""></div>
            </div>
        </div>
    </div>
</div>

<div class="ciclo">
			<p>I tuoi dati di pagamento</p>
		</div>
		
		<%
			for (PagamentoBean pbean : obj.getPagamento()){ %>
				
				<div class="dunno"> 
					<p><strong>Nominativo:</strong> <%=pbean.getNominativo() %></p>
					<p><strong>Codice Carta:</strong> <%=pbean.getCodiceCarta() %></p>
					<p><strong>Mese Scadenza:</strong> <%=pbean.getMeseScadenza() %></p>
					<p><strong>Anno Scadenza:</strong> <%=pbean.getAnnoScadenza() %></p>
					<p><strong>CVV:</strong> ***</p>
				</div>
				
			<% } %>

		<div class="ciclo">
			<p>I tuoi dati di consegna</p>
		</div>

		<%
			for (ConsegnaBean pbean : obj.getConsegna()){ %>
				
				<div class="dunno">
					<p><strong>Via:</strong> <%=pbean.getVia() %></p>
					<p><strong>Numero:</strong> <%=pbean.getNumero() %></p>
					<p><strong>CAP:</strong> <%=pbean.getCap() %></p>
					<p><strong>Città</strong> <%=pbean.getCitta() %></p>
				</div>
				
			<% } %>
			
			
			<div class="tabella"> 
			<table class="tab">
				<tr> <!--  INTESTAZIONE TABELLA  -->
					<th>ID ordine </th>
					<th>Data ordine </th>
					<th>Stato ordine</th>
					<th>Nome/Cognome utente </th>
					<th>Metodo di pagamento </th>
					<th>Totale</th>
					<th>Ordine</th>
					<th>Fattura</th>
					
				</tr>
			<%for(OrdineBean bean : arr){ %>
				<tr> <!-- CONTENUTO TABELLA -->
					<td> <%= bean.getIdOrdine() %></td>
					<td> <%= bean.getDataOrdine() %></td>
					<td> <%= bean.getStatoOrdine() %></td>
					<td> <%= bean.getCodUtente().getEmail() %></td>
					<td> <%= bean.getCodPagamento().getCodiceCarta() %></td>
					<td> <%= String.format("%.02f", bean.getPrezzoTotale()) %> &euro; </td>
					<td>
						<form action="Fattura" method="post">
							<input type="hidden" name="ordine" value="<%= bean.getIdOrdine()%>">
							<input type="submit" value="Fattura" class="fattura">
						</form>
					</td>
					<td> <% for(ProdottoBean pbean : bean.getComposizione().keySet()){%>
						<img class ="images" src="<%= pbean.getImmagine().getPath() %>">	
						<%}%>
					</td>
					
				</tr>
				
				<%} %>
			</table>
 		</div> 

</body>
		<jsp:include page="footer.jsp" />

</html>