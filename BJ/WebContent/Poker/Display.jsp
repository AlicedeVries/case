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
			<td> <h3>Table: </h3> <h4> PotSize: ${game.potSize}  </h4> </td> 
			<td> 
				<img src="<c:url value="${game.bovensteKaart.image}" />" width="84pt"  height="122pt" >			
			</td>
			<c:forEach items="${game.tableCards}" var="card">
			<td>	
			<img src="<c:url value="${card.image}" />" width="84pt"  height="122pt"  alt="${card.getal} ${card.kleur}">			
			</td>
			</c:forEach>
		</tr>
		<c:forEach items="${players}" var="p">
		<tr> 
			<td> 
			<c:if test="${p.name!=player.name}">
				<h3> ${p.name} 's hand:</h3> 
			</c:if>
			<c:if test="${p.name==player.name}">
				<h3> Your hand:</h3> 
			</c:if>		

				<c:if test="${p.dealer==true}">
					<h4 style="color:#f67f00"> Dealer </h4>	
				</c:if>
			<h4> 	
				Chips: ${p.stack}
				<c:if test="${p.winner==true}">
					(+ ${gain})
				</c:if>
				
				
			</h4>
			</td>
			<c:forEach items="${p.hand}" var="card">
			<td>
			<c:if test="${p.visibleHand==true}">
				<img src="<c:url value="${card.image}" />" height="122" width="84"alt="${card.getal} ${card.kleur}">			
			</c:if>
			<c:if test="${p.visibleHand==false}">
				<img src="<c:url value="/IMAGES/backWithYCP.svg"/>" height="122" width="84"alt="${card.getal} ${card.kleur}">			
			</c:if>			
			</td>
			</c:forEach>
		</tr>
		</c:forEach>		
	</table>
</body>
</html>