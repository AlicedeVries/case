<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<meta http-equiv="refresh" content="10 url=/BJ/Stand">
<title>Stand</title>
</head>
<body>
<h1 style="color:#f67f00">Welcome to Blackjack Online</h1>
<c:import url="/BlackJack/Display.jsp"></c:import>
<h2>${msg}</h2>  
<c:if test="${game.hasFinished!=false}">
	<table>
	<tr>
		<td>
		<form method="post" action="/BJ/Wait">
			<input type="submit" value="Start new game" style="width: 150px; color: #000000; height: 40px; font-size: 14px; font-weight: normal; background-color: #f67f00">
		</form>
		</td>
		<td>
		<form method="post" action="/BJ/Start">
			<input type="submit" name="logout" value="Logout" style="width: 75px; color: #000000; height: 40px; font-size: 14px; font-weight: normal">
		</form>
		</td>
	</tr>
	</table>
</c:if>

<c:if test="${game.hasFinished==false}">
	<h2>Wait for other players to finish</h2>
</c:if>

</body>
</html>