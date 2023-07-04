<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,Model.*"%>

<%
	// Provo a prendere dalla request l'attributo prodotti:
	// Se � nullo, richiama la servlet Catalogo per farlo riempire, altrimenti procede alla generazione della pagina

	Collection<ProdottoBean> obj = (Collection<ProdottoBean>) request.getAttribute("prodotti");
	if (obj == null) {
		response.sendRedirect("./Catalogo?action=read");
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
	    
	    <link href="https://fonts.google.com/specimen/Ubuntu?sort=popularity&noto.lang=en_Latn" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="./style/font.css">
	
	    
		<title>Sito TSW|Catalogo</title>
	</head>


	<body>
	
		<jsp:include page="header.jsp" />
	
		<h1 class="titoloCatalogo">Catalogo Sito TSW</h1>
		
		<div class="div_esterno">
		
			<%
			// Il for crea una variabile del tipo ProdottoBean ed ad ogni iterazione va ad assegnare a quella variabile il contenuto di obj all'i-esima posizione 
			if (obj != null)
				for (ProdottoBean var : obj) {
					if (var.getRimosso() == 1) {
						continue;
					}
			%>
	
			<!--  POPUP DA INTEGRARE CON LO SCRIPT SOTTO: 
			<jps:include page="PopupAcquisto.html" /> -->
			
			<div class="prodotto">
			
				<a href="Catalogo?action=view&id=<%=var.getCodprodotto()%>">
							<img src="<%=var.getImmagine().getPath()%>" class="imgProdotto">
				</a>	
				<a href="Catalogo?action=view&id=<%=var.getCodprodotto()%>">
							<%=var.getNome()%>
							<br>
				</a> 
				<a href="Cart?action=add&id=<%=var.getCodprodotto()%>&qnt=1&provenienza=catalogo"></a>
					
			</div>
						
			
				
			<%
				}
			%>
		</div>
		
	
	
		<jsp:include page="footer.jsp" />
		
	</body>

</html>