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
		
		  $(document).ready(function() {
		    var today = new Date();
		    var tomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);
		    var dd = tomorrow.getDate();
		    var mm = tomorrow.getMonth() + 1;
		    var yyyy = tomorrow.getFullYear();
		    if (dd < 10) {
		      dd = '0' + dd;
		    }
		    if (mm < 10) {
		      mm = '0' + mm;
		    }
		    var tomorrowFormatted = yyyy + '-' + mm + '-' + dd;
		    $('#dataEvento').attr('min', tomorrowFormatted);
		  });
	</script>
</head>

<body>
	<jsp:include page="header.jsp" />

	<p class="product-title-nascosto"><%= var.getNome() %></p>
	
	<div class="padre">
		<div class="container">
			<div class="item_descr_pagina">
				<img src="<%= var.getImmagine().getPath()%>"  class="image" alt="immagine prodotto">
			</div>
			
			<div class="item-descrizione">
				<p class="product-title"><%= var.getNome() %></p>
				<p class="descrizione"><%= var.getDescrizione()%></p>
				<p>Seleziona pacchetto</p>
				<input type="radio" id="base" name="fav_language" value="Base" checked="checked">
				<label for="base">Base</label><br>
				<input type="radio" id="silver" name="fav_language" value="Silver">
				<label for="silver">Silver</label><br>
				<input type="radio" id="gold" name="fav_language" value="Gold">
				<label for="gold">Gold</label>
		

			 	<p>Seleziona la data di prenotazione:</p>
   		        <input type="date" id="dataEvento" name="dataEvento" required>
   		        
   		        
				<p class = "product-title-price">&euro; <%= String.format("%.02f", var.getPrezzo()) %> <span>iva inc.</span></p>
				<p class="container-bottone">
				
   			  
					<button class="aggiungi">
						<a href="Cart?action=add&id=<%=var.getCodProdotto()%>&qnt=1&provenienza=catalogo">Aggiungi al carrello</a>
					</button>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
	
	
</body>

</html>