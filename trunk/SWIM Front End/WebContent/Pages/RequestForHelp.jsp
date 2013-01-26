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
<%@ page import="java.util.*" %>
<%@ page import="it.polimi.ingsw2.swim.entities.*" %>


<%
//check if exists a valid session
	Long user = (Long)session.getAttribute("Id");
		if (user == null){
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
					<h2 class="title">Ricerca di aiuto</h2>
					
					<div class="entry"><p>Ricerca l'utente che può darti una 
						mano ricercando una sua abilità specifica, oppure 
						ricercandolo per nome. <br />
						Oppure <a href="HomePage.jsp">
						torna alla home</a>.<br />
								</p>
					</div>
				</div>
				<div class="notification" style="border-style: groove; left: 528px; top: 320px; height: 341px; width: 353px">
						<div class="notification-inner" style="font-weight:bold; width:337px; height: 335px; margin-left: 5px; margin-top: 5px">
							<h4>Risultati della ricerca</h4>
							<ul id="noMargin">
							
							<%
							try{
								List<User> a = (List<User>) request.getSession().getAttribute("listUser");
								Iterator<User> i = a.iterator();
								if (i.hasNext()){
									while (i.hasNext()){
										User el = i.next();
							%>
							<li>
							<div id="smallWindow" style="width: 309px">	
								<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
								<div id="requestLabel" style="left: 56px; top: -50px; bottom: 50px; width: 249px"><a href=""><%out.print(el.getName().getFirstname()+""+el.getName().getSurname()); %></a> 
									- <%out.print(el.getGender()); %></div>
								<form method="post" action=<% response.encodeURL("../Pages/AskForHelp?id="+el.getId());%>>
									<input id="selectButton" name="selectButton" type="submit" value="Seleziona" style="position:relative; left: 240px; top: -42px; width: 66px; height: 21px" />
								</form>
								</div>
							</li>
							<% 
									}
								}
							} catch (NullPointerException e){
								
								out.print("Non ci sono utenti che possiedono quelle abilità");
							}
							%>
							
							
							
								<li>
									<div id="smallWindow" style="width: 309px">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel" style="left: 56px; top: -50px; bottom: 50px; width: 249px"><a href="RequestFriendProfile.html">Utente 1</a> 
											- Sesso</div>
										<form method="post" action="AskForHelp.jsp">
											<input id="selectButton" name="selectButton" type="submit" value="Seleziona" style="position:relative; left: 240px; top: -42px; width: 66px; height: 21px" />
										</form>
									</div>
								</li>
								<li>
									<div id="smallWindow" style="width: 308px">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel" style="left: 56px; top: -50px; bottom: 50px; width: 249px"><a href="RequestFriendProfile.html">Utente 2</a> 
											- Sesso</div>
										<form method="post" action="AskForHelp.jsp">
											<input id="selectButton" name="selectButton" type="submit" value="Seleziona" style="position:relative; left: 240px; top: -42px; width: 66px; height: 21px" />
										</form>
									</div>
								</li>

								<li>
									<div id="smallWindow" style="width: 309px">	
										<img alt="" id="smallImage" height="50" src="../images/imgProfileSmall.gif" width="50" />
										<div id="requestLabel" style="left: 58px; top: -50px; bottom: 50px; width: 250px"><a href="RequestFriendProfile.html">Utente 3</a> 
											- Sesso</div>
										<form method="post" action="AskForHelp.html">
											<input id="selectButton" name="selectButton" type="submit" value="Seleziona" style="position:relative; left: 240px; top: -42px; width: 66px; height: 21px" />
										</form>
									</div>
								</li>
							</ul>

						</div>
					</div>
							
				<div id="layer6" style="position: absolute; width: 343px; height: 147px; z-index: 1; left: 77px; top: 314px" class="headerTextForm">
							<form method="post" action="<%= response.encodeURL("../RequestHelpServlet?id="+user)%>" style="border-style: ridge; width: 328px; height: 143px; position: absolute; left: 1px; top: 4px; right: 6px;">
								Ricerca l'abilità che possiede l'utente <br />
								&nbsp;&nbsp;
								<label id="LabelError" class="absolute" style="left: 20px; top: 115px; width: 295px"></label>
								<input class="absolute" name="SubmitAbilityButton" style="left: 248px; top: 47px; height: 30px; width: 73px;" type="submit" value="Ricerca" />
								<input class="absolute" name="TextAbility" style="border-style: outset; left: 25px; top: 49px; width: 205px;" type="text" />
							</form>
								
				</div>
				<div id="layer6" style="position: absolute; width: 343px; height: 147px; z-index: 1; left: 77px; top: 462px" class="headerTextForm">
							<form method="post" action="RequestForHelp.html" style="border-style: ridge; width: 328px; height: 143px; position: absolute; left: 1px; top: 4px; right: 6px;">
								Ricerca l'utente direttamente per nome o 
								cognome, e se preferisci inserisci un feedback 
								minimo<br />
								&nbsp;&nbsp;
								<label id="LabelError" class="absolute" style="left: 20px; top: 115px; width: 295px">qui 
								</label>
								<input class="absolute" name="SubmitAbilityButton" style="left: 248px; top: 47px; height: 30px; width: 73px;" type="submit" value="Ricerca" />
								<input class="absolute" name="TextAbility" style="border-style: outset; left: 25px; top: 49px; width: 205px;" type="text" />
								<input name="Checkbox1" type="checkbox" style="position:relative; left: -141px; top: 44px; width: 20px; height: 22px;"/>
								<label id="Label1" style="position:absolute; left: 55px; top: 82px;">Feedback Minimo</label>
								<div style="position:absolute; left: 167px; top: 81px;">
						    		<select disabled="disabled" name="Evaluate">
											<option>-5</option>
											<option>-4</option>
											<option>-3</option>
											<option>-2</option>
											<option>-1</option>
											<option selected="selected">0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>4</option>
											<option>5</option>
										</select>
						    	</div>

							</form>	
				</div>


				<div class="absolute" style="width: 86px; left: 82px; top: 628px">
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
