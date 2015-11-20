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
	
		<table>
		<tr>
		<c:forEach items="${KJgame.players}" var="p">
			<td>
				<c:if test="${p.playCard!=null}">
					<img  src="<c:url value="${p.playCard.image}" />" height="122"  >
				</c:if>
				<c:if test="${p.playCard==null}">
					<img src="<c:url value="/IMAGES/emptyCard.svg" />" height="122"  >
				</c:if>
			</td>	
			<td class="box"> 
			<c:if test="${p.name!=KJplayer.name}">
				<h3>${p.name}</h3> 	
					<h4>team: <span class="team">${p.team}</span></h4>
					<h4>score: ${p.teamScore}</h4>
			</c:if>	
			<c:if test="${p.name==KJplayer.name}">
					<h3> You</h3> 	
					<h4>team: <span class="team">${p.team}</span> </h4>
					<h4>score: ${p.teamScore}</h4>
			</c:if>
			</td>
		</c:forEach>		
		</tr>
	</table>
	
	<h3> Which card do you want to play?</h3>
	
	
	<form method="post" action="/Kaartspellen/Klaverjassen/Play" > 
	<table>
		<tr> 
			<td >
				<h3>Troef:   </h3>
			</td>
			<td class=troef>
				<img src="<c:url value="${KJgame.troefCard.image}" />" height="122"  >
			</td>
			<td> 
				<h3 class=box2> Your hand:</h3> 	
			</td>
			<c:forEach items="${KJplayer.hand}" var="card">
				<td>
					<c:if test="${card.clickable==false}">
						<img class="playerCard" src="<c:url value="${card.image}" />" height="122" width="84">			
					</c:if>
					<c:if test="${card.clickable==true}">
						<input class="playerCard" type="image" src="<c:url value="${card.image}"/>" name="card" height="122" width="84" id="card"/>
					</c:if>
				</td>
			</c:forEach>
		</tr>
	</table>
	</form>
	
</div>
</body>
</html>