<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Model.*"%>
    
<%	if(request.getSession().getAttribute("secure") == null || request.getSession().getAttribute("secure").equals("Utente")){
	response.sendRedirect("./login.jsp");
	return;
}
	ProdottoBean var = (ProdottoBean)request.getAttribute("modify");
	request.setAttribute("modify", null);
%>    
<!DOCTYPE html>
	<html>
	
	<head>

 		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
		<link rel="stylesheet" href="style/modify.css">
	    <link rel="stylesheet" href="style/header.css">
	    <link rel="stylesheet" href="style/footer.css">
		<title>Sito TSW|Modifica Catalogo</title> 
	
	</head>
	
	<body>
	
		<jsp:include page="header.jsp" />
		
		<form class="modify_form" action="Modify" method="POST">
		
			<div class="div_modifica">
			
				<h1>Modifica prodotto</h1>
				
				<input class="box" type="hidden" name="id" value="<%= var.getCodProdotto()%>">
				<input class="box" type="hidden" name="action" value="update">
				
				<div class="modify">
					<p class="p_mod">Nome prodotto</p>
					<input class="box" type="text" name="Nome" value="<%= var.getNome()%>">
				</div>		
				
				<div class="modify">
					<p class="p_mod">Prezzo Base</p>
					<input  class="box" type="number" name="Prezzo" value="<%= var.getPrezzo()%>">
				</div>
				
				<div class="modify">
					<p class="p_mod">Prezzo Silver</p>
					<input  class="box" type="number" name="PrezzoS" value="<%= var.getPrezzoSilver()%>">
				</div>
				
				<div class="modify">
					<p class="p_mod">Prezzo Gold</p>
					<input  class="box" type="number" name="PrezzoG" value="<%= var.getPrezzoGold()%>">
				</div>
				
				<div class="modify">
					<p class="p_mod">Descrizione</p>
					<input class="box" type="text" name="Descrizione" value="<%= var.getDescrizione()%>">
				</div>
				
				
				<div class="modify">
					<p class="p_mod">Eliminare?</p>
					<div class="sino" style= "display:flex;"><input type="radio" name="Rimosso" value="1" <%if(var.getRimosso() == 1) out.println("checked"); %>> <p>Sì</p> </div>
					<div class="sino" style= "display:flex;"><input type="radio" name="Rimosso" value="0" <%if(var.getRimosso() == 0) out.println("checked"); %>> <p>No</p></div>
				</div>
				
				
				
				<input class ="Modifica" type="submit" value="Invia modifica">
				
			</div>
		
		</form>
		
		<jsp:include page="footer.jsp" />
	
	</body>
	
</html>