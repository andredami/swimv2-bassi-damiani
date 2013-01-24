<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; "http-equiv="Content-Type">
<title>Aggiungi Nuovo Amministratore</title>
<%
//check if exists a valid session
	String admin = (String)session.getAttribute("Username");
		if (admin == null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>
</head>

<body>
<h3>Aggiungi nuovo amministratore</h3>
<h3><%
	// all the answers to the action taken
	try{
	if (request.getSession().getAttribute("PasswordError").equals(1)){
		request.getSession().setAttribute("PasswordError", null);
		out.println("Errore. Password non corrispondenti <br>");
	}
	} catch (NullPointerException e){
		
	}
	try {
	if (request.getSession().getAttribute("DataError").equals(1)){
	request.getSession().setAttribute("DataError", null);
	out.println("Errore. Dati errati");
	}
	}
	catch (NullPointerException e){
		
	}

%></h3>
<form method="post" action="<%= response.encodeURL("../AddNewAdminServlet")%>">
	Username:<input name="Username" type="text"><br><br>Email:
	<input name="Email" type="text"><br><br>Password:
	<input name="Password" type="password"><br><br>Conferma Password:
	<input name="ConfirmPassword" type="password"><br><br>
	<input name="Confirm" type="submit" value="Conferma">
	<input name="Cancel" type="button" onclick="/Pages/home.jsp" value="Cancella"></form>
</body>

</html>
