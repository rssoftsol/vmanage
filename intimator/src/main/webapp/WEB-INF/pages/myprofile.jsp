<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="menu.jsp" %>
<html>
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">


<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

<script src="<c:url value="/resources/js/table.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>
<script src="<c:url value="/resources/js/jquery.maxlength.js" />"></script>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>My Profile</title>
</head>
<script type="text/javascript">
	var active = "${isActive}";
	$(document).ready(function() {
	 	$("#phonenumber").ForceNumericOnly();
	 	
	 	if(active=='N'){
	    	$("#deActivate").hide();
	    }else{
	    	$("#reActivate").hide();
	    }
	    
	    $('#deActivate').on( 'click', function () {
	  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/myprofile/deActivate');
	  	  	$('form[name=form]').submit();
	  	});
	    
	    $('#reActivate').on( 'click', function () {
	  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/myprofile/reActivate');
	  	  	$('form[name=form]').submit();
	  	});
	});
</script>
<body class="body" background="<c:url value="/resources/img/body-bg.jpg" />">
<form:form name="form" action="${pageContext.request.contextPath}/myprofile/editAction.htm" method="post" commandName="command">
<%-- <form:errors path="clubDetails.*" cssClass="errorblock" element="div" />
 --%>
 <form:input type="hidden" id="club_id" path="club_id"/>
 <form:input type="hidden" id="password" path="password"/>
 <form:input type="hidden" id="newPassword" path="newPassword"/>
 <div class="form-register">
	<div class="panel panel-default">
	  <div class="panel-heading header"><h3 class="panel-title">My Profile</h3></div>
	  <div class="panel-body greybg">
	    <div class="col-xs-12">
			<div class="col-xs-4"><label>Club Name:</label></div>
			<div class="col-xs-4"><form:input type="text" id="clubname" maxlength="20" path="clubname" class="form-control" /></div>
			<div class="col-xs-4"><form:errors path="clubname" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Phone:</label></div>
			<div class="col-xs-4"><form:input type="text" id="phonenumber" maxlength="10" path="phonenumber" class="form-control" /></div>
			<div class="col-xs-4"><form:errors path="phonenumber" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>User Name:</label></div>
			<div class="col-xs-4"><form:input type="text" id="username"
				maxlength="20" path="username" class="form-control" readonly="true"/></div>
			<div class="col-xs-4"><form:errors path="username" cssClass="error" element="div"/></div>
		</div>
		
		 <div class="col-xs-12">
			<div class="col-xs-4"><label>Email :</label></div>
			<div class="col-xs-4"><form:input type="text" id="email" maxlength="40" path="email" class="form-control" /></div>
			<div class="col-xs-4"><form:errors path="email" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Membership type:</label></div>
			<div class="col-xs-4">Regular<form:radiobutton checked="checked" id="regular" path="membershiptype" value="R"/>
			&nbsp;&nbsp;
			Premium<form:radiobutton id="premium" path="membershiptype" value="P"/></div>
			<div class="col-xs-4"><form:errors path="membershiptype" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>SMS Text:</label></div>
			<div class="col-xs-4"><form:textarea id="smsText" maxlength="160" path="smsText" class="form-control" /></div>
			<div class="col-xs-4"><form:errors path="smsText" cssClass="smsText" element="div" /></div>
		</div>
		
		<div class="col-xs-12" style="margin-top:5px">
			<div class="col-xs-11">
				<input type="button" id="deActivate" value="Deactivate Account" class="btn btn-primary pull-right">
				<input type="button" id="reActivate" value="Activate Account" class="btn btn-primary pull-right">
			</div>
		<div class="col-xs-1"><input type="submit" value="Edit" class="btn btn-primary"></div>
		
		</div>
	  </div>
	</div>
	</div>
</form:form>
</body>
</html>