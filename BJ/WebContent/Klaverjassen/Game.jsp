<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game</title>
</head>
<body>
<h1 style="color:#f67f00">Welcome to Blackjack Online</h1>
	<c:import url="/Klaverjassen/Display.jsp"></c:import>
	
	<form> 
	<table>
		<tr> 
			<td> 
				<h3> Your hand:</h3> 	
				<h4>team: ${player.team}</h4>
			</td>
			<c:forEach items="${player.hand}" var="card">
			<td>
				<a href="hierheen?card=${card.getal}"><img src="<c:url value="${card.image}" />" height="122" width="84"alt="${card.getal} ${card.kleur}"></a>			
			</td>
			</c:forEach>
		</tr>
	</table>
	</form>
		
</body>
</html>