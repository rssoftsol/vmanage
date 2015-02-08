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
<%@ include file="popup.html" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Registration</title>
</head>
<script type="text/javascript">
	if("${popupMessage}"!=''){
		$('#popup').modal('show');
	}
	$(document).ready(function() {
	 	$("#phonenumber").ForceNumericOnly();
	});
</script>
<body class="body">
<form:form action="${pageContext.request.contextPath}/myprofile/editAction.json" method="post" commandName="command">
<%-- <form:errors path="clubDetails.*" cssClass="errorblock" element="div" />
 --%>
 <form:input type="hidden" id="club_id" path="club_id"/>
 <form:input type="hidden" id="password" path="password"/>
 <form:input type="hidden" id="newPassword" path="newPassword"/>
 <div class="form-register">
	<div class="panel panel-default">
	  <div class="panel-heading header"><h3 class="panel-title">Registration</h3></div>
	  <div class="panel-body greybg">
	    <div class="col-xs-12">
			<div class="col-xs-4"><label>Club Name:</label></div>
			<div class="col-xs-4"><form:input type="text" id="clubname" path="clubname" class="form-control" /></div>
			<div class="col-xs-4"><form:errors path="clubname" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Phone:</label></div>
			<div class="col-xs-4"><form:input type="text" id="phonenumber" path="phonenumber" class="form-control" /></div>
			<div class="col-xs-4"><form:errors path="phonenumber" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>User Name:</label></div>
			<div class="col-xs-4"><form:input type="text" id="username" path="username" class="form-control" readonly="true"/></div>
			<div class="col-xs-4"><form:errors path="username" cssClass="error" element="div"/></div>
		</div>
		
		 <div class="col-xs-12">
			<div class="col-xs-4"><label>Email :</label></div>
			<div class="col-xs-4"><form:input type="text" id="email" path="email" class="form-control" /></div>
			<div class="col-xs-4"><form:errors path="email" cssClass="error" element="div" /></div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-4"><label>Membership type:</label></div>
			<div class="col-xs-4">Regular<form:radiobutton checked="checked" id="regular" path="membershiptype" value="R"/>
			&nbsp;&nbsp;
			Premium<form:radiobutton id="premium" path="membershiptype" value="P"/></div>
			<div class="col-xs-4"><form:errors path="membershiptype" cssClass="error" element="div" /></div>
		</div>
		<div><input type="submit" value="Edit" class="btn btn-primary pull-right"></div>
	  </div>
	</div>

	
	
		
	</div>
</form:form>
</body>
</html>