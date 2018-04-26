<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
<title>Crosses</title>
<style type="text/css">
TABLE {
border-collapse: collapse;
}
TD, TH {
padding: 5px;
}
</style>
</head>
<body>
Enter number:<br><br>
<form action="crosses" method="post">
<input type="text" name="search" size="25">
<input type="submit" name="button" value="Search">
</form>
Result for: ${search}<br><br>
<table border="1">
<tr><td>Number</td><td>Brand</td><td>Description</td></tr>
<c:forEach var="cross" items="${result}">
<tr>
<c:forEach items="${cross}" varStatus="loop">
<td>${cross[loop.index]}</td>
</c:forEach>
</tr>
</c:forEach>
</table>
<body>
</html>

