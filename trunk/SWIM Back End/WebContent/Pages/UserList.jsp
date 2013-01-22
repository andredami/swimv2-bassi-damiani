<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Lista Utenti</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<jsp:useBean id="listUser" scope="session" class="it.polimi.ingsw2.swim.session.administration.UserManager" />

<%
	if (session.getAttribute("Name")== null)
		response.sendRedirect(response.encodeRedirectURL("../index.jsp"));
%>
</head>

<body>
<h3>Lista Utenti</h3>

<%
	List<User> a = listUser.retriveUserList();
	Iterator<User> i = a.iterator();
	if (i.hasNext()){
	out.println("<ul>");
	while (i.hasNext()){
		User el = i.next();
		out.print("<li>Foto: <br><img src="+el.getPicture()+"><br>"+ el.getName().getFirstname()+" "+el.getName().getSurname());
		out.print("-" + el.getGender()+" - <a href="+"UserContactProfile.jsp"+">Mostra Profilo</a><br>");
		out.println("</li>");				
	}
	out.println("</ul>");
	}

%>

</body>

</html>
