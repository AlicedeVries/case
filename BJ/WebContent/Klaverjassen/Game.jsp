<!DOCTYPE html >
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<link type="text/css" rel="stylesheet" href="<c:url value="/opmaak.css"/>" />     
<meta charset="UTF-8">
<title>Game</title>
</head>
<body>
<h2>Klaverjassen Online</h2>
<div class=page>
	<h3>Troef is ${KJgame.troef} </h3>
	<script>
		window.onload = function(){
			var images = document.getElementsByClassName('playerCard');
			for ( var i =0; i<images.length;i++)
				images[i].value = i;
			
			var teams = document.getElementsByClassName("team")
			for (var i=0; i<teams.length;i++)
					teams[i].style.color = teams[i].innerHTML;
		}
	</script>
	
	<form method="post" action="/Kaartspellen/Klaverjassen/Play" > 
	<table>
		<c:forEach items="${KJgame.players}" var="p">
		<tr> 
			<c:if test="${p.name!=player.name}">
				<td> 
					<h3>${p.name} 's hand:</h3> 	
					<h4>team: <span class="team">${p.team}</span></h4>
					<h4>score: ${p.teamScore}</h4>
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
					<h4>team: <span class="team">${p.team}</span></h4>
					<h4>score: ${p.teamScore}</h4>
				</td>
				<c:forEach items="${player.hand}" var="card">
				<td>
					<c:if test="${card.clickable==false}">
						<img class="playerCard" src="<c:url value="${card.image}" />" height="122" width="84">			
					</c:if>
					<c:if test="${card.clickable==true}">
						<input class="playerCard" type="image" src="<c:url value="${card.image}"/>" name="card" height="122" width="84" id="card"/>
					</c:if>
				</td>
				</c:forEach>			
			</c:if>
			
			<c:if test="${p.playCard!=null}">
				<td>
					<p style="color:#ffffff">-----------------</p>
				</td>
				<td>
					 <img src="<c:url value="${p.playCard.image}" />" height="122" width="84" >			
				</td>
			</c:if>
		</tr>
		</c:forEach>		
		<tr> 
		</tr>
	</table>
	</form>	
		
</div>
</body>
</html>