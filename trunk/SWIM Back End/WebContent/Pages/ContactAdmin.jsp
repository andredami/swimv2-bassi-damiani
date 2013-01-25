<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html;" http-equiv="Content-Type">
<title>Manda un messaggio</title>
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
<h3>Contatta</h3>
<h3><%
	// all the answers to the action taken
	try{
	if (request.getSession().getAttribute("DataError").equals(1)){
		request.getSession().setAttribute("DataError", null);
		out.println("Errore. Non esiste il mittente scelto. <br>");
	}
	} catch (NullPointerException e){
		
	}

%></h3>
<% String count = (String)request.getParameter("Count"); 
	request.getSession().setAttribute("id", request.getSession().getAttribute("adminId"+count));
	%>
<form action="<%= response.encodeURL("../ContactAdminServlet?id="+request.getSession().getAttribute("id"))%>" method="post">

	Destinatario: <input name="Addressee" type="text" disabled="disabled" value="
	<% // select a differente addressee, depending on who requests this page
		out.println(request.getSession().getAttribute("admin"+count));
	%>">
		<br><br>Messaggio:<br>
	<textarea name="Message" style="width: 278px; height: 117px"></textarea><br>
	<br><input name="Submit" type="submit" value="Invia">
	<a href="<%= response.encodeURL("../LoadAdminListServlet")%>">Torna alla lista</a></form>

</body>

</html>
