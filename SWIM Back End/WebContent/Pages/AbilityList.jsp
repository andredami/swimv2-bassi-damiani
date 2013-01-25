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
	Long admin = (Long)session.getAttribute("Id");
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
	a = (List<Ability>)request.getSession().getAttribute("abilityList");
	if (a.isEmpty()){
		out.println("Non ci sono abilità registrate nel database.");
		%>
		<a href="<%= response.encodeURL("../AbilityListServlet")%>">Torna indietro</a>
		<%
	}
	
	

	Iterator<Ability> i = a.iterator();
	int count=0;
	if (i.hasNext()){
	out.println("<ul>");
	while (i.hasNext()){
		Ability el = i.next();
		String name = el.getName();
		String desc = el.getDescription();
		request.getSession().setAttribute("Name"+count, name);
		request.getSession().setAttribute("Desc"+count, desc);
		out.println("<li>Nome: "+name+" <br>");
		out.println("Descrizione:<br>");
		%>
		<form method="post">
		<textarea name="TextArea" style="width: 276px; height: 100px"><%out.print(desc);%></textarea></form>
		<br><a href="<%= response.encodeURL("../Pages/AbilityEditor.jsp?Count="+count)%>">Modifica</a> <a href="<%= response.encodeURL("../Pages/AbilityEditor.jsp?name="+name)%>">Cancella</a>
		<%
		out.println("</li>");
		count++;
	}
	out.println("</ul>");
	}

		%>
		<a href="<%= response.encodeURL("../LoadHomePageServlet")%>">Torna alla home</a>
</div>
</body>

</html>
