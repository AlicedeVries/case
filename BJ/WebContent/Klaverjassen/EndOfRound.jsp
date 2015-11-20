<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="<c:url value="/opmaak.css"/>" />
<title>Card Played</title>
</head>
<body>
<h2>Klaverjassen Online</h2>
<div class=page>
	<table>
		<tr>
		<c:forEach items="${KJgame.players}" var="p">
			<td>
				<c:if test="${p==KJgame.winnerOfRound}">
				 	<img id="winner" src="<c:url value="${p.playCard.image}" />" height="122" width="84" >			
				</c:if>
				<c:if test="${p!=KJgame.winnerOfRound}">
				 	<img src="<c:url value="${p.playCard.image}" />" height="122" width="84" >			
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
	
	<h3> ${msg} ${msg2}</h3>
	
		<form method="post" action="/Kaartspellen/Klaverjassen/EndOfRound" > 
			<input type="submit" value="Next Round" style="width: 100px; color: #000000; height: 40px; font-size: 14px; font-weight: normal; background-color: #f67f00">			
		</form>	
		
		<br>
	<table>
		<tr> 
			<td >
				<h3>Troef:   </h3>
			</td>
			<td class=troef>
				<img src="<c:url value="${KJgame.troefCard.image}" />" height="122"  >
			</td>
		</tr>
	</table>
	
</div>	
		
	<script type="text/javascript">
		window.onload = function(){	
			document.getElementById("winner").style.border = "5px solid #000000";
			
			var teams = document.getElementsByClassName("team")
			for (var i=0; i<teams.length;i++)
					teams[i].style.color = teams[i].innerHTML;
		}
	</script>

</body>
</html>