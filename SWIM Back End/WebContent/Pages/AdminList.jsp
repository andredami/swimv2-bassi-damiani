<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Admin List</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>

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
<h3>Lista degli amministratori</h3>

<%
	try{
		List<Administrator> a = new ArrayList<Administrator>();
		a = (List<Administrator>)request.getSession().getAttribute("list");
		Iterator<Administrator> i = a.iterator();
		if (i.hasNext()){
		out.println("<ul>");
		while (i.hasNext()){
			Administrator el = i.next();
			out.println("<li>");
			out.println("Username: "+ el.getUsername()+"<br>");
			out.println("Email:" + el.getEmail()+"<br>");
			out.println("<a href=" + "ContactUser.jsp" + ">Contatta</a>");
			out.println("</li>");				
		}
		out.println("</ul>");
		}
	}catch(NullPointerException e){
		out.println("Non ci sono amministratori.");
	}
	
%>

<a href="../Pages/AddNewAdmin.jsp">Aggiungi nuovo amministratore</a><br>
<a href="../Pages/ChangePassword.jsp?Username=Username">Cambia la mia password</a><br>
<a href="../Pages/ContactUser.jsp">Contatta utente</a><br>

</body>

</html>
