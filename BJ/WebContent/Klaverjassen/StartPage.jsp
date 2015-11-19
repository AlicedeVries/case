<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<link type="text/css" rel="stylesheet" href="<c:url value="/opmaak.css"/>" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<h1>Welcome to Klaverjassen Online</h1>
<div class=login>
<form method="post" action="/Kaartspellen/Klaverjassen/Start">
<h3>Enter your name to start klaverjassen: </h3><input name="name" type="text" style="width: 200px; height: 35px; font-size: 14px; text-indent: 10px">
<input type="submit" value="Start game" style="width: 100px; color: #000000; height: 40px; font-size: 14px; font-weight: normal; background-color: #f67f00"></form>
</div>
<img src="<c:url value="/IMAGES/klaverjas90.svg"/>" class="plaatje">							
</body>
</html>