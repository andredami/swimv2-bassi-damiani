<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javax.naming.InitialContext" %>
<%@ page import = "it.polimi.ingsw2.swim.entities.User" %>
<%@ page import = "it.polimi.ingsw2.swim.session.remote.ProfileManagerRemote" %>
<%@ page import = "it.polimi.ingsw2.swim.servlets.AbilitySelectionMode" %>
<% 	String CONTEXT_PATH = request.getContextPath(); 
	String addressee = request.getParameter("addressee");
	String ability = request.getParameter("ability");
	if(addressee == null){
		if(ability == null){
			response.sendRedirect(response.encodeURL(CONTEXT_PATH + "/Pages/AbilitySelection.jsp?" + AbilitySelectionMode.FILTER.toString()));
			return;
		} else {
			response.sendRedirect(response.encodeURL(CONTEXT_PATH + "/Pages/RequestForHelp.jsp?chosenAbility=" + ability.replace(' ', '+')));
			return;
		}
	}
	
	ProfileManagerRemote profileManager = (ProfileManagerRemote) (new InitialContext()).lookup("ProfileManager/remote");
	
	User addresseeUser = profileManager.getUser(addressee);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=response.encodeURL(CONTEXT_PATH + "/Popup/popup.js") %>"></script>

<title>Swim</title>

<link href="<%=response.encodeURL(CONTEXT_PATH + "/css/style.css") %>" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
.auto-style1 {
	margin-top: 0;
}
</style>
</head>
<body class="absolute" style="width: 1250px; height: 777px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
			
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 774px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 959px; height: 137px">
		
				<h1><a href="<%=response.encodeURL(CONTEXT_PATH + "/index.jsp") %>">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 635px; width: 819px">
				<div class="welcome">
					<h2 class="title">Completa la richiesta</h2>
					<div class="entry" style="height: 516px"> <br>
						<div class="absolute" style="left: 187px; top: 277px; width: 148px; height: 94px">
							Nome:<%=addresseeUser.getName().getFirstname() %><br />
							Cognome:<%=addresseeUser.getName().getSurname() %><br />
						<br />
						<br />
						<%=ability %><br />
						</div>
						<br /><br /><br /><br /><br /><br />
						Messaggio da allegare alla richiesta:
						<form action="<%=response.encodeURL(CONTEXT_PATH + "/RegisterHelpRequest") %>" style="height: 189px; width: 292px">
							<textarea name="Text" class="absolute" style="border-style:groove; width: 285px; height: 123px; left: 77px; top: 583px;"></textarea><br />
							<input type="hidden" name="ability" id="ability" value="<%=ability %>" />
							<input type="hidden" name="addressee" id="addressee" value="<%=addressee %>" />
							<input name="SubmitRequest" type="submit" value="Invia Richiesta" class="absolute" style="left: 78px; top: 722px; width: 97px; height: 25px" />
						</form>
						
						<div class="absolute" style="width: 86px; left: 283px; top: 726px">
							<a href="javascript:open_win('../Popup/AbusePopup.html', 'AbusePopup');">Segnala Abuso</a>
						</div>
					</div>
						&nbsp;</div>
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
