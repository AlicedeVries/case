<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<link type="text/css" rel="stylesheet" href="<c:url value="/opmaak.css"/>" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game</title>
</head>
<body>
<h2>Blackjack Online</h2>
<div class=page>
	<c:import url="/BlackJack/Display.jsp"></c:import>
	<table>
	<tr>
		<td>
		<form method="post" action="/Kaartspellen/Blackjack/Hit">
			<input type="submit" value="Hit" style="width: 90px; color: #000000; height: 40px; font-weight: normal; font-size: 14px; background-color: #f67f00">
		</form>
		</td>
		<td>
		<form method="post" action="/Kaartspellen/Blackjack/Stand">
			<input type="submit" value="Stand" style="width: 90px; color: #000000; height: 40px; font-size: 14px; font-weight: normal; background-color: #f67f00">
		</form>
		</td>
	</tr>
	</table>
</div>
</body>
</html>