<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<link type="text/css" rel="stylesheet" href="<c:url value="/opmaak.css"/>" />
<meta http-equiv="refresh" content="10 url=/Kaartspellen/Blackjack/Stand">
<title>Stand</title>
</head>
<body>
<h2>Blackjack Online</h2>
<div class=page>
<c:import url="/BlackJack/Display.jsp"></c:import>
<h3>${msg}</h3>  
<c:if test="${game.Finished!=false}">
	<table>
	<tr>
		<td>
		<form method="post" action="/Kaartspellen/Blackjack/Wait">
			<input type="submit" value="Start new game" style="width: 150px; color: #000000; height: 40px; font-size: 14px; font-weight: normal; background-color: #f67f00">
		</form>
		</td>
		<td>
		<form method="post" action="/Kaartspellen/">
			<input type="submit" value="Play other kaartspel" style="width: 150px; color: #000000; height: 40px; font-size: 14px; font-weight: normal">
		</form>
		</td>
	</tr>
	</table>
</c:if>

<c:if test="${game.Finished==false}">
	<h2>Wait for other players to finish</h2>
</c:if>
</div>
</body>
</html>