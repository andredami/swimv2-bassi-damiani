<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html;" http-equiv="Content-Type">
<title>Contatta Admin</title>
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
<h3>Contatta</h3>
<h3><%
	// all the answers to the action taken
	try{
	if (request.getSession().getAttribute("DataError").equals(1)){
		request.getSession().setAttribute("DataError", null);
		out.println("Errore. Non esiste il mittente scelto. <br>");
	}
	} catch (NullPointerException e){
		
	}

%></h3>
<form action="<%= response.encodeURL("../ContactUserServlet")%>" method="post">
	Mittente: <input name="Addressee" type="text"><br><br>Messaggio:<br>
	<textarea name="Message" style="width: 278px; height: 117px"></textarea><br>
	<br><input name="Submit" type="submit" value="Invia"><input name="Cancel" type="button" value="Cancella"></form>

</body>

</html>
