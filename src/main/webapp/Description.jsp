<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8" import="java.util.*,Model.*"%>

<%
	ProdottoBean var = (ProdottoBean) request.getAttribute("description");
	request.setAttribute("description", null);
%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<link href="style/description.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" type="image/png" href="logo.jpg">
	<title><%= var.getNome() %></title>
	
	<script>
		$(document).ready(function() {
			$('input[type="radio"]').change(function() {
				var selectedValue = $(this).val();
				
				// Aggiorna il prezzo in base alla scelta del radio button
				switch (selectedValue) {
					case 'Base':
						$('.product-title-price').html('&euro; <%= String.format("%.02f", var.getPrezzo()) %> <span>iva inc.</span>');
						break;
					case 'Silver':
						$('.product-title-price').html('&euro; <%= String.format("%.02f", var.getPrezzo()*0.02*100) %> <span>iva inc.</span>');
						break;
					case 'Gold':
						$('.product-title-price').html('&euro; <%= String.format("%.02f", var.getPrezzo()*0.03*100) %> <span>iva inc.</span>');
						break;
					default:
						break;
				}
				
			
			});
		});
	</script>
</head>

<body>
	<jsp:include page="header.jsp" />

	<p class="product-title-nascosto"><%= var.getNome() %></p>
	
	<div class="padre">
		<div class="container">
			<div class="item_descr_pagina">
				<img class="image" src="<%= var.getImmagine().getPath()%>">
			</div>
			
			<div class="item-descrizione">
				<p class="product-title"><%= var.getNome() %></p>
				<p class="descrizione"><%= var.getDescrizione()%></p>
				<p>Seleziona pacchetto</p>
				<input type="radio" id="base" name="fav_language" value="Base" checked="checked">
				<label>Base</label><br>
				<input type="radio" id="silver" name="fav_language" value="Silver">
				<label>Silver</label><br>
				<input type="radio" id="gold" name="fav_language" value="Gold">
				<label>Gold</label>


			
				<p class = "product-title-price">&euro; <%= String.format("%.02f", var.getPrezzo()) %> <span>iva inc.</span></p>
				<p class="container-bottone">
					<button class="aggiungi">
					
						<%= %>
						<a href="Cart?action=add&id=<%=var.getCodprodotto()%> %>&qnt=1&provenienza=catalogo">Aggiungi al carrello</a>
					</button>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>

</html>