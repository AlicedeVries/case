<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<title>Waiting</title>
</head>
<body>
<h1 style="color:#f67f00">Welcome to Klaverjassen Online</h1>


<h3>Waiting for game to start... </h3>

<script>
setTimeout( function() {window.location.href='<c:url value="/Klaverjassen/Wait"/>'},5000)
</script>

</body>
</html>