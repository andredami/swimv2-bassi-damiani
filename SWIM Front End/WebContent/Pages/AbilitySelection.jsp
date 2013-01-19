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
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 763px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 957px; height: 137px">
		
				<h1><a href="Login.html">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 623px; width: 838px">
				<div class="welcome">
					<h2 class="title">Selezionare le abilità</h2>
					
					<div class="entry"><p>La prima fase della registrazione è 
						andata a buon fine! / Seleziona le abilità che vuoi 
						inserire nel tuo profilo<br />
						Ora serve che tu inserisca almeno un abilità, oppure
						<a href="Registration.html">torna indietro</a>. / Dopo 
						aver inserito le tue abilità, <a href="Profile.html">
						torna al profilo</a>.<br />
								</p>
					</div>
				</div>
				<div class="notification" style="border-style: groove; left: 499px; top: 319px; height: 250px; width: 359px">
						<div class="notification-inner" style="width: 345px; height: 241px; margin-left: 5px; margin-top: 5px">
							Abilità da inserire nella lista (messaggio di errore)<br />
							&nbsp;<ul>
										<li>Nome Abilità 1 <a href="AbilitySelection.html">Rimuovi</a> <a href="AbilitySelection.html">Sottoscrivi</a></li>
										<li id="description">
											<textarea readonly="readonly" name="TextArea1" rows="2" style="width: 285px">Descrizione</textarea>
										</li>
										<li>Nome Abilità 2 <a href="AbilitySelection.html">Rimuovi</a> <a href="AbilitySelection.html">Sottoscrivi</a></li>
										<li id="description">
											<textarea readonly="readonly" name="TextArea2" rows="2" style="width: 285px">Descrizione</textarea>
										</li>
										<li>Nome Abilità 3 <a href="AbilitySelection.html">Rimuovi</a> <a href="AbilitySelection.html">Sottoscrivi</a></li>
										<li id="description">
											<textarea readonly="readonly" name="TextArea3" rows="2" style="width: 285px">Descrizione</textarea>
										</li>							
								</ul>
						</div>
				</div>
							
				<div id="layer6" style="position: absolute; width: 343px; height: 147px; z-index: 1; left: 77px; top: 314px" class="headerTextForm">
							<form method="post" action="Validation.html" style="border-style: ridge; width: 328px; height: 143px; position: absolute; left: 1px; top: 4px; right: 6px;">
								Scrivi direttamente l'abilità che vuoi inserire, 
								oppure<br />
								fai richiesta di una nuova abilità.<br />
								<input class="absolute" name="NextButton" style="left: 520px; top: 283px; height: 27px; width: 262px;" type="submit" value="Prosegui(attivo solo se presente una abilità)" />
								&nbsp;
								<label id="LabelError" class="absolute" style="left: 20px; top: 115px; width: 295px">qui 
								l'errore quando scrive qualcosa di assurdo</label>
							</form>
							<form method="post" action="AbilitySelection.html" style="height: 148px">
								<input class="absolute" name="SubmitAbilityButton" style="left: 182px; top: 81px; height: 30px; width: 129px;" type="submit" value="Inserisci" />
								<input class="absolute" name="TextAbility" style="border-style: outset; left: 25px; top: 49px; width: 205px;" type="text" />
							</form>
								<input class="absolute" name="RequestAbilityButton" type="button" value="Richiedi Nuova Abilità" onclick="open_win('../Popup/RequestAbilityPopup.html','RequestAbilityPopup');" style="left: 26px; top: 81px; width: 143px; height: 31px;" />

						</div>

				<div class="absolute" style="width: 86px; left: 82px; top: 482px">
								<a href="javascript:open_win('../Popup/AbusePopup.html', 'AbusePopup');">Segnala Abuso</a>
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
