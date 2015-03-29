<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">


<title>Forgot Password</title>
<body class="forgotimage">
		<div class="container">
			<label>Forgot Password : </label>
			<!-- forgotPasswd-box -->
			<form:form id="forgotPasswordForm" name="forgotPasswordForm"
				action="forgotPasswordSubmit.htm" method="post"
				commandName="forgotPasswordBean">
				<p>
					Enter your e-mail address below and we'll send you an e-mail message containing your new password.
				</p>
				<div class="forgotPasswd-form-input">
					<span><label>User Name </label> <form:input type="text" class="submitClass"
						id="toEmailId" path="userName"/></span>
						<span><form:errors path="userName" cssClass="error" element="span" /></span>
				</div>
				<div class="">
					<input type="submit" id="sendEmailBtn" value="Send" class="btn btn-primary">
				</div>
			</form:form>
		</div>
		<!-- forgotPasswd-box -->
</body>
