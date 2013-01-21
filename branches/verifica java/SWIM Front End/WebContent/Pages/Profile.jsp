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
.auto-style2 {
	position: absolute;
	margin-top: 0;
}
</style>
</head>
<body class="absolute" style="width: 1250px; height: 1019px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
			
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 1017px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 959px; height: 137px">
		
				<h1><a href="HomePage.html">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 879px; width: 819px">
				<div class="welcome">
					<div id="navigation" style="height: 22px">
						<ul class="absolute" style="left: 413px; top: 192px; height: 18px">
						    <li><a href="HomePage.html">Home Page</a></li>
						    <li>
							<a href="javascript:open_winProfile('../Popup/ModifyProfilePopup.html', 'ModifyProfilePopup')">Modifica Profilo</a></li>
						    <li>
							<a href="javascript:open_winPassword('../Popup/PasswordPopup.html', 'PasswordPopup')">Modifica Password</a></li>
							<li>
							<a href="ManageFriendship.html">Gestisci amicizie</a></li>
						    <li><a href="DeleteProfile.html">Cancella Profilo</a></li>
						</ul>
					</div>
					

					<h2 class="title">Nome Cognome</h2>
					<div class="entry" style="height: 733px">
					
						<img src="../images/img06.jpg" height="93" width="138" /> <br /><br />
						<ul>
							<li>Nome:</li>
							<li>Cognome:</li>
							<li>Sesso:</li>
							<li>Email:</li>
							<li>Data di Nascita:</li>
							<li>Indirizzo:</li>
							<li>Numero di telefono:</li>
							<li>Cellulare:</li>
							<li>Fax:</li>
							<li>Username Skype:</li>
						</ul>
						<div>
							<hr></hr><br>
						</div>
						<div class="notification" style="border-style: groove; left: 76px; top: 631px; height: 293px; width: 313px">
						<div class="notification-inner" style="font-weight:bold; width: 299px; height: 281px; margin-left: 5px; margin-top: 5px">
							<h4>Abilità</h4>
								<form action="Profile.html">
								<ul id="noMargin" style="height: 231px">
										<li>
										<input name="Checkbox4" type="checkbox" value="ON" /> Nome Abilità 1</li>
										<li>Descrizione</li>
										<li>
										<input name="Checkbox5" type="checkbox" value="ON" /> Nome Abilità 2</li>
										<li>Descrizione</li>
										<li>
										<input name="Checkbox6" type="checkbox" value="ON" /> Nome Abilità 3 </li>
										<li>Descrizione</li>
										<input name="RemoveButton1" type="submit" value="Rimuovi Abilità Selezionate" class="auto-style2" style="left: 147px; top: 308px; width: 170px;" />
										<input name="InsertButton1" type="button" onclick="location.href='AbilitySelection.html'" value="Inserisci Nuova Abilità" class="absolute" style="left: -3px; top: 308px; width: 141px;" />
								</ul>
								</form>
							<br/>
						</div>
					</div>
					<div class="notification" style="border-style: groove; left: 577px; top: 631px; height: 293px; width: 313px">
						<div class="notification-inner" style="font-weight:bold; width: 299px; height: 281px; margin-left: 5px; margin-top: 5px">
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
