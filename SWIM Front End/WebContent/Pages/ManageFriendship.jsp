<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../Popup/popup.js"></script>

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
<body class="absolute" style="width: 1250px; height: 684px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 844px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 957px; height: 137px">
		
				<h1><a href="../index.jsp">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 711px; width: 838px">
				<div class="welcome">
					<h2 class="title">Gestione amicizie</h2>
					
					<div class="entry"><p>Gestisci da qui le tue amicizie, 
						oppure <a href="Profile.html">torna al profilo</a>.<br />
								</p>
					</div>
				</div>
				<div class="notification" style="border-style: groove; left: 84px; top: 516px; height: 293px; width: 322px">
						<div class="notification-inner" style="font-weight:bold; width: 310px; height: 281px; margin-left: 5px; margin-top: 5px">
							<h4>Amici</h4>
								<ul id="noMargin" style="height: 231px">
									<li>
										<div id="smallUserWindow">	
											<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
											<div id="requestLabel" style="left: 57px; top: -48px; bottom: 48px">
												<a href="FriendProfile.html">Utente 1</a> 
												- Sesso 
												- <a href="Profile.html">Rimuovi</a>
											</div>
										</div>

									</li>
									<li>
										<div id="smallUserWindow">	
											<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
											<div id="requestLabel" style="left: 57px; top: -49px; bottom: 49px">
												<a href="FriendProfile.html">Utente 2</a> 
												- Sesso - <a href="Profile.html">Rimuovi</a>
											</div>
										</div>

									</li>
									<li>
										<div id="smallUserWindow">	
											<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
											<div id="requestLabel" style="left: 57px; top: -49px; bottom: 49px">
												<a href="FriendProfile.html">Utente 3</a> 
												- Sesso - <a href="Profile.html">Rimuovi</a>
											</div>
										</div>

									</li>
								</ul>
							<br/>
							<br/>
						</div>
					</div>

				<div class="notification" style="border-style: groove; left: 559px; top: 327px; height: 478px; width: 307px">
						<div class="notification-inner" style="font-weight:bold; width: 295px; height: 467px; margin-left: 5px; margin-top: 5px">
							<h4>Risultati della ricerca</h4>
								<ul id="noMargin" style="height: 413px; width: 299px;">
									<li>
										<div id="smallUserWindow">	
											<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
											<div id="requestLabel" style="left: 57px; top: -48px; bottom: 48px">
												<a href="NotYetFriendProfile.html">Utente 1</a> 
												- Sesso 
											</div>
										</div>

									</li>
									<li>
										<div id="smallUserWindow">	
											<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
											<div id="requestLabel" style="left: 57px; top: -49px; bottom: 49px">
												<a href="NotYetFriendProfile.html">Utente 2</a> 
												- Sesso
											</div>
										</div>

									</li>
									<li>
										<div id="smallUserWindow">	
											<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50"/>
											<div id="requestLabel" style="left: 57px; top: -49px; bottom: 49px">
												<a href="NotYetFriendProfile.html">Utente 3</a> 
												- Sesso
											</div>
										</div>

									</li>
								</ul>
							<br/>
							<br/>
						</div>
					</div>

							
				<div id="layer6" style="position: absolute; width: 343px; height: 147px; z-index: 1; left: 75px; top: 320px" class="headerTextForm">
							<form method="post" action="ManageFriendship.html" style="border-style: ridge; width: 328px; height: 143px; position: absolute; left: 1px; top: 4px; right: 6px;">
								Ricerca l'utente direttamente per nome, cognome 
								oppure inserendo un indirizzo email<br />
								&nbsp;&nbsp;
								<label id="LabelError" class="absolute" style="left: 20px; top: 115px; width: 295px">qui 
								l'errore quando scrive qualcosa di assurdo</label>
								<input class="absolute" name="SubmitAbilityButton" style="left: 248px; top: 47px; height: 30px; width: 73px;" type="submit" value="Ricerca" />
								<input class="absolute" name="TextAbility" style="border-style: outset; left: 25px; top: 49px; width: 205px;" type="text" />&nbsp;&nbsp;
								<select name="Select1" class="absolute" style="left: 26px; top: 82px">
									<option>Nessun Filtro</option>
									<option>Nome</option>
									<option>Cognome</option>
									<option>Email</option>
								</select>
							</form>	
				</div>

				<div class="absolute" style="width: 86px; left: 82px; top: 482px">
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
