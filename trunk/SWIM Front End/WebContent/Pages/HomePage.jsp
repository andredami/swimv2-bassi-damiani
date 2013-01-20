<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Popup/popup.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

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
					<a href="Profile.html">Nome cognome</a>
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
						<ul class="absolute" style="left: 658px; top: 192px; height: 18px">
						    <li><a href="RequestForHelp.html">Cerca Aiuto</a></li>
						    <li><a href="Profile.html">Profilo</a></li>	
						    <li><a href="SearchFriends.html">Cerca Amici</a></li>
							<li><a href="../index.jsp">Logout</a></li>						    
						</ul>
					</div>

					
					<div class="entry">
						<p></p>
						<h3>Home</h3>
					
					</div>
					<div class="notification" style="border-style: groove; left: 76px; top: 297px; height: 263px; width: 787px">
						<div class="notification-inner" style="width: 773px; height: 254px; margin-left: 5px; margin-top: 5px">
							<h4>Notifiche</h4>
							<ul id="noMargin">
								<li>
									<div id="smallWindow" style="width: 727px">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel" style="left: 61px; top: -50px; bottom: 50px; width: 655px"><a href="RequestFriendProfile.html">Utente 1</a> 
											blablabla - DateTime</div>
										<form method="post" action="HelpRequest.html">
											<input type="submit" id="feedbackButton" name="notificationButton" value="Leggi la notifica" style="left: 607px; top: -42px; width: 110px"/>										</form>
									</div>
								</li>
								<li>
									<div id="smallWindow" style="width: 727px">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel" style="left: 62px; top: -50px; bottom: 50px; width: 655px"><a href="RequestFriendProfile.html">Utente 2</a> 
											blablabla - DateTime</div>
										<form method="post" action="HelpRequest.html">
											<input type="submit" id="feedbackButton" name="notificationButton"value="Leggi la notifica" style="left: 607px; top: -42px; width: 110px"/>										</form>
									</div>
								</li>

								<li>
									<div id="smallWindow" style="width: 727px">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel" style="left: 61px; top: -50px; bottom: 50px; width: 655px"><a href="RequestFriendProfile.html">Utente 3</a> 
											blablabla - DateTime</div>
										<form method="post" action="HelpRequest.html">
											<input type="submit" id="feedbackButton" name="notificationButton"value="Leggi la notifica" style="left: 607px; top: -42px; width: 110px"/>										</form>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 76px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width:246px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Richieste amicizia</h4>
							<ul id="noMargin">
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel"><a href="NotYetFriendProfile.html">Utente 1</a> ha richiesto la tua amicizia</div>
										<form method="post">
											<input id="acceptButton" name="acceptButton" type="submit" value="Accetta" />
											<input id="denyButton" name="denyButton" type="submit" value="Rifiuta" />
										</form>
									</div>
								</li>
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel"><a href="NotYetFriendProfile.html">Utente 2</a> ha richiesto la tua amicizia</div>
										<form method="post">
											<input id="acceptButton" name="acceptButton" type="submit" value="Accetta" />
											<input id="denyButton" name="denyButton" type="submit" value="Rifiuta" />
										</form>
									</div>
								</li>

								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel"><a href="RequestFriendProfile.html">Utente 3</a> ha richiesto la tua amicizia</div>
										<form method="post">
											<input id="acceptButton" name="acceptButton" type="submit" value="Accetta" />
											<input id="denyButton" name="denyButton" type="submit" value="Rifiuta" />
										</form>
									</div>
								</li>
							</ul>

						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 340px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width: 245px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Feedback</h4>
							<ul id="noMargin">
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href="FriendProfile.html">Utente 1</a> 
											<br />
											Abilità<br />
											DateTime
										</div>
										<form method="post">
											<input type="button" onclick="javascript:open_winFeedback('../Popup/FeedbackPopup.html', 'feedbackPopup');" id="feedbackButton" name="feedbackButton" value="Valuta"/>
										</form>
									</div>

								</li>
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href="FriendProfile.html">Utente 2</a> 
											<br />
											Abilità<br />
											DateTime</div>
										<form method="post">
											<input type="button" onclick="javascript:open_winFeedback('../Popup/FeedbackPopup.html', 'feedbackPopup');" id="feedbackButton" name="feedbackButton"value="Valuta"/>
										</form>
									</div>
								</li>
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href="FriendProfile.html">Utente 3</a> 
											<br />
											Abilità<br />
											DateTime</div>
										<form method="post">
											<input type="button" onclick="javascript:open_winFeedback('../Popup/FeedbackPopup.html', 'feedbackPopup');" id="feedbackButton" name="feedbackButton"value="Valuta"/>
										</form>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 603px; top: 566px; height: 479px; width: 260px">
						<div class="notification-inner" style="font-weight:bold; width: 246px; height: 470px; margin-left: 5px; margin-top: 5px">
							<h4>Richieste aiuto</h4>
							<ul id="noMargin">
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href="FriendProfile.html">Utente 1</a> 
											<br />
											Abilità<br />
											DateTime
										</div>
										<form method="post" action="HelpRequest.html">
											<input type="submit" id="feedbackButton" name="helpRequestButton"value="Vai alla richiesta" style="left: 105px; top: -43px; width: 110px"/>
										</form>
									</div>

								</li>
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href="FriendProfile.html">Utente 1</a> 
											<br />
											Abilità<br />
											DateTime
										</div>
										<form method="post" action="HelpRequest.html">
											<input type="submit" id="feedbackButton" name="helpRequestButton"value="Vai alla richiesta" style="left: 105px; top: -43px; width: 110px"/>

										</form>
									</div>
								</li>
								<li>
									<div id="smallWindow">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
										<div id="requestLabel"><a href="FriendProfile.html">Utente 1</a> 
											<br />
											Abilità<br />
											DateTime
										</div>
										<form method="post">
											<input type="button" id="feedbackButton" name="helpRequestButton" value="Vai alla richiesta" style="left: 105px; top: -43px; width: 110px"/>
										</form>
									</div>
								</li>
							</ul>
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
