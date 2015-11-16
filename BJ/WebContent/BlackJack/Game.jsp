<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<meta http-equiv="refresh" content="10 url=/BJ/Blackjack/Stand">
<title>Game</title>
</head>
<body>
<h1 style="color:#f67f00">Welcome to Blackjack Online</h1>
	<c:import url="/BlackJack/Display.jsp"></c:import>
	<form method="post" action="/BJ/Klaverjassen/Play" > 
	<table>
		<tr> 
			<td> 
				<h3> Your hand:</h3> 	
				<h4>team: ${player.team}</h4>
			</td>
			<c:forEach items="${player.hand}" var="card">
			<td class='card'>
				<input type="image" src="<c:url value="${card.image}"/>"  name="card" height="122" width="84"/>
			</td>
			</c:forEach>
		</tr>
	</table>
	</form>	
		
	<script>
		window.onload = function(){
			var images = document.getElementsByTagName('input');

			for (var i=0; i<images.length; i++){
					images[i].value=i;
			}
		}
	</script>
</body>
</html>