<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.polimi.ingsw2.swim.session.remote.RegistrationRemote" %>
<%@ page import="it.polimi.ingsw2.swim.servlets.SessionAttribute" %>
<%@ page import="it.polimi.ingsw2.swim.servlets.registration.ActivationResend" %>
<%	String CONTEXT_PATH = request.getContextPath(); %>
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
RegistrationRemote registrationAgent;
if(session.getAttribute(SessionAttribute.REGISTRATION_AGENT.toString()) == null || session.getAttribute(SessionAttribute.REGISTRATION_COMPLETED.toString()) == null){
	session.setAttribute(SessionAttribute.REGISTRATION_AGENT.toString(), null);
	session.setAttribute(SessionAttribute.REGISTRATION_COMPLETED.toString(), null);
	response.sendRedirect(CONTEXT_PATH + "/Pages/Registration.jsp");
	return;
} else {
	registrationAgent = (RegistrationRemote) session.getAttribute(SessionAttribute.REGISTRATION_AGENT.toString());
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
						<p>Hai quasi fatto! Stai per ricevere una mail che contiene un link che ti permetterą di convalidare la tua registrazione.<br>
						nel caso non avessi ancora ricevuto nulla entro dieci minuti, <a href="<%= CONTEXT_PATH + "/ActivationResend" %>">reinvia la mail di attivazione</a>.
						</p>
						<p>
						<%
								if (request.getAttribute(ActivationResend.Attribute.RESENT.toString())!=null){
						%>
									Mail di attivazione reinviata con successo.
						<%
								}
						%>
						</p>
						</div>
					</div>
				</div>
				<div class="welcome">
					<div class="entry"> &nbsp;
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
