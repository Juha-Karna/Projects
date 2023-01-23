<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ehdokkaiden lisäys/poisto/editointi</title>
    <link rel="stylesheet" type="text/css" href="./style.css"/>
</head>
<body style="color: white;">
    <center>
    <div class="header">
        <h1>Ehdokas Management</h1>
        <h2>
            <a href="/new">Lisää uusi ehdokas</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">Lista kaikista Ehdokkaista</a>
             
        </h2>
        </div>
    </center>
    <div class="row">	
    <div class="container">
    <div align="center">
        <c:if test="${ehdokas != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${ehdokas == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="0" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${ehdokas != null}">
                        Edit Ehdokas
                    </c:if>
                    <c:if test="${ehdokas == null}">
                        Lisää uusi Ehdokas
                    </c:if>
                </h2>
            </caption>
                <c:if test="${ehdokas != null}">
                    <input type="hidden" name="id" value="<c:out value='${ehdokas.id}' />" />
                </c:if>           
            <tr>
                <th>Puolue: </th>
                <td>
                    <input type="text" name="puolue" size="45"
                            value="<c:out value='${ehdokas.puolue}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Nimi: </th>
                <td>
                    <input type="text" name="nimi" size="45"
                            value="<c:out value='${ehdokas.nimi}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Ikä: </th>
                <td>
                    <input type="text" name="ika" size="5"
                            value="<c:out value='${ehdokas.ika}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input style="width:30%; background-color:#0049FF; color:white;" type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>  
    </div>
    </div> 
</body>
</html>