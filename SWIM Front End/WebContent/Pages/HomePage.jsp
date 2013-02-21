<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Popup/popup.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<%
//check if exists a valid session
	Long user = (Long)session.getAttribute("Id");
		if (user == null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>


<title>Swim</title>

<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
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
					<a href="<%= response.encodeURL("../ProfileServlet")%>">Nome cognome</a>
				</label>
				<img alt="" id="userImage" />
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
						    <li><a href="../Pages/RequestForHelp.jsp">Cerca Aiuto</a></li>
						    <li><a href="/ProfileServlet">Profilo</a></li>
							<li><a href="Login.html">Logout</a></li>						    
						</ul>

					</div>

					
					<div class="entry">
						<p></p>
						<h3>Home</h3>
					
					</div>
					<div class="notification" style="border-style: groove; left: 76px; top: 297px; height: 263px; width: 787px">
						<div class="notification-inner" style="width: 773px; height: 254px; margin-left: 5px; margin-top: 5px">
							<h4>Notifiche</h4>
							<%
							
							
							List<Notification> a;
							
							if (request.getSession().getAttribute(CreateHomePageServlet.Attribute.NOTIFICATION.toString())!= null){
								a = (List<Notification>) request.getSession().getAttribute(CreateHomePageServlet.Attribute.NOTIFICATION.toString());
								if (a.isEmpty()){
									out.println("Non ci sono notifiche al momento.");
								// if a is empty messaggio
								Iterator<Notification> i = a.iterator();
								if (i.hasNext()){
								out.println("<ul id='noMargin'>");
								while (i.hasNext()){
								Notification el = i.next();
								%>	
									<li>
									<div id="smallWindow" style="width: 727px">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel" style="left: 61px; top: -50px; bottom: 50px; width: 655px"><a href=""><%out.print(el.getAddressee().getName().getFirstname()+" "+ el.getAddressee().getName().getSurname()); %></a> 
											<%out.print(el.getText()); %> - <%out.print(el.getTimestamp()); %></div>
										<form method="post" action="/NotificationReadServlet">
											<input type="submit" name="notificationButton" value="Segna come letta" style="left: 607px; top: -42px; width: 110px"/>										</form>
									</div>
									</li>
								<%
								}
								out.println("</ul>");
							
								}
								}
							}else{
								out.println("Non ci sono notifiche al momento.");
							}
							
							
							%>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 76px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width:246px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Richieste amicizia</h4>
							
							<% 
							List<FriendshipRequest> b;
							
							if (request.getSession().getAttribute(CreateHomePageServlet.Attribute.FRIEND.toString())!= null){
								b = (List<FriendshipRequest>) request.getSession().getAttribute(CreateHomePageServlet.Attribute.FRIEND.toString());
								if (b.isEmpty()){
									out.println("Non ci sono richieste di amicizia al momento.");
								// if a is empty messaggio
								Iterator<FriendshipRequest> i = b.iterator();
								if (i.hasNext()){
								out.println("<ul id='noMargin'>");
								while (i.hasNext()){
								FriendshipRequest el = i.next();
								%>	
									<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel"><a href=""><%out.print(el.getSender().getName().getFirstname()+" "+ el.getSender().getName().getSurname()); %></a> ha richiesto la tua amicizia</div>
										<form method="post" action="/FriendshipRequestServlet">
											<input id="acceptButton" name="acceptButton" type="submit" value="Accetta" />
											<input id="denyButton" name="denyButton" type="submit" value="Rifiuta" />
										</form>
									</div>
									</li>
								<%
								}
								out.println("</ul>");
							
								}
								}
							}else{
								out.println("Non ci sono richieste di amicizia al momento.");
							}
												
							%>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 340px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width: 245px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Feedback</h4>
							
							<% 
							List<Message> c;
							
							if (request.getSession().getAttribute(CreateHomePageServlet.Attribute.MESSAGE.toString())!= null){
								c = (List<Message>) request.getSession().getAttribute(CreateHomePageServlet.Attribute.MESSAGE.toString());
								if (c.isEmpty()){
									out.println("Non ci sono richieste di feedback al momento.");
								// if a is empty messaggio
								Iterator<Message> i = c.iterator();
								if (i.hasNext()){
								out.println("<ul id='noMargin'>");
								while (i.hasNext()){
								Message el = i.next();
								%>	
									<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href="FriendProfile.html"><%out.print(el.getSender()); %></a> 
											<br />
											<% %><br />
											DateTime
										</div>
										<form method="post">
											<input type="button" onclick="javascript:open_winFeedback('../Popup/FeedbackPopup.html', 'feedbackPopup');" id="feedbackButton" name="feedbackButton" value="Valuta"/>
										</form>
									</div>

								</li>
								<%
								}
								out.println("</ul>");
							
								}
								}
							}else{
								out.println("Non hai feedback da dare al momento.");
							}
												
							%>
							
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 603px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width: 246px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Richieste aiuto</h4>
							
							<% 
							List<Help> d;
							
							if (request.getSession().getAttribute(CreateHomePageServlet.Attribute.HELP.toString())!= null){
								d = (List<Help>) request.getSession().getAttribute(CreateHomePageServlet.Attribute.HELP.toString());
								if (d.isEmpty()){
									out.println("Non ci sono richieste di aiuto al momento.");
								// if a is empty messaggio
								Iterator<Help> i = d.iterator();
								if (i.hasNext()){
								out.println("<ul id='noMargin'>");
								while (i.hasNext()){
								Help el = i.next();
								%>	
									<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href=""><%out.print(el.getSender().getName().getFirstname()+" "+ el.getSender().getName().getSurname()); %></a> 
											<br />
											<%out.print(el.getAbility()); %><br />
											<%out.print(el.getTimestamp()); %>
										</div>
										<form method="post" action="/HelpRequest.jsp">
											<input type="submit" id="feedbackButton" name="helpRequestButton"value="Vai alla richiesta" style="left: 105px; top: -43px; width: 110px"/>
										</form>
									</div>

								</li>
								<%
								}
								out.println("</ul>");
							
								}
								}
							}else{
								out.println("Non hai richieste di aiuto per ora.");
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
