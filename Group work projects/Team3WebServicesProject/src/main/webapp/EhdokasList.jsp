<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ehdokkaiden lisäys/poisto/editointi</title>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body>

    <center>
    <div class="header"> 
        <h1>Ehdokas Management</h1>
        
        <h2>
        <% 
            if ((request.getSession(false).getAttribute("AdminUser") == null)) {
                %>
                   
                <%
                    } 
                    else {
                %>
                <a href="/new">Lisää uusi ehdokas</a>
                <%
                    }
                %>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">Lista kaikista Ehdokkaista</a>
            
             
        </h2>
        </div>
    </center>
    <div class="row">	
    <div class="container">
    <div align="center">
        <table border="1" cellpadding="9" style="color:white; font-size: 1vw;">
            <caption><h2>Lista ehdokkaista</h2></caption>
            <tr>
                <th>ID</th>
                <th>Puolue</th>
                <th>Nimi</th>
                <th>Ikä</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="ehdokas" items="${listEhdokas}">
                <tr>
                    <td><c:out value="${ehdokas.id}" /></td>
                    <td><c:out value="${ehdokas.puolue}" /></td>
                    <td><c:out value="${ehdokas.nimi}" /></td>
                    <td><c:out value="${ehdokas.ika}" /></td>
                    <td>
                         <% 
                        if ((request.getSession(false).getAttribute("AdminUser") == null)) {
                            %>
                               
                            <%
                                } 
                                else {
                            %>
                            <a style="color:#6688CC;" href="/edit?id=<c:out value='${ehdokas.id}' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a style="color:#d02424;" href="/delete?id=<c:out value='${ehdokas.id}' />">Delete</a>
                            <%
                                }
                            %>                    
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>  
        </div>
        </div>
        
</body>
</html>