﻿<%@page import="it.polimi.ingsw2.swim.admin.AddNewAdminServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; " http-equiv="Content-Type">
<title>Aggiungi Nuovo Amministratore</title>
<%
	//check if exists a valid session
	Object adminObj = session.getAttribute("Id");
	if (adminObj == null) {
		String url = response.encodeURL("/index.jsp");
		response.sendRedirect(request.getContextPath() + url);
		return;
	}
%>
</head>

<body>
	<h3>Aggiungi nuovo amministratore</h3>
	<h3>
		<%
			// all the answers to the action taken
			try {
				if (request.getSession().getAttribute(AddNewAdminServlet.Attribute.PASSWORD_ERROR.toString())
						.equals(1)) {
					request.getSession().setAttribute(AddNewAdminServlet.Attribute.PASSWORD_ERROR.toString(), null);
					out.println("Errore. Password non corrispondenti");
				}
			} catch (NullPointerException e) {
			}
			try {
				if (request.getSession().getAttribute(AddNewAdminServlet.Attribute.DATA_ERROR.toString()).equals(1)) {
					request.getSession().setAttribute(AddNewAdminServlet.Attribute.DATA_ERROR.toString(), null);
					out.println("Errore. Dati errati");
				}
			} catch (NullPointerException e) {
			}
			try {
				if (request.getSession().getAttribute(AddNewAdminServlet.Attribute.DUPLICATED_ERROR.toString()).equals(1)) {
					request.getSession().setAttribute(AddNewAdminServlet.Attribute.DUPLICATED_ERROR.toString(), null);
					out.println("Errore. È già presente un admin con questo nome");
				}
			} catch (NullPointerException e) {
			}
		%>
	</h3>
	<form method="post"
		action="<%=response.encodeURL("../AddNewAdminServlet")%>">
		Username:<input name="Username" type="text"><br>
		<br>Email: <input name="Email" type="text"><br>
		<br>Password: <input name="Password" type="password"><br>
		<br>Conferma Password: <input name="ConfirmPassword"
			type="password"><br>
		<br> <input name="Confirm" type="submit" value="Conferma">
		<a href="<%=response.encodeURL("../LoadAdminListServlet")%>">Torna
			alla lista</a>
	</form>
</body>

</html>