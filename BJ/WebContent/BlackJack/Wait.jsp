<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<link type="text/css" rel="stylesheet" href="<c:url value="/opmaak.css"/>" />
<title>Waiting</title>
</head>
<body>
<script>
setTimeout( function() {window.location.href='<c:url value="/Blackjack/Wait"/>'},5000)
</script>
<h1>Welcome to Blackjack Online</h1>
<div class=login>
<h3>Waiting for game to start... </h3>
</div>
<img src="<c:url value="/IMAGES/blackjack.svg"/>" class="plaatje">							
</body>
</html>