<%@page import="org.jboss.aspects.security.Unchecked"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="it.polimi.ingsw2.swim.entities.Ability"%>
<%@ page import="it.polimi.ingsw2.swim.pages.AbilitySelectionMode"%>
<%@ page import="it.polimi.ingsw2.swim.pages.RegistrationServlet"%>
<%@ page
	import="it.polimi.ingsw2.swim.pages.SelectAbilityRegistrationServlet"%>
<%@ page
	import="it.polimi.ingsw2.swim.pages.CompleteRegistrationServlet"%>
<%@ page
	import="it.polimi.ingsw2.swim.session.remote.RegistrationRemote"%>
<%
	String CONTEXT_PATH = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	AbilitySelectionMode mode = AbilitySelectionMode.FILTER;
	RegistrationRemote registrationAgent = (RegistrationRemote) session
			.getAttribute(RegistrationServlet.Attribute.REGISTRATION_AGENT
					.toString());

	//TODO: PROFILE
	if (request.getAttribute("profileediting") != null) {
		if (session.getAttribute("USER") == null) {
			String url = response.encodeURL("/Pages/home.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
		mode = AbilitySelectionMode.PROFILE;
	} else if (registrationAgent != null) {
		mode = AbilitySelectionMode.REGISTRATION;
	}
%>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=CONTEXT_PATH + "/Popup/popup.js"%>"></script>
<script type="text/javascript"
	src="<%=CONTEXT_PATH + "/jquery/jquery-1.9.0.js"%>"></script>


<title>Swim</title>

<link href="<%=CONTEXT_PATH + "/css/style.css"%>" rel="stylesheet"
	type="text/css" media="screen" />

<style type="text/css">
.auto-style1 {
	margin-top: 0;
}
</style>

</head>
<body class="absolute"
	style="width: 1250px; height: 684px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">

	<div id="wrapper"
		style="width: 956px; left: 4px; top: -3px; height: 763px;"
		class="absolute">
		<div id="wrapper2" style="height: 135px">
			<div id="header" style="width: 961px">
				<div id="logo" style="width: 957px; height: 137px">

					<h1>
						<a href="<%=response.encodeURL(CONTEXT_PATH + "/index.jsp") %>">SWIM</a>
					</h1>

				</div>
			</div>
			<!-- end #header -->
			<div id="page">
				<div id="content" class="auto-style1"
					style="height: 623px; width: 838px">
					<div class="welcome">
						<h2 class="title">Selezionare le abilità</h2>

						<div class="entry">
							<p>
								<%
									switch (mode) {
									case REGISTRATION:
										out.println("La prima fase della registrazione è andata a buon fine!<br>");
										out.println("Seleziona la tua prima abilità!");
										break;
									case PROFILE:
										out.println("Seleziona l'abilità che vuoi inserire nel tuo profilo<br>");
										out.println("Oppure <a href='" + response.encodeURL(CONTEXT_PATH + "/Profile.html")
												+ "'>torna al tuo profilo</a>");
										break;
									case FILTER:
										out.println("Seleziona l'abilità per cui desideri cercare aiuto<br>");
										out.println("Oppure <a href='" + response.encodeURL(CONTEXT_PATH + "/Profile.html")
												+ "'>torna al tuo profilo</a>");
										break;
									}
								%>
							</p>
						</div>
					</div>

					<div id="layer6"
						style="position: absolute; width: 343px; height: 147px; z-index: 1; left: 77px; top: 314px"
						class="headerTextForm">
						Ricerca l'abilità<br />
						<form method="post" action="<%=response.encodeURL(CONTEXT_PATH + "/SelectAbilityRegistrationServlet")%>"
							style="height: 148px">
							<input class="absolute" name="SubmitAbilityButton"
								style="left: 182px; top: 81px; height: 30px; width: 129px;"
								type="submit" value="Cerca" />
							<input class="absolute" name="TextAbility"
								style="border-style: outset; left: 25px; top: 49px; width: 205px;"
								type="text" />
						</form>
						<label id="LabelError" class="absolute"
								style="left: 20px; top: 115px; width: 295px"> </label>
							<div class="notification"
								style="border-style: groove; left: 431px; top: -3px; height: 250px; width: 359px">
								<div class="notification-inner"
									style="width: 345px; height: 241px; margin-left: 5px; margin-top: 5px">
									Abilità trovate (clicca sul nome dell'abilità per selezionarla)
									<br /> &nbsp;
									<%
										String destination = "";
										switch (mode) {
										case REGISTRATION:
											destination = "../CompleteRegistrationServlet";
											break;
										case PROFILE:
											destination = "../InsertAbilityServlet";
											break;
										case FILTER:
											destination = "../HelpRequestUser";
											break;
										}

										try {
											List<Ability> a = (List<Ability>) request
													.getAttribute(SelectAbilityRegistrationServlet.Attribute.LIST
															.toString());
											if (a.isEmpty()) {
									%>
									<p>
										Non è stata trovata nessuna abilità che corrisponde alla
										ricerca "<%=request
							.getAttribute(SelectAbilityRegistrationServlet.Attribute.SEARCH_KEY
									.toString())%>"
										<br />

										<%
											if (mode == AbilitySelectionMode.PROFILE) {
										%>
										Se vuoi puoi <a
											href="<%=response.encodeURL(CONTEXT_PATH + "MANCA")%>"
											target="_blank">richiederne l'aggiunta</a>!
										<%
											}
										%>
									</p>
									<%
										} else {
									%>
									<ul>
										<%
											Iterator<Ability> i = a.iterator();
													while (i.hasNext()) {
														Ability el = i.next();
														if (mode != AbilitySelectionMode.PROFILE
																&& !el.isStub()) {
										%>
										<li><a
											href=" <%=response.encodeURL(CONTEXT_PATH + destination
									+ "?ChosenAbility=" + el.getName())%>">
												<%
													out.print(el.getName());
												%>
										</a></li>
										<li id='description'><textarea readonly='readonly'
												name='TextArea' rows='2' style='width: 285px'>
																<%
																	out.print(el.getDescription());
																%>
															</textarea></li>
										<%
											} else {
										%>
										<li>[Abilità proposta] <%
											out.print(el.getName());
										%><br />
											<a
											href="<%=response
									.encodeURL(CONTEXT_PATH + "/SubscribeAbility?ChosenAbility="
											+ el.getName())%>">
												Sottoscrivi </a> questa abilità per averla nonappena
											disponibile!
										</li>
										<li id='description'><textarea readonly='readonly'
												name='TextArea' rows='2' style='width: 285px'>
																<%
																	out.print(el.getDescription());
																%>
															</textarea></li>
										<%
											}
													}
										%>
									</ul>
									<%
										}
										} catch (NullPointerException e) {
										}
									%>
								</div>
							</div>

					</div>


					<div class="absolute" style="width: 86px; left: 92px; top: 753px">
						<a
							href="javascript:open_win('<%=response.encodeURL(CONTEXT_PATH + "/Popup/AbusePopup.js")%>', 'AbusePopup');">Segnala
							Abuso</a>
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
