<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- <style>
html {
  background-image: url("<c:url value="/resources/img/images.jpg" />");
  background-repeat: no-repeat;
  back
}
.body {
     background-color: transparent; 
 }
</style>
 -->
<html >
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">


<title>Login</title>

<style>
 /* body {
  background-image: url("<c:url value="/resources/img/images.jpg" />");
  background-repeat: no-repeat;
  background-position: 0 0;
  background-size: cover;
}

@media (min-width: 1120px), (min-height: 630px) {
    body { background-size: auto; }
}
 */
</style>	
</head>

<body class="body">
<form:form action="/intimator/loginAction" method="post">
<div class="center-right">
<div><font color="red">${message}</font></div>
<div><label class="col-xs-5">User Name:</label><span><form:input id="username" path="username"/></span></div>
<div><label class="col-xs-5">Password:</label><span><form:password  id="password" path="password"/></span></div>
<div><div class="col-xs-12"><input type="submit" class="btn" value="Login"></div></div>
<div><div class="col-xs-12"><label>New club?click <a href="/intimator/register">
<font color="red">here</font></a> for registration</label></div></div>
</div>
 
 </form:form>
</body>
</html>