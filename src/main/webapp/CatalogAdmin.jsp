<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Model.*"%>
    
<% // Provo a prendere dalla request l'attributo prodotti:
	// Se � nullo, richiama la servleet Catalogo per farlo riempire, altrimenti procede alla generazione della pagina
	if(request.getSession().getAttribute("secure") == null || request.getSession().getAttribute("secure").equals("Utente")){
		response.sendRedirect("./Login.jsp");
		return;
	}

	Collection<ProdottoBean> obj = (Collection<ProdottoBean>)request.getAttribute("prodotti"); 
	if(obj == null){
		response.sendRedirect("./Catalogo?action=read");
		return;
	}
	request.setAttribute("prodotti", null);
%>

<!DOCTYPE html>
<html>

	<head>
	
			<meta charset="ISO-8859-1">
			<meta charset="UTF-8">
		    <meta http-equiv="X-UA-Compatible" content="IE=edge">
		    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		    <link rel="stylesheet" href="style/catalog.css">
		    <link rel="shortcut icon" type="image/png" href="logo.png">
		    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
		    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
			<title>Catalogo Admin</title>
		
	</head>

	<body>

		<jsp:include page="header.jsp" />

		<h1 class="titoloCatalogo">Catalogo Admin Sito TSW</h1>
	
		<div class="div_esterno">
	
			<%
			// Il for crea una variabile del tipo ProdottoBean ed ad ogni iterazione va ad assegnare a quella variabile il contenuto di obj all'i-esima posizione 
			if (obj != null)
				for (ProdottoBean var : obj) {
			%>
	
			
			<div class="prodotto">
			
				<a href="Catalogo?action=view&id=<%=var.getCodProdotto()%>">
							<img src="<%=var.getImmagine().getPath()%>" class="imgProdotto" alt="immagine prodotto">
					</a>	
				<a href="Catalogo?action=view&id=<%=var.getCodProdotto()%>">
							<%=var.getNome()%>
					</a>
					
				<form action="Modify" method="POST">
				
				<input type="hidden" name="action" value="mod">
				<input type="hidden" name="id" value="<%= var.getCodProdotto()%>">
				
				
				<input class ="pulsante" type="submit" value="Modifica">
				
				</form>
				
			</div>
					
		
				
			<%
				}
			%>
		</div>
	


		<jsp:include page="footer.jsp" />
		
	</body>
	
</html>