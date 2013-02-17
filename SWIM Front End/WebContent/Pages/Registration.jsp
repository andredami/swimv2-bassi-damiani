<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.*, java.lang.System" %>
<%@ page import="it.polimi.ingsw2.swim.pages.RegistrationServlet.Attribute"%>
<%@ page import="it.polimi.ingsw2.swim.pages.RegistrationServlet" %>
<%@ page import="it.polimi.ingsw2.swim.session.remote.RegistrationRemote" %>
<%@ page import="it.polimi.ingsw2.swim.entities.TempUser" %>
<%@ page import="it.polimi.ingsw2.swim.entities.User" %>
<%@ page import="it.polimi.ingsw2.swim.entities.User.Gender"%>
<%	String CONTEXT_PATH = request.getContextPath(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
if (session.getAttribute("USER") != null){
	String url = response.encodeURL("/Pages/home.jsp");
	response.sendRedirect(CONTEXT_PATH + url);
	return;
}
%>
<meta http-equiv="content-type" content="text/html; charset=utf-8;" />
<script type="text/javascript" src="<%= CONTEXT_PATH + "/jquery/jquery-1.9.0.js" %>"></script>
<script type="text/javascript" src="<%= CONTEXT_PATH + "/Pages/functions.js" %>"></script>
<script>
$(document).load(function () {
	$("#prosegui").attr("disabled","disabled");
	$("#termsAndCondition").removeAttr("checked");
});
</script>
<title>Swim</title>
<link href= "<%= CONTEXT_PATH + "/css/style.css" %>" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
.auto-style1 {
	margin-top: 0;
}
</style>

</head>



<body onload="javascript:setCheckToNone();" class="absolute" style="width: 1250px; height: 684px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
					
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
					<h2 class="title">Registrazione alla piattaforma</h2>
					
					<div class="entry"><p>
					<%
						boolean errorShown = false;
						try {
							if (request.getAttribute(Attribute.INVALID_DATA.toString()).equals(1)){
								request.setAttribute(Attribute.INVALID_DATA.toString(), null);
								errorShown = true;
								out.println("<span style=\"font-weight: bolder; color: red;\">Attenzione! I dati non sono stati inseriti correttamente. Ritenta ancora.</span>");	
							}
						}
						catch (NullPointerException e){
						}
						try {
							if (request.getAttribute(Attribute.ALREADY_EXISTS.toString()).equals(1)){
								request.setAttribute(Attribute.ALREADY_EXISTS.toString(), null);
								errorShown = true;
								out.println("<span style=\"font-weight: bolder; color: red;\">Attenzione! I dati inseriti corrispondono ad un utente già registrato! Riprova con altri valori.</span>");	
							}
						}
						catch (NullPointerException e){
						}
						try {
							if (request.getAttribute(Attribute.NOT_MATCHING.toString()).equals(1)){
								request.setAttribute(Attribute.NOT_MATCHING.toString(), null);
								errorShown = true;
								out.println("<span style=\"font-weight: bolder; color: red;\">Attenzione! La combinazione di email e password risulta errata. Ritenta ancora.</span>");	
							}
						}
						catch (NullPointerException e){
						}
						try {
							if (request.getAttribute(Attribute.TIMEOUT.toString()).equals(1)){
								request.setAttribute(Attribute.TIMEOUT.toString(), null);
								errorShown = true;
								out.println("<span style=\"font-weight: bolder; color: red;\">Siamo spiacenti ma il tempo per completare la registrazione è scaduto. Riprova!</span>");	
							}
						}
						catch (NullPointerException e){
						}
						if(!errorShown){
							out.println("Compila i seguenti campi per registrarti alla piattaforma.<br />Oppure altrimenti torna alla <a href=\"" + response.encodeURL(CONTEXT_PATH + "/index.jsp") + "\">home</a>.");
						}
						%>
						</p>
					</div>
				</div>
				<%
					TempUser tempUser = null;
					RegistrationRemote registrationAgent = (RegistrationRemote) request.getSession().getAttribute(RegistrationServlet.Attribute.REGISTRATION_AGENT.toString());
					if(registrationAgent != null){
							tempUser = registrationAgent.getTempUser();
					}
				%>
				<div id="layer6" style="position: absolute; width: 476px; height: 356px; z-index: 1; left: 75px; top: 330px" class="headerTextForm">
							<form method="post" action="<%= response.encodeURL(CONTEXT_PATH + "/RegistrationServlet")%>" style="border-style: ridge; width: 460px; height: 339px; position: absolute; left: 1px; top: 4px;">
								<label id="LabelName" class="absolute" style="left: 10px; top: 9px; width: 46px">
								Nome</label>
								<input class="absolute" value="<% out.print((tempUser != null) ? tempUser.getName().getFirstname() : ""); %>" name="TextName" style="border-style: outset; left: 212px; top: 7px; right: 128px;" type="text" ><br />
								
								<label id="LabelSurname" class="absolute" style="left: 8px; top: 38px; width: 68px; right: 384px;">
								Cognome</label>
								<input class="absolute" value="<% out.print((tempUser != null) ? tempUser.getName().getSurname() : ""); %>" name="TextSurname" style="border-style: outset; left: 212px; top: 35px; right: 128px;" type="text" >
								
								<label id="LabelBirthdate" class="absolute" style="left: 16px; top: 68px; width: 85px; height: 19px;">
								Data di nascita</label>
								<select name="Day" class="absolute" style="left: 212px; top: 65px; width: 45px;">
								<%
								Calendar c = Calendar.getInstance();
								int selectedDay = (tempUser != null ? tempUser.getBirthdate() : c).get(Calendar.DAY_OF_MONTH);
								out.println("<option></option>");
								for (int d=1; d <= 31; d++){
									if(selectedDay == d){
										out.println("<option selected>"+d+"</option>");
									} else {
										out.println("<option>"+d+"</option>");
									}
								}
								%>
								</select>
								<select name="Month" class="absolute" style="left: 266px; top: 65px; width: 45px;">
								<%
								int selectedMonth = (tempUser != null ? tempUser.getBirthdate() : c).get(Calendar.MONTH);
								out.println("<option></option>");
								for (int m=0; m < 12; m++){
									if(selectedMonth == m){
										out.println("<option value=\"" + m + "\"selected>"+ (m + 1) +"</option>");
									} else {
										out.println("<option value=\"" + m + "\">"+ (m + 1) +"</option>");
									}
								}
								%>
								</select>
								<select name="Year" class="absolute" style="left: 318px; top: 65px; width: 58px; right: 84px;">
								<%
								int thisYear = c.get(Calendar.YEAR);
								int selectedYear = tempUser != null ? tempUser.getBirthdate().get(Calendar.YEAR) : thisYear;
								out.println("<option></option>");
								for (int y=1920; y <= thisYear; y++){
									if(selectedYear == y){
										out.println("<option selected>"+y+"</option>");
									} else {
										out.println("<option>"+y+"</option>");
									}
								}
								%>
								</select>
								
								
								<label id="LabelGender" class="absolute" style="left: 10px; top: 98px; width: 46px">
								Sesso</label>
								<select name="Gender" class="absolute" style="left: 212px; top: 98px; width: 45px;">
								<option <% out.print(((tempUser != null) && tempUser.getGender().equals(Gender.M)) ? "selected" : ""); %>>M</option>
								<option <% out.print(((tempUser != null) && tempUser.getGender().equals(Gender.F)) ? "selected" : ""); %>>F</option>
								</select>
								
								<label id="LabelEmail" class="absolute" style="left: 9px; top: 128px; width: 47px">
								Email</label>
								<input class="absolute" value="<% out.print((tempUser != null) ? tempUser.getEmail() : ""); %>" name="TextEmail" style="border-style: outset; left: 212px; top: 124px; right: 115px;" type="text" >
								
								<label id="LabelConfirmEmail" class="absolute" style="left: 17px; top: 159px; width: 85px">
								Conferma Email</label>
								<input class="absolute" name="TextConfirmEmail" style="border-style: outset; left: 212px; top: 156px; right: 115px;" type="text" >
								
								<label id="LabelPassword" class="absolute" style="left: 15px; top: 195px; width: 58px">
								Password</label>
								<input class="absolute" name="TextPassword" style="border-style: outset; left: 212px; top: 191px; right: 116px;" type="password" >
								
								<label id="LabelConfirmPassword" class="absolute" style="left: 18px; top: 229px; width: 109px">
								Conferma Password</label>
								<input class="absolute" name="TextConfirmPassword" style="border-style: outset; left: 212px; top: 225px; right: 116px;" type="password" >

								<label id="LabelAccept" class="absolute" style="left: 46px; top: 286px; width: 177px">
								Accetto i termini del contratto</label>
								<input class="absolute" id="termsAndCondition" name="CheckboxConfirm" onclick="javascript:goingOn();" style="left: 15px; top: 288px; width: 30px; right: 415px" type="checkbox">

								<br />

								<input class="absolute" id="prosegui" name="submitAndGoOn" disabled="disabled" style="height: 21px; top: 312px; left: 193px;" type="submit" value="Prosegui" />
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
