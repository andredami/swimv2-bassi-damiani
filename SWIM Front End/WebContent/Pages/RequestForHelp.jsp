<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>
<%@ page import = "it.polimi.ingsw2.swim.entities.User" %>
<%@ page import = "it.polimi.ingsw2.swim.servlets.HelpRequestUser" %>
<%@ page import = "it.polimi.ingsw2.swim.servlets.SessionAttribute" %>
<%@ page import = "it.polimi.ingsw2.swim.servlets.AbilitySelectionMode" %>
<% String CONTEXT_PATH = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>Swim</title>

<link href="<%=response.encodeURL(CONTEXT_PATH + "/css/style.css") %>" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
.auto-style1 {
	margin-top: 0;
}
</style>
</head>
<body class="absolute" style="width: 1250px; height: 684px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 763px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 957px; height: 137px">
		
				<h1><a href="<%=response.encodeURL(CONTEXT_PATH + "/index.jsp") %>">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 623px; width: 838px">
				<div class="welcome">
					<h2 class="title">Ricerca di aiuto</h2>
					
					<div class="entry"><p>Ricerca l'utente che può darti una 
						mano ricercando una sua abilità specifica. <br />
						Oppure <a href="<%=response.encodeURL(CONTEXT_PATH + "/index.jsp") %>">
						torna alla home</a>.<br />
								</p>
					</div>
				</div>
				<div class="notification" style="border-style: groove; left: 528px; top: 320px; height: 341px; width: 353px">
						<div class="notification-inner" style="font-weight:bold; width:337px; height: 335px; margin-left: 5px; margin-top: 5px">
							<h4>Risultati della ricerca</h4>
							<%
								@SuppressWarnings("unchecked")
								List<User> a = (List<User>) request.getAttribute(HelpRequestUser.Attribute.RESULT.toString());
								if(a != null && !a.isEmpty()){
							%>
							<ul id="noMargin">
							<%
								Iterator<User> i = a.iterator();
									while (i.hasNext()){
										User el = i.next();
							%>
							<li>
							<div id="smallWindow" style="width: 309px">	
							<%
								String iPicture = CONTEXT_PATH + (el.getPicture() != null ? ("/upload/" + el.getPicture()) : "/images/imgProfileSmall.gif");
							%>
								<img alt="" id="smallImage" height="50" src="<%=iPicture %>" width="50" />
								<div id="requestLabel" style="left: 56px; top: -50px; bottom: 50px; width: 249px"><a href="<%=response.encodeURL(CONTEXT_PATH + "/Pages/FriendProfile?profile=" + el.getId().toString()) %>" target="_blank"><%=el.getName().toString()%></a></div>
								<form method="post" action="<%=response.encodeURL(CONTEXT_PATH + "/Pages/AskForHelp.jsp")%>">
									<input type="hidden" id="ability" name="ability" value="<%=request.getParameter("chosenAbility") %>"/>
									<input type="hidden" id="addressee" name="addressee" value="<%=el.getId().toString() %>"/>
									<input id="selectButton" name="selectButton" type="submit" value="Seleziona" style="position:relative; left: 240px; top: -42px; width: 66px; height: 21px" />
								</form>
								</div>
							</li>
							<% 
									}
							%>
							</ul>
							<%
								} else {
							%>
							<p>Nessun utente corrisponde ai parametri di ricerca immessi. <br />
							<a href="<%= CONTEXT_PATH + "/Pages/AbilitySelection.jsp?" + AbilitySelectionMode.FILTER.toString() %>">Seleziona un'altra abilità</a> e riprova.</p>
							<%
								}
							%>
						</div>
					</div>
				<div class="absolute" style="width: 86px; left: 82px; top: 628px">
								<a href="javascript:open_win('../Popup/AbusePopup.html', 'AbusePopup');">Segnala Abuso</a></div>
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
