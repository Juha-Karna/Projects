<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add new Admin</title>

</head>

<body>
<h2>Fill in - this form adds admin</h2>
<form action="../rest/adminservice/addadmin" method='post'>
Username: <input id='username' type='text' name='username' value='' placeholder='New Username'><br>
Password: <input id='password' type='password' name='password' value='' placeholder='New Password'><br>
<input type='submit' name='ok' value='Send'><br>
</form>
</body>
</html>