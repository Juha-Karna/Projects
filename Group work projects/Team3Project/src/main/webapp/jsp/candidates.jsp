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
<a href="jsp/yllapito.jsp">Ylläpito</a> <br> <br>
<a href="jsp/addcandidate.jsp">Add Candidate</a> 



<c:forEach var="candidate" items="${sessionScope.candidates}" >
	<tr>
		                <h1><b>${candidate.ehdokas_id}:</b> ${candidate.etunimi} ${candidate.sukunimi}</h1>
                        <b>Puolue:</b><br>
                        ${candidate.puolue}<br><br>
                        <b>Kotipaikkakunta:</b><br>
                        ${candidate.kotipaikkakunta}<br><br>
                        <b>Ikä:</b><br>
                        ${candidate.ika}<br><br>
                        <b>Ammatti:</b><br>
                        ${candidate.ammatti}<br><br>
                        <b>Miksi haluat eduskuntaan?</b><br>
                        ${candidate.miksi_eduskuntaan}<br><br>
                        <b>Mitä asioita haluat edistää</b><br>
                        ${candidate.mita_asioita_haluat_edistaa}<br><br>
                        <br><br>

			<a href="/editcandidate?ehdokas_id=${candidate.ehdokas_id}">Edit</a>
			<a href="/deletecandidate?ehdokas_id=${candidate.ehdokas_id}">Delete</a>
		</td>
		
		<br>
	</tr>

</c:forEach>

</body>
</html>