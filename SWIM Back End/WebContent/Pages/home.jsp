<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="it" http-equiv="Content-Language">
<meta content="text/html;" http-equiv="Content-Type">
<script type="text/javascript" src="../jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="functionsAdmin.js"></script>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<% 
	// check if exists a valid session
	Long admin = (Long)session.getAttribute("Id");
		if (admin == null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>

<title>Home admin</title>
</head>

<body>

<h3>Benvenuto <% out.print(request.getSession().getAttribute("Admin")); %>! (<a href="<%= response.encodeURL("../LogOutServlet") %>">Logout</a>)</h3>
<% 
	// all results of well closed operations
	try {
	if (request.getSession().getAttribute("PasswordChanged").equals(1)){
		request.getSession().setAttribute("PasswordChanged", null);
		out.println("La password è stata aggiornata correttamente!");
		out.println("<br>");
	}
	} catch (NullPointerException e){
		
	}

%>
<a href="<%= response.encodeURL("../LoadAdminListServlet")%>">Lista degli admin</a><br>
<a href="<%= response.encodeURL("../UserListServlet")%>">Lista degli Utenti</a><br>
<a href="<%= response.encodeURL("../AbuseListServlet")%>">Gestisci abusi</a>
<br>
<br>
<br>
<a href="<%= response.encodeURL("../AbilityListServlet")%>">Lista delle abilità</a><br>
<a href="<%= response.encodeURL("../AbilityCreatorServlet")%>">Aggiungi nuova abilità</a><br>
<a href="">Aggiungi abilità come alias</a><br><br>
<div>
	Lista di abilità richieste:
	<% 

		List<Ability> a = new ArrayList<Ability>();
		a = (List<Ability>)request.getSession().getAttribute("list");
		if (a.isEmpty())
			out.println("Non ci sono richieste di abilità per ora.");
		ListIterator<Ability> i = a.listIterator();
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
