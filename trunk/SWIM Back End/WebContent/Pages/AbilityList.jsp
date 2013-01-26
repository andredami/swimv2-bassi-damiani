<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html;" http-equiv="Content-Type">
<title>Lista abilità</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<%@ page import="it.polimi.ingsw2.swim.admin.AbilityListServlet" %>
<%@ page import="it.polimi.ingsw2.swim.admin.AbilityEditorServlet" %>
<%@ page import="it.polimi.ingsw2.swim.admin.FilterAbilityServlet" %>
<%@ page import="it.polimi.ingsw2.swim.admin.CancelAbilityServlet" %>

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

	try{ 
		List<Ability> a;
		// verify if a filter is applied
		if (request.getSession().getAttribute(FilterAbilityServlet.Attribute.FILTER.toString())!=null){
			request.getSession().setAttribute(FilterAbilityServlet.Attribute.FILTER.toString(), null);
			a = (List<Ability>) request.getSession().getAttribute(FilterAbilityServlet.Attribute.LIST.toString());
		}
		else{ 
			if (request.getSession().getAttribute(CancelAbilityServlet.Attribute.CANCEL.toString())!=null){
				request.getSession().setAttribute(CancelAbilityServlet.Attribute.CANCEL.toString(),null);
				a = (List<Ability>) request.getSession().getAttribute(CancelAbilityServlet.Attribute.LIST.toString());
		}else{
			if (request.getSession().getAttribute(AbilityEditorServlet.Attribute.MODIFY.toString())!=null){
				request.getSession().setAttribute(AbilityEditorServlet.Attribute.MODIFY.toString(),null);
				a = (List<Ability>) request.getSession().getAttribute(CancelAbilityServlet.Attribute.LIST.toString());
		}else{
			
			// if not, display all the abilities	
			a = (List<Ability>) request.getSession().getAttribute(AbilityListServlet.Attribute.LIST.toString());
		}
		}
			}
		
			
		if (a.isEmpty()){ 
			out.println("Non ci sono abilità registrate nel database.");
		}
		Iterator<Ability> i = a.iterator();
		if (i.hasNext()){
		out.println("<ul>");
		while (i.hasNext()){
			Ability el = i.next();
			String name = el.getName();
			String desc = el.getDescription();
			out.println("<li>Nome: "+name+" <br>");
			out.println("Descrizione:<br>");
			%>
			<form method="post">
			<textarea name="Desc" disabled="disabled" style="width: 276px; height: 100px"><%out.print(desc);%></textarea></form>
			<br><a href="<%= response.encodeURL("../LoadAbilityEditorServlet?name="+name+"&desc="+desc)%>">Modifica</a> <a href="<%= response.encodeURL("../CancelAbilityServlet?name="+name)%>">Cancella</a>
			<%
			out.println("</li>");
		}
		out.println("</ul>");
		}
	}catch(NullPointerException e){
		out.println("Non ci sono abilità disponibili.");
	}
		%>
		<a href="<%= response.encodeURL("../LoadHomePageServlet")%>">Torna alla home</a>
</div>
</body>

</html>
