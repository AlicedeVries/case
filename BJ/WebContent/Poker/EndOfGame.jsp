<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game</title>
</head>
<body>
<h1 style="color:#f67f00">Welcome to Poker Online</h1>
	<c:import url="/Poker/Display.jsp"></c:import>

	<h4>${msg}</h4>

		<form method="post" action="/BJ/Poker/PlayPoker">
			<input type="submit" name="restart" value="Start New Game" style="width: 150px; color: #000000; height: 40px; font-weight: normal; font-size: 14px; background-color: #f67f00">
		</form>
	
</body>
</html>