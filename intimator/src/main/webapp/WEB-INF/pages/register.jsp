<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Registration</title>
</head>
<body>
<form action="/intimator/registerAction" method="post">
<table align="center" style="margin-top: 100px">
	<tr ><th></th><th><font color="red">${message}</font></th></tr>
	<tr><td>Club Name:</td><td><input type="text" id="clubname" name="clubname"/></td></tr>
	<tr><td>User Name:</td><td><input type="text" id="username" name="username"/></td></tr>
	<tr><td>Password :</td><td><input type="text" id="password" name="password"/></td></tr>
	<tr><td>Membership type:</td><td>Regular<input type="radio" checked="checked" id="regular" name="membershipType" value="regular"/>&nbsp;&nbsp;Premium<input type="radio" id="premium" name="membershipType" value="premium"/></td></tr>
	<tr><td></td><td><input type="submit" value="Register"></td></tr>
</table>
</form>
</body>
</html>