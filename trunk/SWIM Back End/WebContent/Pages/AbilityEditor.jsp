<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="it" http-equiv="Content-Language">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Modifica abilit�</title>
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

<p>Modifica abilit�</p>
<form action="<%= response.encodeURL("../AbilityEditorServlet")%>" method="post">
	<% String count = (String)request.getParameter("Count"); %>
	Nome: <input name="Name" type="text" value="<% out.print(request.getSession().getAttribute("Name"+count));%>"><br><br>Descrizione:<br>&nbsp;<textarea name="Description" style="width: 308px; height: 134px"><%out.print(request.getSession().getAttribute("Desc"+count));%></textarea><br>
	<br>Alias:<input name="Text2" type="text"><input name="Button1" type="button" value="Assegna"><br>
	<br>Lista di alias:
	<br><br>
	<input name="SubmitAbility" type="submit" value="Inserisci"><br><br><br><br>
</form>

</body>

</html>