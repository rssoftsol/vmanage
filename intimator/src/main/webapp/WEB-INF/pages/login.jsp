<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>


<title>Login</title>
<body style="background-image: url('<c:url value="/resources/img/images.jpg" />');">
<form action="/intimator/loginAction" method="post">
<table align="center" style="margin-top: 100px">
<tr><th></th><th><font color="red">${message}</font></th></tr>
<tr><td>User Name:</td><td><input type="text" id="username" name="name"></td></tr>
<tr><td>Password :</td><td><input type="password" id="password" name="password"></td></tr>
<tr><td></td><td><input type="submit" value="Login"></td></tr>
<tr><td></td><td>New club?click <a href="/intimator/register">here</a> for registration</td></tr>
</table>

<%--  <div style="margin-top: 100px">
<div><font color="red">${message}</font></div>
<div><span>User Name:</span><span><input type="text" id="username" name="name"></span></div>
<div><span>Password:</span><span><input type="password" id="password" name="password"></span></div>
<div><span><input type="submit" value="Login"></span></div>
<div><span>New club?click <a href="/intimator/register">here</a> for registration</span></div>
</div>
 --%> 
 </form>
</body>
</html>