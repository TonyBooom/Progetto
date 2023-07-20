<%-- <%@page import="Model.UserBean" %>
<%@ page errorPage="ErrorPage.jsp" %>  --%>

<!DOCTYPE html>
<html lang="it">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="style/login.css">
	    <link rel="stylesheet" href="style/header.css">
	    <link rel="stylesheet" href="style/footer.css">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
 	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
	    <script src="https://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	    <title>ABD Studio|Login</title>
	</head>

	<body>
	
		<jsp:include page="header.jsp"/>
		
		<div id="error_message" style="display: none; color: red;"></div>
		

	    <form id="loginForm" class="loginForm" method="post" action="Login">
	        <h1 id="accedi">Accedi</h1>
	        <div class="content">
	        
	            <div class="input-field">
	                <input type="email" placeholder="Username" id="username" name="username">
	            </div>
	            
	            <div class="input-field">
	                <input type="password" placeholder="Password" id="password" name="password">
	            </div>
	        </div>
	        
	        
	        <div class="action">
	            <a href="Registrazione.jsp">Registrati</a>
	            <button type="submit" onclick ='validateForm()' id="loginButton">Login</button>
	        </div>
	    </form>
	
		<jsp:include page="footer.jsp"/>
		
		
		
		
<script>
		function validateForm(event) {
	        event.preventDefault();

	        var email = $('#username').val();
	        var password = $('#password').val();
	        var errorMessages = [];

	        // Validazione dei campi della form
	        if (email === "") {
	            errorMessages.push("Il campo email è obbligatorio.");
	        }

	        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	        if (!emailRegex.test(email)) {
	            errorMessages.push("L'indirizzo email non è valido.");
	        }

	        if (password === "") {
	            errorMessages.push("Il campo password è obbligatorio.");
	        } else if (password.length < 4) {
	            errorMessages.push("Il campo password deve contenere almeno 4 caratteri.");
	        }

	        // Mostra gli eventuali messaggi di errore
	        if (errorMessages.length > 0) {
	            var errorHtml = "";
	            for (var i = 0; i < errorMessages.length; i++) {
	                errorHtml += "<p>" + errorMessages[i] + "</p>";
	            }
	            $('#error_messages').html(errorHtml);
	        } else {
	            // Invio dei dati al server
	            $.ajax({
	                type: 'POST',
	                url: 'Login',  // Inserisci l'URL del server che riceverà i dati
	                data: {
	                    email: email,
	                    password: password
	  
	                },
	                success: function (response) {
	                    console.log(response);
	     
	                },
	                error: function (xhr, status, error) {
	                    console.log(error);
	                }
	            });
	        }
	    }
</script>
</body>
</html>