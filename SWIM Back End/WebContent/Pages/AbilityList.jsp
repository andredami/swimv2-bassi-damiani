<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Lista abilità</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<jsp:useBean id="listAbility" scope="session" class="it.polimi.ingsw2.swim.session.administration.AbilityManager" />

<%
	if (session.getAttribute("Name")== null)
		response.sendRedirect(response.encodeRedirectURL("../index.jsp"));
%>
</head>

<body>
<h3>Lista delle abilità</h3>
<p>(possibili errori)</p>
<form action="FilterAbilityServlet" method="post">
Filtra abilità:
<input name="FilterText" type="text"><input name="FilterSubmit" type="submit" value="Filtra"></form>
<div>
	<%

	List<Ability> a = listAbility.retriveAbilityList();
	Iterator<Ability> i = a.iterator();
	if (i.hasNext()){
	out.println("<ul>");
	while (i.hasNext()){
		Ability el = i.next();
		out.println("<li>Nome:"+el.getName()+" <br>");
		out.println("Descrizione:<br>");
		%>
		<form method="post">
		<textarea name="TextArea1" style="width: 276px; height: 100px"><%el.getDescription();%></textarea></form>
		<br><a href="AbilityEditor.jsp">Modifica</a> <a href="">Cancella</a>
		<%
		out.println("</li>");
	}
	out.println("</ul>");
	}

		%>
</div>
</body>

</html>
