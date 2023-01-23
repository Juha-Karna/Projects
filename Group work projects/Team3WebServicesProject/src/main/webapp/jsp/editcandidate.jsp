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


	<form action="./editcandidate" method="post" accept-charset="UTF-8">
	ID: <input type="text" name="ehdokas_id" value="${sessionScope.candidate.ehdokas_id}"><br>
	Sukunimi: <input type="text" name="sukunimi" value="${sessionScope.candidate.sukunimi}"><br>
	Etunimi: <input type="text" name="etunimi" value="${sessionScope.candidate.etunimi}"><br>
		Puolue: <input type="text" name="puolue" value="${sessionScope.candidate.puolue}"><br>
			Kotipaikkakunta: <input type="text" name="kotipaikkakunta" value="${sessionScope.candidate.kotipaikkakunta}"><br>
				Ik‰: <input type="text" name="ika" value="${sessionScope.candidate.ika}"><br>
					Miksi eduskuntaan: <input type="text" name="miksi_eduskuntaan" value="${sessionScope.candidate.miksi_eduskuntaan}"><br>
						Mit‰ asioita haluat edist‰‰: <input type="text" name="mita_asioita_haluat_edistaa" value="${sessionScope.candidate.mita_asioita_haluat_edistaa}"><br>
							Ammatti: <input type="text" name="ammatti" value="${sessionScope.candidate.ammatti}"><br>
	<input type="submit" name="ok" value="edit">
	</form>
</body>
</html>