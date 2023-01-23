<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="app.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="author" content="Lauri Jokinen">
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("AdminUser")== null) )
{
%>
<jsp:forward page="/jsp/login.jsp"></jsp:forward>
<%} %>


</head>
<body>

	<form action="../addquestion" method="post" accept-charset="UTF-8">
	ID: <input type="text" name="kysymys_id" value=""><br>
	Kysymys: <input type="text" name="kysymys" value=""><br>
	<input type="submit" name="ok" value="add">
	</form>
	
</body>
</html>