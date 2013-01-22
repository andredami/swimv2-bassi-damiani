<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Lista Abusi</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<jsp:useBean id="listAbuse" scope="session" class="it.polimi.ingsw2.swim.session.administration.AbuseManager" />

<%
	if (session.getAttribute("Name")== null)
		response.sendRedirect(response.encodeRedirectURL("../index.jsp"));
%>
</head>

<body>
<h3>Lista abusi (<a href="../index.jsp">Torna alla home</a>)</h3>

<%

	List<Abuse> a = listAbuse.getAbuseList();
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
		<input name="ManagedButton" type="button" value="Letto"></form>
		<%
		out.println("<a href=" + "ContactUser.jsp" + ">Contatta</a>");
		out.println("</li>");				
	}
	out.println("</ul>");
	}

		%>
</body>

</html>
