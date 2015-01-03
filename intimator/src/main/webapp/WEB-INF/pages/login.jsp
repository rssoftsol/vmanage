<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html >
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">


<title>Login</title>
</head>

<body class="body">
<form action="/intimator/loginAction" method="post">
 <div class="center-right">
<div><font color="red">${message}</font></div>
<div><span>User Name:</span><span><input type="text" id="username" name="username"/></span></div>
<div><span>Password:</span><span><input type="password" id="password" name="password"/></span></div>
<div><span><input type="submit" value="Login"></span></div>
<div><span>New club?click <a href="/intimator/register">here</a> for registration</span></div>
<div><span>New club?click <a href="/intimator/schedular">here</a> Current Date/Time</span></div>
</div>
 
 </form>
</body>
</html>