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

<% 
	// check if exists a valid session
	Long admin = (Long)session.getAttribute("Id");
		if (admin == null){
			String url = response.encodeURL("/index.jsp");
			response.sendRedirect(request.getContextPath() + url);
			return;
		}
%>

</head>
<body class="absolute" style="width: 1250px; height: 622px; left: 180px; top: 0px; margin-left: 106; margin-top: 0">
			
					
<div id="wrapper" style="width: 956px; left: 4px; top: -3px; height: 627px;" class="absolute">
	<div id="wrapper2" style="height: 135px">
		<div id="header" style="width: 961px">
			<div id="logo" style="width: 959px; height: 137px">
		
				<h1><a href="">SWIM</a></h1>


			</div>
			</div>
		<!-- end #header -->
		<div id="page">
			<div id="content" class="auto-style1" style="height: 483px; width: 819px">
				<div class="welcome">
					<div id="navigation" style="height: 22px">
						<ul class="absolute" style="left: 672px; top: 192px; height: 18px">
							<li><a href="<%= response.encodeURL("../Pages/ContactUser.jsp")%>">Contatta</a></li>
							<li>
							<a href="<%= response.encodeURL("../BanUserServlet")%>">Ban Utente</a></li>
						    <li><a href="<%= response.encodeURL("../Pages/UserList.jsp")%>">Torna Indietro</a></li>
						</ul>
					</div>
					

					<h2 class="title"><% out.print(request.getSession().getAttribute("Name")); out.print(request.getSession().getAttribute("Surname")); %></h2>
					<div class="entry" style="height: 349px">
					
						<img src="<% out.print(request.getSession().getAttribute("Image")); %>" height="93" width="138" /> <br /><br />
						<ul>
							<li>Nome: <% out.print(request.getSession().getAttribute("Name")); %></li>
							<li>Cognome: <% out.print(request.getSession().getAttribute("Surname")); %></li>
							<li>Sesso: <% out.print(request.getSession().getAttribute("Gender")); %></li>
							<li>Email: <% out.print(request.getSession().getAttribute("Email")); %></li>
							<li>Data di Nascita: <% out.print(request.getSession().getAttribute("Birthdate")); %></li>
							<li>Indirizzo: <% out.print(request.getSession().getAttribute("Address")); %></li>
							<li>Numero di telefono: <% out.print(request.getSession().getAttribute("Phone Number")); %></li>
							<li>Cellulare: <% out.print(request.getSession().getAttribute("Cellphone")); %></li>
							<li>Fax: <% out.print(request.getSession().getAttribute("Fax")); %></li>
							<li>Username Skype: <% out.print(request.getSession().getAttribute("Skype")); %></li>
						</ul>
						<div>
							<hr></hr><br>
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
