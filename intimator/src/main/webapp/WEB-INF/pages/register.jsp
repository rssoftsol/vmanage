<%@include file="commonmenu.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Registration</title>
</head>
<body class="body">
<form action="/${pageContext.request.contextPath}/registerAction.json" method="post">
<div class="form-register">
	<div class="panel panel-default">
	  <div class="panel-heading header"><h3 class="panel-title">Registration</h3></div>
	  <div class="panel-body greybg">
	    <div><font color="red">${message}</font></div>
		<div class="col-xs-4"><label>Club Name:</label></div>
		<div class="col-xs-6"><input type="text" id="clubname" name="clubname" class="form-control"/></div>
		<div class="col-xs-4"><label>Phone:</label></div>
		<div class="col-xs-6"><input type="text" id="phonenumber" name="phonenumber" class="form-control"/></div>
		<div class="col-xs-4"><label>User Name:</label></div>
		<div class="col-xs-6"><input type="text" id="username" name="username" class="form-control"/></div>
		<div class="col-xs-4"><label>Password :</label></div>
		<div class="col-xs-6"><input type="password" id="password" name="password" class="form-control"/></div>
		<div class="col-xs-4"><label>Email :</label></div>
		<div class="col-xs-6"><input type="text" id="email" name="email" class="form-control"/></div>
		<div class="col-xs-4"><label>Role :</label></div>
		<div class="col-xs-6"><input type="text" id="roleid" name="roleid" class="form-control" readonly="readonly" value="1"/></div>
		<div class="col-xs-4"><label>Membership type:</label></div>
		<div class="col-xs-6">Regular<input type="radio" checked="checked" id="regular" name="membershiptype" value="R"/>
		&nbsp;&nbsp;
		Premium<input type="radio" id="premium" name="membershipType" value="premium"/></div>
		<div><input type="submit" value="Register" class="btn btn-primary"></div>
	  </div>
	</div>

	
	
		
	</div>
</form>
</body>
</html>