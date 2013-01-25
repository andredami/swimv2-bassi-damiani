<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../Popup/popup.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="../jquery/jquery-1.9.0.js"></script>
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>
<title>Swim</title>

<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
.auto-style1 {
	margin-top: 0;
}
</style>

<%
	String email = (String)request.getSession().getAttribute("TextEmail");
		if (email == null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>

</head>
<body class="absolute" style="width: 1250px; height: 684px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
					
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
					<h2 class="title">Selezionare le abilità</h2>
					
					<div class="entry">
					<p>
						<%
							try{
							if (request.getSession().getAttribute("Registration").equals(1)){
								out.println ("La prima fase della registrazione è andata a buon fine!");
								out.println ("Ora serve che tu inserisca almeno un abilità, oppure <a href='Registration.jsp'>torna indietro</a>");
							}
							}
							catch (NullPointerException e){
								out.println ("Seleziona le abilità che vuoi inserire nel tuo profilo");
								out.println ("Quando hai terminato, <a href='Profile.html'>torna al tuo profilo</a>");	
								}
						%>
					</p>
					</div>
				</div>
				
							
				<div id="layer6" style="position: absolute; width: 343px; height: 147px; z-index: 1; left: 77px; top: 314px" class="headerTextForm">
							<form method="post" action="../ValidationServlet" style="border-style: ridge; width: 328px; height: 143px; position: absolute; left: 1px; top: 4px; right: 6px;">
								Scrivi direttamente l'abilità che vuoi inserire, 
								oppure<br />
								fai richiesta di una nuova abilità.<br />
								<input class="absolute" id="next" name="NextButton" style="left: 520px; top: 283px; height: 27px; width: 262px;" type="submit" value="Prosegui(attivo solo se presente una abilità)" />
								&nbsp;
								<label id="LabelError" class="absolute" style="left: 20px; top: 115px; width: 295px">
								<%
								// verifying if the request is empty or if there are no results
								try{
								if (request.getSession().getAttribute("EmptyRequest").equals(1)){
									out.println ("Devi inserire un'abilità da ricercare.");
								}
								else {
										if (request.getSession().getAttribute("NotFoundAbility").equals(1)){
											out.println ("L'abilità richiesta non esiste. Se lo desideri puoi farne richiesta.");
											}
								}
								}catch (NullPointerException e){
									e.printStackTrace();
								}
								%>
								</label>
								<div class="notification" style="border-style: groove; left: 431px; top: -3px; height: 250px; width: 359px">
									<div class="notification-inner" style="width: 345px; height: 241px; margin-left: 5px; margin-top: 5px">
										Abilità trovate (clicca sul nome per aggiungere alle scelte)
										<br />
										&nbsp;
										<ul> 
										<%
											try{
											List<Ability> a = new ArrayList<Ability>();
											a = (List<Ability>)request.getSession().getAttribute("abilityList");
											Iterator<Ability> i = a.iterator();
											while (i.hasNext()){
												Ability el = i.next();
												%>
												<li><a href="<%=response.encodeURL("InsertAbilityServlet?ChosenAbility="+el.getName())%>"><% out.print(el.getName()); %></a></li>
												<li id='description'>
												<textarea readonly='readonly' name='TextArea' rows='2' style='width: 285px'><% out.print(el.getDescription()); %></textarea>
												</li>
											<%
											}
											}catch(NullPointerException e){			
												out.println("Non ci sono abilità registrate nel database.");
											}
										%>							
										</ul>
									</div>
								</div>
							</form>
							<form method="post" action="../SelectAbilityRegistrationServlet" style="height: 148px">
								<input class="absolute" name="SubmitAbilityButton" style="left: 182px; top: 81px; height: 30px; width: 129px;" type="submit" value="Inserisci" />
								<input class="absolute" name="TextAbility" style="border-style: outset; left: 25px; top: 49px; width: 205px;" type="text" />
							</form>
								<input class="absolute" name="RequestAbilityButton" type="button" value="Richiedi Nuova Abilità" onclick="open_win('../Popup/RequestAbilityPopup.html','RequestAbilityPopup');" style="left: 26px; top: 81px; width: 143px; height:31px;"/>
								</div>
								

				<div class="absolute" style="width: 86px; left: 92px; top: 753px">
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
