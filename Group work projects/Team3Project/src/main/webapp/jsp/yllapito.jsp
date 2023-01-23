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

    <table>
    
          <tr>
        <td><a href='/logout'>Log Out</a></td>
      </tr>

     <tr>
        <td><a href='./index.jsp'>Home</a></td>
      </tr>
      
      <tr>
        <td><a href='/questionlist'>Question Options</a></td>
      </tr>
      
       <tr>
        <td><a href='/candidatelist'>Candidate Options</a></td>
      </tr>
      
      <tr>
      <td><a href='./index.jsp'>Another Home Button</a></td>
      </tr>
    </table>
  </body>
  
</body>
</html>