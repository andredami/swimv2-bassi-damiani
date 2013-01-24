<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html;" http-equiv="Content-Type">
<title>Lista abilità</title>
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
<h3>Lista delle abilità</h3>
<form action="<%= response.encodeURL("../FilterAbilityServlet")%>" method="post">
Filtra abilità:
<input name="FilterText" type="text"><input name="FilterSubmit" type="submit" value="Filtra"></form>
<div>
	<%

	List<Ability> a = new ArrayList<Ability>();
	a = (List<Ability>)request.getSession().getAttribute("list");
	if (a.isEmpty())
		out.println("Non ci sono abilità registrate nel database.");
	Iterator<Ability> i = a.iterator();
	if (i.hasNext()){
	out.println("<ul>");
	while (i.hasNext()){
		Ability el = i.next();
		out.println("<li>Nome:"+el.getName()+" <br>");
		out.println("Descrizione:<br>");
		%>
		<form method="post">
		<textarea name="TextArea" style="width: 276px; height: 100px"><%el.getDescription();%></textarea></form>
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
