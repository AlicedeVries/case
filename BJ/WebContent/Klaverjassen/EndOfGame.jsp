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
	<h3>Spel afgelopen</h3>
	<h3>Team <span class="team">${KJgame.winningTeam}</span> has won</h3>
	<table>
		<tr>
		<c:forEach items="${KJgame.players}" var="p">
			<td valign="top"> 
			<c:if test="${p.name!=player.name}">
				<h3>${p.name}</h3> 	
					<h4>team: <span class="team">${p.team}</span></h4>
					<h4>score: ${p.teamScore}</h4>
					
			</c:if>	
			<c:if test="${p.name==player.name}">
					<h3> You</h3> 	
					<h4>team: <span class="team">${p.team}</span> </h4>
					<h4>score: ${p.teamScore}</h4>
			</c:if>
			
			<c:forEach items="${p.slagen}" var="slag">
				<c:forEach items="${slag}" var="card">
					<img src="<c:url value="${card.image}" />" height="60"  >
				</c:forEach>
				<br>
			</c:forEach>
			</td>		
			<td>
				<p style="color:#ffffff">---------</p>
			</td>
		</c:forEach>		
		</tr>
	</table>
	
	<form method="post" action="/BJ/Klaverjassen/Wait" > 
		<input type="submit" value="Start new game" style="width: 150px; color: #000000; height: 40px; font-size: 14px; font-weight: normal; background-color: #f67f00">			
	</form>	
	
	<script>
	window.onload = function (){
		var teams = document.getElementsByClassName("team")
		for (var i=0; i<teams.length;i++)
				teams[i].style.color = teams[i].innerHTML;
	}
	</script>
		
</body>
</html>