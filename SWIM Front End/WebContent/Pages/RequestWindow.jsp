<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Swim</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>

<body style="left: 0px; top: 0px; width: 229px; height: 86px;">

<div id="smallWindow">	
	<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50">
	<div id="requestLabel" style="left: 56px; top: -50px; bottom: 50px">
		<a href="RequestFriendProfile.html">Utente 1</a> ha richiesto la tua amicizia
	</div>
	
	<form method="post">
		<input id="acceptButton" name="acceptButton" type="submit" value="Accetta" style="top: -43px; left: 92px;">
		<input id="denyButton" name="denyButton" type="submit" value="Rifiuta" style="left: 95px; top: -43px">
	</form>
</div>

</body>

</html>
