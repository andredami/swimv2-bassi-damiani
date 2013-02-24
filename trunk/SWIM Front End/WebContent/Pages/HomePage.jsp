<%@page import="it.polimi.ingsw2.swim.entities.Help.State"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="it.polimi.ingsw2.swim.entities.User" %>
<%@ page import="it.polimi.ingsw2.swim.entities.Notification" %>
<%@ page import="it.polimi.ingsw2.swim.entities.FriendshipRequest" %>
<%@ page import="it.polimi.ingsw2.swim.entities.Help" %>
<%@ page import="it.polimi.ingsw2.swim.entities.Message" %>
<%@ page import="it.polimi.ingsw2.swim.session.remote.ProfileManagerRemote" %>
<%@ page import="it.polimi.ingsw2.swim.session.remote.NotificationManagerRemote" %>
<%@ page import="it.polimi.ingsw2.swim.servlets.SessionAttribute" %>
<% String CONTEXT_PATH = request.getContextPath(); %>
<% SimpleDateFormat datePrinter = new SimpleDateFormat("dd/MM/yyyy"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Popup/popup.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<%
//check if exists a valid session
	Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID.toString());
	User user;
		if (userId == null){
			String url = response.encodeURL(CONTEXT_PATH + "/index.jsp");
			response.sendRedirect(url);
			return;
		} else {
			user = ((ProfileManagerRemote) (new InitialContext()).lookup("ProfileManager/remote")).getUser(userId.toString(), userId.toString());
		} 
%>


<title>Swim</title>

