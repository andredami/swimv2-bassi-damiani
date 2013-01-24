<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Cambia password</title>
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
<h3>Modifica Password</h3>
<h3><%
	// all the answers to the action taken
	try{
	if (request.getSession().getAttribute("PasswordError").equals(1)){
		request.getSession().setAttribute("PasswordError", null);
		out.println("Errore. Password non corrispondenti <br>");
	}
	} catch (NullPointerException e){
		
	}

%></h3>
<form method="post" action="<%= response.encodeURL("../ChangeAdminPasswordServlet")%>" >
	Vecchia Password:<input name="OldPassword" type="password"><br><br>Nuova 
	Password:<input name="NewPassword" type="password"><br><br>Conferma Nuova 
	Password: <input name="ConfirmNewPassword" type="password"><br><br>
	<input name="Confirm" type="submit" value="Conferma"><input name="Cancel" type="button" value="Cancella"></form>
</body>

</html>
