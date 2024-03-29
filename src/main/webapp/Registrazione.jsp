<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,Model.*"%>
  
<% String registrationMessage = (String) session.getAttribute("registrationMessage"); %>
 

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/register.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/footer.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>


    <title>Sito TSW|Registrazione</title>
</head>
<%
	if(request.getSession().getAttribute("utente") == null){;
		request.getSession().setAttribute("ruolo", "guest");
	}
	if(!request.getSession().getAttribute("ruolo").equals("guest"))
	{
		response.sendRedirect("homepage.jsp");
		return;
	} %>
	
<body>
	
    <jsp:include page="header.jsp"/>


	<div id="error_message" style="display: none; color: red;"></div>
	
	
    <form action="RegistrazioneC" method="post" class="loginForm" id="registerForm">
    	<div id="form1">
        	<h1 id="accedi">Registrati</h1>
        	<div class="content">
            	<div class="input-field">
                	<input type="email" placeholder="Email" id="emailInput" name="email" required>
    				<span id="emailError" class="error"></span>
            	</div>
 
            	<div class="input-field">
                	<input type="password" placeholder="Password" class="pw" name="password" id="pass1" required>
            	</div>
            	<div class="input-field">
               		<input type="password" placeholder="Ripeti Password" class="pw" name="pw" id="pass2" required>
            	</div>
        	</div>
        	
            	<div class="input-field">
                	<input type="text" placeholder="Nome" id="nome" name="nome" required>
                	<span id="nomeError" class="error"></span>
                	
            	</div>
            
            	<div class="input-field">
                	<input type="text" placeholder="Cognome" id="cognome" name="cognome" required>
                	<span id="cognomeError" class="error"></span>
                	
            	</div>
            	
            	<div class="input-field">
                	<input type="text" placeholder="Codice Fiscale" id="codicefiscale" name="codicefiscale"  maxlength="16" required>
            	</div>
            	
        	</div>
        	
        	
			<div class="">
                <p id="error_messages" style="color: red;"></p>
            </div>
     
            <div id="success_message" style="color: green;"></div>
        
        	<br><br>
        	<div class="action">
				<button id="regBtn" onclick="validateForm(event)" type="submit">Registrati</button>
        	</div>
        	
	
    </form>

        <jsp:include page="footer.jsp"/>
           
        
  
                
<script>
    function validateForm() {
        event.preventDefault();

        var email = $('#emailInput').val();
        var password1 = $('#pass1').val();
        var password2 = $('#pass2').val();
        var nome = $('#nome').val();
        var cognome = $('#cognome').val();
        var codicefiscale = $('#codicefiscale').val();
        var errorMessages = [];

        // Validazione dei campi della form
        if (email === "") {
            errorMessages.push("Il campo email è obbligatorio.");
        }

        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            errorMessages.push("L'indirizzo email non è valido.");
        }

        if (password1 === "") {
            errorMessages.push("Il campo password è obbligatorio.");
        } else if (password1.length < 4) {
            errorMessages.push("Il campo password deve contenere almeno 4 caratteri.");
        }

        if (password2 === "") {
            errorMessages.push("Il campo ripeti password è obbligatorio.");
        }

        if (password1 !== password2) {
            errorMessages.push("Le password non corrispondono.");
        }

        if (nome === "") {
            errorMessages.push("Il campo nome è obbligatorio.");
        } else if (nome.length < 4) {
            errorMessages.push("Il campo nome deve contenere almeno 4 caratteri.");
        }

        if (cognome === "") {
            errorMessages.push("Il campo cognome è obbligatorio.");
        } else if (cognome.length < 4) {
            errorMessages.push("Il campo cognome deve contenere almeno 4 caratteri.");
        }

        if (codicefiscale === "") {
            errorMessages.push("Il campo codice fiscale è obbligatorio.");
        } else if (codicefiscale.length !== 16) {
            errorMessages.push("Il campo codice fiscale deve essere di 16 caratteri.");
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
                url: 'RegistrazioneC',  // Inserisci l'URL del server che riceverà i dati
                data: {
                    email: email,
                    password: password1,
                    nome: nome,
                    cognome: cognome,
                    codicefiscale: codicefiscale
                },
                success: function (response) {
                    // Gestisci la risposta dal server
                    console.log(response);
                    // Reindirizza alla pagina di login
                    $('#success_message').text("Registrazione avvenuta con successo.");
                    sessionStorage.setItem('registrationMessage', 'Registrazione avvenuta con successo');
                    window.location.href = "Login.jsp";


                },
                error: function (xhr, status, error) {
                    console.log(error);
                    $('#failure_message').text("Registrazione fallita. Si è verificato un errore.");

                }
            });
        }
    }
    var registrationMessage = "<%= registrationMessage %>";
    if (registrationMessage && registrationMessage !== "null") {
        if (registrationMessage.startsWith("Errore")) {
            var errorMessageElement = document.getElementById('error_message');
            if (errorMessageElement) {
                errorMessageElement.innerText = registrationMessage;
                errorMessageElement.style.display = 'block';
            }
        } else {
            var successMessageElement = document.getElementById('success_message');
            if (successMessageElement) {
                successMessageElement.innerText = registrationMessage;
                successMessageElement.style.display = 'block';
            }
        }
    }
    
</script>
        
</body>

</html>