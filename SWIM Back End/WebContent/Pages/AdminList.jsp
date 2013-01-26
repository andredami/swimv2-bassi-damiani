<%@page import="it.polimi.ingsw2.swim.admin.AddNewAdminServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Admin List</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<%@ page import="it.polimi.ingsw2.swim.admin.LoadAdminListServlet" %>
<%@ page import="it.polimi.ingsw2.swim.admin.ChangeAdminPasswordServlet" %>

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
<h3>Lista degli amministratori</h3>

<%
	if(request.getSession().getAttribute(AddNewAdminServlet.Attribute.INSERTED.toString())!=null){
%>
		<h4>Amministratore aggiunto correttamente</h4>
<%
		request.getSession().setAttribute(AddNewAdminServlet.Attribute.INSERTED.toString(), null);
	}

if (request
		.getSession()
		.getAttribute(
				ChangeAdminPasswordServlet.Attribute.EDITED
						.toString())!=null) {
	request.getSession()
			.setAttribute(
					ChangeAdminPasswordServlet.Attribute.EDITED
							.toString(), null);
	%>
		<h4>Password modificata correttamente</h4>
	<%
	}
	try{
		List<Administrator> a = (List<Administrator>) request.getSession().getAttribute(LoadAdminListServlet.Attribute.LIST.toString());
		Iterator<Administrator> i = a.iterator();
		if (i.hasNext()){
			out.println("<ul>");
			while (i.hasNext()){
				Administrator el = i.next();
				String addr = el.getUsername();
				Long adminId = el.getId();
				out.println("<li>");
				out.println("Username: "+ el.getUsername()+"<br>");
				out.println("Email: " + el.getEmail()+"<br>");
				if(!el.getId().equals(request.getSession().getAttribute("Id"))){
%>
			<a href="<%=response.encodeURL("../Pages/ContactAdmin.jsp?Count=" + adminId)%>">Contatta</a>
			&nbsp; - &nbsp;
			<a href="<%=response.encodeURL("../DeleteAdmin?Id=" + adminId)%>">Elimina</a>
<% 
				}
			out.println("</li>");	
		}
		out.println("</ul>");
		}
		request.getSession().setAttribute(LoadAdminListServlet.Attribute.LIST.toString(), null);
	}catch(NullPointerException e){
		out.println("Non ci sono amministratori.");
	}
	
%>

<a href="../Pages/AddNewAdmin.jsp">Aggiungi nuovo amministratore</a><br>
<a href="../Pages/ChangePassword.jsp">Cambia la mia password</a><br>
<br>
<a href="<%= response.encodeURL("../LoadHomePageServlet")%>">Torna alla home</a>

</body>

</html>
