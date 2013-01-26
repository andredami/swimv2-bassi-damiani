<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html;" http-equiv="Content-Type">
<title>Lista Utenti</title>
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
</head>

<body>
<h3>Lista Utenti</h3>

<%
try{
	List<User> a = new ArrayList<User>();
	a = (List<User>)request.getSession().getAttribute("userList");
	if (a.isEmpty())
		out.println("Non sono presenti utenti registrati nel database.");
	Iterator<User> i = a.iterator();
	if (i.hasNext()){
	out.println("<ul>");
	while (i.hasNext()){
		User el = i.next();
		Long id = el.getId();
		out.println("<li><img src='"+el.getPicture()+"'><br>"+el.getName().getFirstname()+""+ el.getName().getSurname()+" - "+el.getGender().toString()+" - <a href='../LoadUserProfileServlet?id="+id+">Mostra Profilo</a><br>");
		out.println("</li>");				
	}
	out.println("</ul>");
	}
}catch(NullPointerException e){
	out.println("Non ci sono utenti nel sistema.");
}

%>
<br>
<a href="<%= response.encodeURL("../LoadHomePageServlet")%>">Torna alla home</a>
</body>

</html>
