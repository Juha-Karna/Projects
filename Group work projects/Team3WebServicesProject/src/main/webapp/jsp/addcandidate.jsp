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
	<form action="../addcandidate" method="post" accept-charset="UTF-8">
	ID: <input type="text" name="ehdokas_id" value=""><br>
	Sukunimi: <input type="text" name="sukunimi" value=""><br>
	Etunimi: <input type="text" name="etunimi" value=""><br>
		Puolue: <input type="text" name="puolue" value=""><br>
			Kotipaikkakunta: <input type="text" name="kotipaikkakunta" value=""><br>
				Ikä: <input type="text" name="ika" value=""><br>
					Miksi eduskuntaan: <input type="text" name="miksi_eduskuntaan" value=""><br>
						Mitä asioita haluat edistää: <input type="text" name="mita_asioita_haluat_edistaa" value=""><br>
							Ammatti: <input type="text" name="ammatti" value=""><br>
	<input type="submit" name="ok" value="add">
	</form>
</body>
</html>