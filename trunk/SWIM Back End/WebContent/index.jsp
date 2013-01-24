<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="it" http-equiv="Content-Language">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">

<%
	String admin = (String)session.getAttribute("Username");
		if (admin != null){
			String url = response.encodeURL("/Pages/home.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>

<title>Admin pages</title>
</head>

<body>

<p>
<%
	try {
		if (request.getSession().getAttribute("LoginError").equals(1)){
			request.getSession().setAttribute("LoginError", null);
			out.println("Attenzione! La combinazione di email è password è errata. Ritenta ancora.");
			
		}
	}
	catch (NullPointerException e){
		out.println("Benvenuto nella sezione degli amministratori!");
	}
%>
</p>
<form action="<%= response.encodeURL("../admin/LoginAdminServlet")%>" method="post">
	Username: <input name="Username" type="text"><br><br>Password:
	<input name="Password" type="password"><br><br>
	<input name="Submit1" type="submit" value="Login"></form>
</body>

</html>
