<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Lista Abusi</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>

<% 
	// check if exists a valid session
	String admin = (String)session.getAttribute("Username");
		if (admin == null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>
</head>

<body>
<h3>Lista abusi (<a href="../index.jsp">Torna alla home</a>)</h3>

<%

	List<Abuse> a = new ArrayList<Abuse>();
	a = (List<Abuse>)request.getSession().getAttribute("list");
	if (a.isEmpty())
		out.println("Al momento non ci sono richieste di abuso.");
	Iterator<Abuse> i = a.iterator();
	if (i.hasNext()){
	out.println("<ul>");
	while (i.hasNext()){
		Abuse el = i.next();
		out.println("<li>Mittente:"+el.getEmail()+" <br>");
		out.println("Descrizione abuso:<br>");
		%>
		<form method="post">
		<textarea name="TextArea1" style="width: 286px; height: 107px"></textarea><%el.getDescriprion();%><br>
		<input name="ManagedButton" type="button" value="Gestito">
		<input name="Remove" type="submit" value="Ignora"></form>
		<%
		out.println("</li>");				
	}
	out.println("</ul>");
	}

		%>
</body>

</html>