<link href="<%= CONTEXT_PATH %>/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
.auto-style1 {
	margin-top: 0;
}
</style>
</head>
<body>
			
					
<div id="wrapper">
	<div id="wrapper2">
		<div id="header">
			<div id="logo">
		
				<h1>
					<a href="HomePage.html">SWIM</a>
				</h1>
				<label id="labelUser">
					<a href="<%= response.encodeURL(CONTEXT_PATH + "/ProfileServlet")%>"><%=user.getName().toString() %></a>
				</label>
				<%
					String picture = CONTEXT_PATH + (user.getPicture() != null ? ("/upload/" + user.getPicture()) : "/images/imgProfileSmall.gif");
				%>
				<img alt="<%=user.getName().toString() %> Picture" id="userImage" src="<%=picture%>"/>
			</div>

			</div>
		<!-- end #header -->
		<div id="page">
			<!-- end #content -->
			<!-- end #sidebar -->
			<!-- end #widebar -->
			<div id="content" class="auto-style1" style="height: 623px; width: 830px">
				<div class="welcome">
					<div id="navigation" style="height: 22px">
						<ul class="absolute" style="left: 734px; top: 192px; height: 18px">
						    <li><a href="<%= response.encodeURL(CONTEXT_PATH + "/Pages/RequestForHelp.jsp")%>">Cerca Aiuto</a></li>
						    <li><a href="<%= response.encodeURL(CONTEXT_PATH + "/ProfileServlet")%>">Profilo</a></li>
							<li><a href="<%= response.encodeURL(CONTEXT_PATH + "/LogoutServlet")%>">Logout</a></li>						    
						</ul>
					</div>
					<div class="entry">
						<p></p>
						<h3>Home</h3>					
					</div>
					<%
								NotificationManagerRemote notificationManager = (NotificationManagerRemote) (new InitialContext()).lookup("NotificationManager/remote");
					
								List<FriendshipRequest> friendshipRequests = notificationManager.retriveFriendshipRequestsByUser(userId.toString());
								List<Help> helpRequests = notificationManager.retriveHelpRelationStatusByUser(userId.toString());
								List<Message> messages = notificationManager.retriveIncomingMessagesByUser(userId.toString());
								List<Notification> notifications = notificationManager.retriveNotificationsByUser(userId.toString());
							%>
					<div class="notification" style="border-style: groove; left: 76px; top: 297px; height: 263px; width: 787px">
						<div class="notification-inner" style="width: 773px; height: 254px; margin-left: 5px; margin-top: 5px">
							<h4>Notifiche</h4>
							
							<%
								if(notifications.isEmpty()){
							%>
								Nessuna nuova notifica.
							<%
								} else {
								Iterator<Notification> i = notifications.iterator();
							%>
							<ul id='noMargin'>
							<%
								while (i.hasNext()){
								Notification el = i.next();
							%>	
									<li>
									<div id="smallWindow" style="width: 727px">	
										<%out.print(el.getText()); %> - <%out.print(datePrinter.format(el.getTimestamp().getTime())); %>
									</div>
									</li>
							<%
								}
							%>
								</ul>
							<%
								}
							%>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 76px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width:246px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Richieste di amicizia</h4>
							
							<%
								if(friendshipRequests.isEmpty()){
							%>
								Nessuna nuova richiesta di amicizia.
							<%
								} else {
								Iterator<FriendshipRequest> i = friendshipRequests.iterator();
							%>
								<ul id='noMargin'>
							<%
								while (i.hasNext()){
								FriendshipRequest el = i.next();
							%>
									<li>
									<div id="smallWindow">
							<%
								User sender = el.getSender();
								String iPicture = CONTEXT_PATH + (sender.getPicture() != null ? ("/upload/" + sender.getPicture()) : "/images/imgProfileSmall.gif");
							%>			
										<img alt="<%=sender.getName().toString() %> Picture" id="smallImage" height="50" src="<%=iPicture %>" width="50" />
										<div id="requestLabel"><a href="<%=response.encodeURL(CONTEXT_PATH + "/Pages/FriendProfile?profile=" + sender.getId().toString()) %>" target="_blank"><%=sender.getName().toString() %></a> ha richiesto la tua amicizia</div>
										<form method="post" action="<%=response.encodeURL(CONTEXT_PATH + "/FriendshipRequestServlet") %>">
											<input name="newFriendId" type="hidden" value="<%=el.getSender().getId().toString()%>" />
											<input id="acceptButton" name="acceptButton" type="submit" value="Accetta" />
											<input id="denyButton" name="denyButton" type="submit" value="Rifiuta" />
										</form>
									</div>
									</li>
								<%
								}
							%>
								</ul>
							<%
								}
							%>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 340px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width: 245px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Messaggi</h4>
							
							<%
								if(messages.isEmpty()){
							%>
								Nessun nuovo messaggio.
							<%
								} else {
								Iterator<Message> i = messages.iterator();
							%>
								<ul id='noMargin'>
							<%
								while (i.hasNext()){
								Message el = i.next();
							%>
									<li>
									<div id="smallWindow">
							<%
								User sender = el.getSender();
								String iPicture = CONTEXT_PATH + (sender.getPicture() != null && !sender.getPicture().isEmpty() ? ("/upload/" + sender.getPicture()) : "/images/imgProfileSmall.gif");
							%>		
										<img alt="<%=sender.getName() %> Picture" id="smallImage" height="50" src="<%=iPicture %> Picture" width="50"/>
										<div id="requestLabel"><a href="<%=response.encodeURL(CONTEXT_PATH + "/Pages/FriendProfile.jsp?profile=" + sender.getId().toString())%>" target="_blank"><%=sender.getName().toString() %></a> 
											<br />
											(<%=datePrinter.format(el.getTimestamp().getTime()) %>)
											<br />
											<textarea readonly="readonly" rows="4">
											<%=el.getText() %>
											</textarea>
											<br />
											<a href="<%=response.encodeURL(CONTEXT_PATH + "/Pages/Conversation.jsp?msg=" + el.getId().toString()) %>" target="_blank">Rivedi la conversazione e rispondi</a>
										</div>
									</div>

								</li>
							<%
								}
							%>
								</ul>
							<%
								}
							%>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 603px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width: 246px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Richieste aiuto</h4>
							
							<%
								if(helpRequests.isEmpty()){
							%>
								Nessuna richiesta d'aiuto attiva.
							<%
								} else {
								Iterator<Help> i = helpRequests.iterator();
							%>
								<ul id='noMargin'>
							<%
								while (i.hasNext()){
								Help el = i.next();
								if(el.getState() == State.CLOSED){
									continue;
								}
							%>
									<li>
									<div id="smallWindow">
							<%
								User other;
								if(el.getSender().getId() == userId){
									other = el.getAddressee();
								} else {
									other = el.getSender();
								}
								String iPicture = CONTEXT_PATH + (other.getPicture() != null && !other.getPicture().isEmpty() ? ("/upload/" + other.getPicture()) : "/images/imgProfileSmall.gif");
							%>	
										<img alt="<%=other.getName() %> Picture" id="smallImage" height="50" src="<%=iPicture %>" width="50"/>
										<div id="requestLabel"><a href="<%=response.encodeURL(CONTEXT_PATH + "/Pages/FriendProfile.jsp?profile=" + other.getId().toString())%>" target="_blank"><%=other.getName().toString() %></a> 
											<br />
											Abilità: <%=el.getAbility().getName().toString() %>
											<br />
											Stato: <%=el.getState().toString() %>
											<br />
										</div>
										<form method="post" action="/HelpRequest.jsp">
							<%
								if(el.getState() == State.REQUESTED && el.getSender().getId() != userId){
							%>
											<input id="acceptButton" name="acceptButton" type="submit" value="Accetta" />
											<input id="denyButton" name="denyButton" type="submit" value="Rifiuta" />
							<%
								} else if (el.getState() == State.REQUESTED) {
							%>
									Richiesta in attesa di conferma.
							<%
								} else if (el.getState() == State.ACCEPTED) {
							%>
									<input id="closeButton" name="closeButton" type="submit" value="Valuta e chiudi" />
							<%									
								} 
							%>
										</form>
									</div>
								</li>
							<%
								}
							%>
								</ul>
							<%
								}
							%>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!-- end #page -->
	</div>
	<!-- end #wrapper2 -->
</div>
<!-- end #wrapper -->
</body>

</html>
