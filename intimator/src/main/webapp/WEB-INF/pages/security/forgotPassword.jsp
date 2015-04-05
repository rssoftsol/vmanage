<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../style/style.jsp" %>

<title>Intimator-Forgot Password</title>
<body class="header">
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
