<%@include file="commonmenu.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js" />"></script>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Registration</title>
</head>
<script type="text/javascript">
function checkPassword(t){
	if(t.value!=document.getElementById('password').value){
		t.value = '';
		alert('Password doesn`t match');
	}
}

 $(document).ready(function() {
 	$("#phonenumber").ForceNumericOnly();
});
</script>
<body background="<c:url value="/resources/img/body-bg.jpg" />">
<form:form action="${pageContext.request.contextPath}/createAction.htm" method="post" commandName="command">
	<form:hidden path="smsText" value="ur mem expire today"/>
<%-- <form:errors path="clubDetails.*" cssClass="errorblock" element="div" />
 --%><div class="form-register">
	<div class="panel panel-default">
	  <div class="panel-heading header"><h3 class="panel-title">Subscription</h3></div>
	  <div class="panel-body greybg">
	    <div class="col-xs-12">
			<div class="col-xs-4"><label>Club Name:</label></div>
			<div class="col-xs-4"><form:input type="text" id="clubname" path="clubname" 
			 maxlength="20" class="form-control" required="true" autofocus="true"/></div>
			<div class="col-xs-4"><form:errors path="clubname" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Phone:</label></div>
			<div class="col-xs-4"><form:input type="text" id="phonenumber" path="phonenumber" 
			maxlength="20" class="form-control" required="true" autofocus="true"/></div>
			<div class="col-xs-4"><form:errors path="phonenumber" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>User Name:</label></div>
			<div class="col-xs-4"><form:input type="text" id="username" path="username" class="form-control" 
			maxlength="20" required="true" autofocus="true"/></div>
			<div class="col-xs-4"><form:errors path="username" cssClass="error" element="div"/></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Password :</label></div>
			<div class="col-xs-4"><form:input type="password" id="password" maxlength="20" path="password" class="form-control"/></div>
			<div class="col-xs-4"><form:errors path="password" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Confirm Password :</label></div>
			<div class="col-xs-4"><input type="password" id="confirmPassword" maxlength="20" name="confirmPassword"
			 class="form-control" onblur ="return checkPassword(this);" required="true" autofocus="true"/></div>
			 <div class="col-xs-4"></div>
		 </div>
		 
		 <div class="col-xs-12">
			<div class="col-xs-4"><label>Email :</label></div>
			<div class="col-xs-4"><form:input type="text" id="email" maxlength="40" path="email" class="form-control" 
			required="true" autofocus="true"/></div>
			<div class="col-xs-4"><form:errors path="email" cssClass="error" element="div" /></div>
		</div>
		
		<!-- <div class="col-xs-12">
			<div class="col-xs-3"><label>Role :</label></div>
 			<div class="col-xs-4">-->
 					<input type="hidden" id="roleid" name="roleid" class="form-control" readonly="readonly" value="1"/>
 			<%--	</div>
			 <div class="col-xs-5"><form:errors path="clubDetails.roleid" cssClass="error" element="div" /></div>
		</div> --%>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Membership type:</label></div>
			<div class="col-xs-4">Regular<input type="radio" checked="checked" id="regular" name="membershiptype" value="R"/>
			&nbsp;&nbsp;
			Premium<input type="radio" id="premium" name="membershipType" value="premium"/></div>
			<div class="col-xs-4"><form:errors path="membershiptype" cssClass="error" element="div" /></div>
		</div>
		<div><input type="submit" value="Register" class="btn btn-primary pull-right"></div>
	  </div>
	</div>

	
	
		
	</div>
</form:form>
</body>
</html>