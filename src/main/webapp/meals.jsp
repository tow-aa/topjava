<%--
  Created by IntelliJ IDEA.
  User: towner
  Date: 07.06.2019
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h1>Meals</h1>
<table border="1">
    <c:forEach var="meal" items="${requestScope.meals}">
        <c:set var="excess" value="${meal.excess}"/>
        <c:choose>
            <c:when test="${meal.excess==true}">
                <tr style="background-color: red;">
            </c:when>
            <c:otherwise>
                <tr style="background-color: green;">
            </c:otherwise>
        </c:choose>
        <javatime:format value="${meal.dateTime}" style="MS" var="time" />
        <td>${time}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
