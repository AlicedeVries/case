<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<link type="text/css" rel="stylesheet" href="<c:url value="/opmaak.css"/>" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Kaartspellen Online</title>
</head>
<body>
<h1>Welcome to Kaartspellen Online</h1>
<div class=login>
<h3>Which game do you want to play? </h3>
<div class=gameButtons>
<form method="post" action="/Kaartspellen/Blackjack/Start">
	<input type="submit" value="Blackjack" style="width: 200px; color: #000000; height: 40px; font-size: 18px; font-weight: normal; background-color: #f67f00">
</form> <br>
<form method="post" action="/Kaartspellen/Poker/StartPoker">
	<input type="submit" value="Poker" style="width: 200px; color: #000000; height: 40px; font-size: 18px; font-weight: normal; background-color: #f67f00">
</form> <br>
<form method="post" action="/Kaartspellen/Klaverjassen/Start">
	<input type="submit" value="Klaverjassen" style="width: 200px; color: #000000; height: 40px; font-size: 18px; font-weight: normal; background-color: #f67f00">
</form>
</div>
</div>
<img src="<c:url value="/IMAGES/hand90.svg"/>" class="plaatje">							
</body>
</html>