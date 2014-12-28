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
<div class="center-pos">
	<div><font color="red">${message}</font></div>
	<div class="col-xs-4"><label>Club Name:</label></div><div><form:input id="clubname" path="clubname"/></div>
	<div class="col-xs-4"><label>Phone:</label></div><div><form:input id="phonenumber" path="phonenumber"/></div>
	<div class="col-xs-4"><label>User Name:</label></div><div><form:input id="username" path="username"/></div>
	<div class="col-xs-4"><label>Password :</label></div><div><form:input id="password" path="password"/></div>
	<div class="col-xs-4"><label>Membership type:</label></div><div><label>Regular</label><form:input type="radio" checked="checked" id="regular" path="membershiptype" value="R"/>&nbsp;&nbsp;
	<%-- Premium<form:input type="radio" id="premium" path="membershipType" value="premium"/> --%></div>
	<div class="margin-small-top pull-right col-xs-8"><input type="submit" class="btn" value="Register"></div>
</div>
</form:form>
</body>
</html>