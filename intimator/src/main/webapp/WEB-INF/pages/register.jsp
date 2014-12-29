<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Registration</title>
</head>
<body class="body">
<form:form action="/intimator/registerAction.json" method="post">
<table align="center" style="margin-top: 100px">
	<tr ><th></th><th><font color="red">${message}</font></th></tr>
	<tr><td>Club Name:</td><td><form:input id="clubname" path="clubname"/></td></tr>
	<tr><td>Phone:</td><td><form:input id="phonenumber" path="phonenumber"/></td></tr>
	<tr><td>User Name:</td><td><form:input id="username" path="username"/></td></tr>
	<tr><td>Password :</td><td><form:input id="password" path="password"/></td></tr>
	<%-- <tr><td>Membership type:</td><td>Regular<form:input type="radio" checked="checked" id="regular" path="membershiptype" value="R"/>&nbsp;&nbsp; --%>
	<%-- Premium<form:input type="radio" id="premium" path="membershipType" value="premium"/> --%></td></tr>
	<tr><td></td><td><input type="submit" value="Register"></td></tr>
</table>
</form:form>
</body>
</html>