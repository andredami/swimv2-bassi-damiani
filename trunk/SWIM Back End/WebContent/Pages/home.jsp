<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="it" http-equiv="Content-Language">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<script type="text/javascript" src="../jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="functionsAdmin.js"></script>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<jsp:useBean id="aut" scope="session" class="it.polimi.ingsw2.swim.session.administration.Authentication" />
<jsp:useBean id="listAbility" scope="session" class="it.polimi.ingsw2.swim.session.administration.RequestManager" />
<% 
String user = request.getParameter("Username");
String pass = request.getParameter("Password");
	if (!(aut.authenticate(user, pass))){
		response.sendRedirect(response.encodeRedirectURL("../index.jsp"));
	}	
	session.setAttribute("Name", user);
	
%>

<title>Home admin</title>
</head>

<body>

<h3>Benvenuto <% session.getAttribute("Name"); %>! (<a href="../index.jsp">Logout</a>)</h3>
<a href="AdminList.jsp">Lista degli admin</a><br>
<a href="UserList.jsp">Lista degli Utenti</a><br>
<a href="AbuseList.jsp">Gestisci abusi</a>
<br>
<br>
<br>
<a href="AbilityList.jsp">Lista delle abilità</a><br>
<a href="AbilityEditor.jsp">Aggiungi nuova abilità</a><br>
<a href="">Aggiungi abilità come alias</a><br><br>
<div>
	Lista di abilità richieste:
	<% 
	List<Ability> a = listAbility.retriveRequestsList();
	Iterator<Ability> i = a.iterator();
	if (i.hasNext()){
		out.println("<ul>");
		while (i.hasNext()){
			Ability el = i.next();
			out.println("<li> Nome abilità: "+ el.getName()+"<br>");
			out.println("Descrizione:" + el.getDescription()+"<br>");
			out.println("Numero di sottoscrizioni:" + el.getSubscribers().size()+"<br>");
			out.println("<a href=" + "" + ">Aggiungi</a> <a href=" +""+">Cancella</a>");
			out.println("</li>");				
		}
		out.println("</ul>");
	}
	%>
</div>

</body>

</html>
