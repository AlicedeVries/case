<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<title>Card Played</title>
</head>
<body>
<h1 style="color:#f67f00">Welcome to Klaverjassen Online</h1>
	<h3>Troef is ${KJgame.troef} </h3>
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
					<c:if test="${card != p.playCard }">
						<img src="<c:url value="/IMAGES/backWithYCP.svg" />" height="122" width="84">							
					</c:if>
					</td>
				</c:forEach>
			</c:if>	
			<c:if test="${p.name==player.name}">
				<td> 
					<h3> Your hand:</h3> 	
					<h4>team: ${player.team}</h4>
				</td>
				<c:forEach items="${player.hand}" var="card">
				<td>
					<c:if test="${card!=player.playCard}">
						<img src="<c:url value="${card.image}" />" height="122" width="84" >			
					</c:if>
				</td>
				</c:forEach>			
			</c:if>
			
			<td>
				----------------->
			</td>
			<td>
				<c:if test="${p==KJgame.winnerOfRound}">
				 	<img id="winner" src="<c:url value="${p.playCard.image}" />" height="122" width="84" >			
				</c:if>
				<c:if test="${p!=KJgame.winnerOfRound}">
				 	<img src="<c:url value="${p.playCard.image}" />" height="122" width="84" >			
				</c:if>
			</td>

		</tr>
		</c:forEach>		
	</table>
	<br>
		<form method="post" action="/BJ/Klaverjassen/EndOfRound" > 
			<input type="submit" value="Next Round" style="width: 100px; color: #000000; height: 40px; font-size: 14px; font-weight: normal; background-color: #f67f00">			
		</form>	
		
	<script type="text/javascript">
		window.onload = function(){	document.getElementById("winner").style.border = "5px solid #000000";}
	</script>
</body>
</html>