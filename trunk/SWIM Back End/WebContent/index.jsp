<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta content="it" http-equiv="Content-Language">
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Admin pages</title>
</head>

<body>

<p>Benvenuto nella sezione degli amministratori!</p>
<form action="../Pages/LoginAdminServlet" method="post">
	Username: <input name="Username" type="text"><br><br>Password:
	<input name="Password" type="text"><br><br>
	<input name="Submit1" type="submit" value="Login"></form>

</body>

</html>
