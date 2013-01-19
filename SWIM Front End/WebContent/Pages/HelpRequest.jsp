<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>Swim</title>
<script type="text/javascript" src="../Popup/popup.js"></script>


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
			<div id="logo" style="width: 959px; height: 137px">
		
				<h1><a href="HomePage.html">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 623px; width: 819px">
				<div class="welcome">
					<h2 class="title">Messaggio di richiesta di aiuto</h2>
					
					<div class="entry" style="height: 292px"> Data e ora di ricezione: 
						gg/mm/aaaa<br />
						Mittente: <a href="FriendProfile.html">Utente1</a><br />
						Destinatario: Tu<br />
						<br />
						<form method="post" action="ReviewConfirmation.html">
							<textarea name="TextArea1" readonly="readonly" style="border-style: inset; width: 813px; height: 169px">Messaggio di richiesta di aiuto</textarea>
							<input name="acceptButton" type="submit" value="Accetta richiesta" class="absolute" style="left: 791px; top: 511px; width: 108px; height: 37px;" />
							<input name="denyButton" type="submit" value="Rifiuta richiesta" class="absolute" style="left: 680px; top: 511px; height: 37px; width: 104px;" />
							<div class="absolute" style="width: 86px; left: 82px; top: 511px">
								<a href="javascript:open_win('../Popup/AbusePopup.html', 'AbusePopup')">Segnala Abuso</a>
							</div>
						</form>
							<input name="replyButton" type="submit" onclick="window.location.href='Message.html'" value="Rispondi alla richiesta" class="absolute" style="left: 530px; top: 511px; width: 142px; height: 37px;" />

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
