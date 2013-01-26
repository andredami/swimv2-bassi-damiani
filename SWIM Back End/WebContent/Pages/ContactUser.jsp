<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="it.polimi.ingsw2.swim.session.remote.UserManagerRemote"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="it.polimi.ingsw2.swim.exceptions.NoSuchUserException"%>
<%@page import="it.polimi.ingsw2.swim.admin.ContactUserServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html;" http-equiv="Content-Type">
<title>Manda un messaggio</title>
<%
	//check if exists a valid session
	Long admin = (Long) session.getAttribute("Id");
	if (admin == null) {
		String url = response.encodeURL("/index.jsp");
		response.sendRedirect(request.getContextPath() + url);
		return;
	}
	boolean message = false;
%>
</head>

<body>
	<h3>Contatta</h3>
	<h3>
		<%
			// all the answers to the action taken
			try {
				if (request
						.getSession()
						.getAttribute(
								ContactUserServlet.Attribute.ADDRESSEE_ERROR
										.toString()).equals(1)) {
					out.println("ERRORE. Il destinatario scelto non esiste");
					message = true;
					request.getSession()
							.setAttribute(
									ContactUserServlet.Attribute.ADDRESSEE_ERROR
											.toString(), null);
				}
			} catch (NullPointerException e) {

			}
			try {
				if (request
						.getSession()
						.getAttribute(
								ContactUserServlet.Attribute.SENDER_ERROR
										.toString()).equals(1)) {
					out.println("ERRORE. Il mittente scelto non esiste");
					message = true;
					request.getSession()
							.setAttribute(
									ContactUserServlet.Attribute.SENDER_ERROR
											.toString(),
									null);
				}
			} catch (NullPointerException e) {

			}
			try {
				if (request
						.getSession()
						.getAttribute(
								ContactUserServlet.Attribute.SENT.toString())
						.equals(1)) {
					out.println("MESSAGGIO INVIATO");
					message = true;
					request.getSession()
							.setAttribute(
									ContactUserServlet.Attribute.SENT
											.toString(),
									null);
				}
			} catch (NullPointerException e) {

			}
			try {
				if (request
						.getSession()
						.getAttribute(
								ContactUserServlet.Attribute.DATA_ERROR.toString())
						.equals(1)) {
					out.println("ERRORE. Non puoi inviare un messaggio con testo vuoto.");
					message = true;
					request.getSession()
							.setAttribute(
									ContactUserServlet.Attribute.DATA_ERROR
											.toString(),
									null);
				}
			} catch (NullPointerException e) {

			}
		%>
	</h3>
	<%
		if (!message) {
			String addressee = request.getParameter("userId");
			InitialContext ctx = new InitialContext();
			UserManagerRemote profileManager = (UserManagerRemote) ctx
					.lookup("UserManager/remote");
			try {
				String userName = profileManager.retriveUserProfile(addressee).getName()
												.getFirstname().toString();
	%>
	<form
		action="<%=response.encodeURL("../ContactUserServlet?id="
							+ request.getSession().getAttribute("userId"))%>"
		method="post">
		<input name="AddresseeId" type="hidden" value="<%=addressee %>">
		Destinatario: <input name="Addressee" type="text" disabled="disabled"
			value="
	<%=userName%>"> <br> <br>Messaggio:<br>
		<textarea name="Message" style="width: 278px; height: 117px"></textarea>
		<br> <br> <input name="Submit" type="submit" value="Invia">
		<%
			} catch (Throwable e) {
		%>
		<h3>Stai tentando di contattare un utente non esistente</h3>
		<%
			}
			}
		%>
		<a href="<%=response.encodeURL("../UserListServlet")%>">Torna
			alla lista degli utenti</a>
	</form>
</body>

</html>

