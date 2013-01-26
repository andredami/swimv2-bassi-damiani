<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Lista Abusi</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<%@ page import="it.polimi.ingsw2.swim.admin.ManageAbuseServlet" %>

<% 
	// check if exists a valid session
	Long admin = (Long)session.getAttribute("Id");
		if (admin == null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>
</head>

<body>
<h3>Lista abusi</h3>

<%

	try{
		List<Abuse> a = (List<Abuse>)request.getSession().getAttribute(ManageAbuseServlet.Attribute.LIST.toString());
		Iterator<Abuse> i = a.iterator();
		if (i.hasNext()){
			out.println("<ul>");
			while (i.hasNext()){
				Abuse el = i.next();
				Long abuseId = el.getId();
				if (el.isHandled()){
					out.println("<br>GESTITO<br>");
				}
				out.println("<li>Mittente: "+el.getEmail()+" <br>");
				out.println("Descrizione abuso:<br>");
				%>
				<form method="post" action="<%= response.encodeURL("../ManageAbuseServlet?Count="+ abuseId)%>">
				<textarea name="Description" style="width: 286px; height: 107px"><%out.print (el.getDescriprion());%></textarea><br>
				<%
				if (!el.isHandled()){
					out.println("<input name='ManagedButton' type='submit' value='Gestito'>");
					out.println("<input name='Remove' type='submit' value='Ignora'>");
				}
				%>
				</form>
				<%
				out.println("</li>");				
			}
			out.println("</ul>");
		}
	}
	catch (NullPointerException e){
		out.println("Non ci sono abusi richiesti.");
	}
	
		%>
		<a href="<%= response.encodeURL("../LoadHomePageServlet")%>">Torna alla home</a>
</body>

</html>
