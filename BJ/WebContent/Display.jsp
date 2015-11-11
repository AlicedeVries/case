<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Game</title>
</head>
<body>
	<table>
		<tr> 
			<td> <h4>Dealers hand:</h4></td> 
			<c:forEach items="${game.dealerHand}" var="card">
			<td>	
			<img src="<c:url value="${card.image}" />" width="84pt"  height="122pt"  alt="${card.waarde} ${card.kleur}">			
			</td>
			</c:forEach>
		</tr>
		<c:forEach items="${players}" var="p">
		<tr> 
			<td> 
			<c:if test="${p.name!=player.name}">
				<h4> ${p.name} 's hand:</h4> 
			</c:if>
			<c:if test="${p.name==player.name}">
				<h4> Your hand:</h4> 
			</c:if>		
			</td>
			<c:forEach items="${p.hand}" var="card">
			<td>
			<img src="<c:url value="${card.image}" />" height="122" width="84"alt="${card.waarde} ${card.kleur}">			
			</td>
			</c:forEach>
		</tr>
		</c:forEach>		
	</table>
</body>
</html>