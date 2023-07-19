<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" import="java.util.*,Model.*"%>
<!DOCTYPE html>
<html>

	<head>
		<script src="https://unpkg.com/jspdf-invoice-template@1.4.0/dist/index.js"></script>
 		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="style/acquisto_completato.css">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
		<title>ABD Studio|Acquisto</title>
	</head>
	
	<body>
		<jsp:include page="header.jsp"/>
		
			<div class="grazie">
				<span> Grazie per aver acquistato dal Sito TSW!</span>
			</div>

			<div class="acquisto">
				<img src="./thanks.jpg" alt="thank you" class="responsive">
			</div>
			
			<div class="torna">
				<button onclick="location.href = 'Profilo_utente.jsp'" class="uname">Vai alla pagina utente</button>
			</div>
			
			<div class="torna">
				<button onclick="location.href = 'Catalog.jsp' "class="uname">Torna al Catalogo</button>
			</div>
		

			
		<jsp:include page="footer.jsp"/>
	</body>
	
</html>