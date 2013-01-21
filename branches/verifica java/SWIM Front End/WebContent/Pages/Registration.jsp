<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8;" />
<script type="text/javascript" src="../jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="functions.js"></script>
<!-- al caricamento della pagina, il checkbox è sempre disattivato -->
<script>
$(document).load(function () {
	$("#prosegui").attr("disabled","disabled");
});
</script>
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
<%@ page language="java" import="java.util.*, java.lang.System" %>


<body onload="javascript:setCheckToNone();" class="absolute" style="width: 1250px; height: 684px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 763px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 957px; height: 137px">
		
				<h1><a href="../index.jsp">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 623px; width: 838px">
				<div class="welcome">
					<h2 class="title">Registrazione alla piattaforma</h2>
					
					<div class="entry"><p>Compila i seguenti campi per 
						registrarti alla piattaforma. <br />
						Se invece sei già registrato, clicca <a href="../index.jsp">qui</a>, 
						altrimenti torna alla <a href="../index.jsp">home</a>.</p>
					</div>
				</div>
				<div id="layer6" style="position: absolute; width: 476px; height: 356px; z-index: 1; left: 75px; top: 330px" class="headerTextForm">
							<form method="post" action="RegistrationServlet" style="border-style: ridge; width: 460px; height: 339px; position: absolute; left: 1px; top: 4px;">
								<input class="absolute" id="prosegui" name="submitAndGoOn" disabled="disabled" style="height: 21px; top: 312px; left: 193px;" type="submit" value="Prosegui" />
								<label id="LabelName" class="absolute" style="left: 10px; top: 9px; width: 46px">
								Nome</label>
								<label id="LabelSurname" class="absolute" style="left: 8px; top: 38px; width: 68px; right: 384px;">
								Cognome</label>
								<label id="LabelBirthdate" class="absolute" style="left: 16px; top: 68px; width: 85px; height: 19px;">
								Data di nascita</label>
								<label id="LabelGender" class="absolute" style="left: 10px; top: 98px; width: 46px">
								Sesso</label>
								<label id="LabelEmail" class="absolute" style="left: 9px; top: 128px; width: 47px">
								Email</label>
								<label id="LabelConfirmEmail" class="absolute" style="left: 17px; top: 159px; width: 85px">
								Conferma Email</label>
								<label id="LabelPassword" class="absolute" style="left: 15px; top: 195px; width: 58px">
								Password</label>
								<label id="LabelConfirmPassword" class="absolute" style="left: 18px; top: 229px; width: 109px">
								Conferma Password</label>

								<label id="LabelAccept" class="absolute" style="left: 46px; top: 286px; width: 177px">
								Accetto i termini del contratto</label><br />
								<input class="absolute" name="TextSurname" style="border-style: outset; left: 212px; top: 35px; right: 128px;" type="text" >
								<input class="absolute" name="TextConfirmPassword" style="border-style: outset; left: 212px; top: 225px; right: 116px;" type="password" >
								<input class="absolute" name="TextPassword" style="border-style: outset; left: 212px; top: 191px; right: 116px;" type="password" >
								<input class="absolute" name="TextConfirmEmail" style="border-style: outset; left: 212px; top: 156px; right: 115px;" type="text" >
								<input class="absolute" name="TextEmail" style="border-style: outset; left: 212px; top: 124px; right: 115px;" type="text" >
								<input class="absolute" name="TextName" style="border-style: outset; left: 212px; top: 7px; right: 128px;" type="text" ><br />
								<select name="Month" class="absolute" style="left: 266px; top: 65px; width: 45px;">
								<%
								out.println("<option></option>");
								for (int m=1; m <= 12; m++){
									out.println("<option>"+m+"</option>");
								}
								%>
								</select><select name="Year" class="absolute" style="left: 318px; top: 65px; width: 58px; right: 84px;">
								<%
								Calendar c = Calendar.getInstance();
								int thisYear = c.get(Calendar.YEAR);
								out.println("<option></option>");
								for (int y=1920; y <= thisYear; y++){
									out.println("<option>"+y+"</option>");
								}
								%>
								
								</select><select name="Gender" class="absolute" style="left: 212px; top: 98px; width: 45px;">
								<option>M</option>
								<option>F</option>
								</select>
								<select name="Day" class="absolute" style="left: 212px; top: 65px; width: 45px;">
								<%
								out.println("<option></option>");
								for (int d=1; d <= 31; d++){
									out.println("<option>"+d+"</option>");
								}
								%>
								</select>
								<input class="absolute" id="prova" name="CheckboxConfirm" onclick="javascript:goingOn();" style="left: 15px; top: 288px; width: 30px; right: 415px" type="checkbox">
								</form>
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
