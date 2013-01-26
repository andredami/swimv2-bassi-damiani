<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.polimi.ingsw2.swim.admin.ChangeAdminPasswordServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Cambia password</title>
<%
	//check if exists a valid session
	if (session.getAttribute("Id") == null) {
		String url = response.encodeURL("/index.jsp");
		response.sendRedirect(request.getContextPath() + url);
		return;
	}
%>

</head>

<body>
	<h3>Modifica Password</h3>
	<h3>
		<%
			// all the answers to the action taken
			try {
				if (request
						.getSession()
						.getAttribute(
								ChangeAdminPasswordServlet.Attribute.NOT_MATCHIN_PASSWORD
										.toString()).equals(1)) {
					request.getSession()
							.setAttribute(
									ChangeAdminPasswordServlet.Attribute.NOT_MATCHIN_PASSWORD
											.toString(), null);
					out.println("Errore. La nuova password non è uguale alla conferma. <br>");
				}
			} catch (NullPointerException e) {

			}
			try {
				if (request
						.getSession()
						.getAttribute(
								ChangeAdminPasswordServlet.Attribute.PASSWORD_ERROR
										.toString()).equals(1)) {
					request.getSession()
							.setAttribute(
									ChangeAdminPasswordServlet.Attribute.PASSWORD_ERROR
											.toString(), null);
					out.println("Errore. La password è identica alla precedente. Scegliere un valore differente <br>");
				}
			} catch (NullPointerException e) {

			}
			try {
				if (request
						.getSession()
						.getAttribute(
								ChangeAdminPasswordServlet.Attribute.NO_USER
										.toString()).equals(1)) {
					request.getSession()
							.setAttribute(
									ChangeAdminPasswordServlet.Attribute.NO_USER
											.toString(), null);
					out.println("Errore. Impossibile trovare l'amministratore, riprova. <br>");
				}
			} catch (NullPointerException e) {
			}

			try {
				if (request
						.getSession()
						.getAttribute(
								ChangeAdminPasswordServlet.Attribute.PASSWORD_WRONG
										.toString()).equals(1)) {
					request.getSession()
							.setAttribute(
									ChangeAdminPasswordServlet.Attribute.PASSWORD_WRONG
											.toString(), null);
					out.println("Errore. La password corrente non è esatta. <br>");
				}
			} catch (NullPointerException e) {
			}
		%>
	</h3>
	<form method="post"
		action="<%=response.encodeURL("../ChangeAdminPasswordServlet")%>">
		<input name="IdAdmin"
			value="<%=request.getSession().getAttribute("Id")%>" type="hidden"><br>
		<br> Vecchia Password:<input name="OldPassword" type="password"><br>
		<br> Nuova Password: <input name="NewPassword" type="password"><br>
		<br> Conferma Password: <input name="ConfPassword"
			type="password"><br>
		<br> <input name="Confirm" type="submit" value="Conferma">
		<a href="<%=response.encodeURL("../LoadAdminListServlet")%>">Torna
			alla lista</a>
	</form>
</body>

</html>
