<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Admin List</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<jsp:useBean id="listAdmin" scope="session" class="it.polimi.ingsw2.swim.session.administration.ProfileManager" />

<%
	if (session.getAttribute("Name")== null)
		response.sendRedirect(response.encodeRedirectURL("../index.jsp"));
%>

</head>

<body>
<h3>Lista degli amministratori</h3>

<%
	List<Administrator> a = listAdmin.retriveList();
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
%>

<a href="AddNewAdmin.jsp">Aggiungi nuovo amministratore</a><br>
<a href="ChangePassword.jsp">Cambia la mia password</a><br>
<a href="ContactUser.jsp">Contatta utente</a><br>

</body>

</html>
