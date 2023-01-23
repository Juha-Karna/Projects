<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="app.*" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="author" content="Lauri Jokinen">
<meta charset="UTF-8">
<title>Insert title here</title>

<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("AdminUser")== null) )
{
%>
<jsp:forward page="/jsp/login.jsp"></jsp:forward>
<%} %>

</head>
<body>


	<form action="./editquestion" method="post" accept-charset="UTF-8">
	ID: <input type="text" name="kysymys_id" value="${sessionScope.questions.id}"><br>
	Kysymys: <input type="text" name="kysymys" value="${sessionScope.questions.question}"><br>
	<input type="submit" name="ok" value="edit">
	</form>
</body>
</html>