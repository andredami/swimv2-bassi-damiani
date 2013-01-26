<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="it" http-equiv="Content-Language">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Modifica abilità</title>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<%@ page import="javax.naming.InitialContext"%>
<%@ page
	import="it.polimi.ingsw2.swim.session.remote.AbilityManagerRemote"%>

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

<p>Modifica abilità</p>
<form action="<%= response.encodeURL("../AbilityEditorServlet?oldName="+request.getSession().getAttribute("name")+"&oldDesc="+request.getSession().getAttribute("desc"))%>" method="post">
	Nome: <input name="Name" type="text" value="<%out.print(request.getSession().getAttribute("name")); %>"><br><br>Descrizione:<br>&nbsp;<textarea name="Description" style="width: 308px; height: 134px"><%out.print(request.getSession().getAttribute("desc")); %></textarea><br>
	<br>Alias:<input name="Alias" type="text"><input name="Assegna" type="button" value="Assegna"><br>
	<br>Lista di alias:

	<br><br>
	<input name="SubmitAbility" type="submit" value="Inserisci"><br><br><br><br>
</form>

</body>

</html>
