<%@page import="it.polimi.ingsw2.swim.servlets.registration.UserActivationServlet.Attribute"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.polimi.ingsw2.swim.servlets.SessionAttribute" %>
<%@ page import="it.polimi.ingsw2.swim.servlets.registration.UserActivationServlet" %>
<%  String CONTEXT_PATH=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>Swim</title>

<link href="<%= CONTEXT_PATH + "/css/style.css" %>" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
.auto-style1 {
	margin-top: 0;
}
.auto-style2 {
	margin-top: 0px;
}
</style>

<%
	Long user = (Long)session.getAttribute(SessionAttribute.USER_ID.toString());
		if (user != null){
			String url = response.encodeURL(request.getContextPath() + "/Pages/HomePage.jsp");
			response.sendRedirect(url);
			return;
		}
%>




</head>
<body class="absolute" style="width: 1250px; height: 684px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
			
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 763px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 959px; height: 137px">
		
				<h1><a href="<%= CONTEXT_PATH + "/index.jsp" %>">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 623px; width: 819px">
				<div class="welcome">
					<h2 class="title">Benvenuto su SWIM!</h2>
					
					<div class="entry"> &nbsp;<div>
						<p>
						SWIMv2 è l'esclusiva piattaforma online che vi permetterà di offrire le vostre abilità lavorative e usufruire delle abilità delle altre persone registrate! Incomincia anche tu registrandoti! </p>
						<p><a href="<%= CONTEXT_PATH + "/Pages/RequestForHelp" %>">Ricerca aiuto anche se non possiedi un account!</a><br /></p>
						<p>
						<%
							if(request.getAttribute(UserActivationServlet.Attribute.FAILED.toString()) != null){
								out.println("<span style=\"font-weight: bolder; color: red;\">Attivazione fallita.</span>");
							//TODO: Adattare a richiesta
							} else if(request.getAttribute(UserActivationServlet.Attribute.SUCCESS.toString()) != null){
								out.println("<span style=\"font-weight: bolder; color: green;\">Utente attivato con successo! Ora puoi effettuare il login.</span>");
							}else if(session.getAttribute("ErrorLogin") != null){
								out.println("<span style=\"font-weight: bolder; color: red;\">Attenzione, accesso fallito! La combinazione di email è password è errata. Ritenta ancora.</span>");
							} 
						%>
						</p>
						</div>
					</div>
				</div>
				<div class="welcome">
					<div class="entry"> &nbsp;<div id="layer6" style="position: absolute; width: 249px; height: 106px; z-index: 1; left: 696px; top: 22px">
							<form method="post" action="<%= response.encodeURL("../swim/LoginServlet")%>" style="width: 246px; height: 102px; position: absolute; left: 1px; top: 3px;">
								<input name="emailText" type="text" class="auto-style2" style="position: absolute; left: 65px; top: 3px; width: 130px;" />
								<label id="labelUser" class="headerText" style="position: absolute; left: -2px; top: 6px; height: 18px; right: 216px;">Email</label>
								<br />
								<br />
								<label id="labelPwd" class="headerText">Password
								<input class="absolute" name="Password" style="left: 66px; top: 33px" type="password" /><br />
								</label>
								<br />
								<input name="rememberBox" type="checkbox" /><span class="headerText"> 
								Resta collegato
								<input name="submitLogin" style="height: 20px; left: 121px; top: 69px" type="submit" value="Accedi" /><br />
								Non hai un account?
								<a href="<%= response.encodeURL("Pages/Registration.jsp")%>">Registrati</a> </span></form>
						</div>
					</div>
				</div>

			</div>
			<!-- end #content -->
			<!-- end #sidebar -->
			<!-- end #widebar -->
		</div>
		<!-- end #page -->
	</div>
	<!-- end #wrapper2 -->
</div>
<!-- end #wrapper -->
</body>

</html>
