<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<script>
setTimeout( function() {window.location.href='<c:url value="/Klaverjassen/PlayAI"/>'},5000)
</script>


<h1 style="color:#f67f00">Welcome to Klaverjassen Online</h1>
	<table>
		<c:forEach items="${KJgame.players}" var="p">
		<tr> 
			<c:if test="${p.name!=player.name}">
			<td> 
				<h3>${p.name} 's hand:</h3> 	
				<h4>team: ${p.team}</h4>
			</td>
			<c:forEach items="${p.hand}" var="card">
				<td>
				<c:if test="${card == p.playCard}">
					<img src="<c:url value="${card.image}" />" height="122" width="84">			
				</c:if>
				<c:if test="${card != p.playCard }">
					<img src="<c:url value="/IMAGES/backWithYCP.svg" />" height="122" width="84">							
				</c:if>
				</td>
			</c:forEach>
			</c:if>	
		</tr>
		</c:forEach>		
		<tr> 
			<td> 
				<h3> Your hand:</h3> 	
				<h4>team: ${player.team}</h4>
			</td>
			<c:forEach items="${player.hand}" var="card">
			<td>
				<img class="playerCard" src="<c:url value="${card.image}" />" height="122" width="84">			
			</td>
			</c:forEach>
		</tr>
	</table>
	
	<h4> Wait for other players to play a card </h4>

</body>
</html>