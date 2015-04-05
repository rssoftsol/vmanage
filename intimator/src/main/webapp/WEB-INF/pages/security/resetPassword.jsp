<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../style/style.jsp" %>
</head>
<script>
function reset(){
	document.getElementById('passwd').value = '';
	document.getElementById('c_password').value = '';
}
function validateConfirmPassword(){
	if(document.getElementById('passwd').value != document.getElementById('c_password').value){
		alert("Password mismatch");
		return false;
	}
}

</script>
<body class="header">
	<form:form method="post" action="resetPasswordSubmit.htm?uId=${uId}"
					id="resetPasswordForm" name="resetPasswordForm"
					commandName="clubDetailsBean" class="form-signin">
		<div class="form-register">
				<div class="panel panel-default">
			  		<div class="panel-heading header"><h3 class="panel-title">Reset Password</h3></div>
			  		<div class="panel-body">
			  		
			  		<input type="hidden" id="username" name="username" value="${clubDetailsBean.username}"/>
							
								<p>
									<spring:message code="resetPassword.password" />
								</p>
							<div >
								<input type="password" id="passwd"
									name="password" size="20" placeholder="Password" class="form-control">
							</div>
								<p>
									<spring:message code="resetPassword.confirmPassword" />
								</p>
							<div>
								<input type="password" id="c_password"
									name="c_password" size="20" placeholder="Confirm password" class="form-control">
							</div>
							<div style="margin-top: 5%">
								<input type="submit" value="<spring:message code="resetPassword.resetBtn" />" 
								class="btn btn-primary" onclick="return validateConfirmPassword();">
								<input type="button" value="<spring:message code="resetPassword.clearBtn" />" onclick="reset();" 
								class="btn btn-primary">
							</div>
			  		
			  		
			  		</div>
			  	</div>
		</div>
	</form:form>
	<%-- <div class="header">
		<div class="resetPassword-container">
			<!-- resetPassword-container -->

			<div class="resetPassword-box">
				<label style="margin: 0px 0px 0px 22px; color: gray;"><spring:message
						code="resetPassword.title" /> : </label>
						<br>
						<br>
				<!-- login-box -->
				<form:form method="post" action="resetPasswordSubmit.htm?uId=${uId}"
					id="resetPasswordForm" name="resetPasswordForm"
					commandName="clubDetailsBean" cssClass="resetForm">
					<input type="hidden" id="username" name="username" value="${clubDetailsBean.username}"/>
					<div class="form-input">
						<p>
							<spring:message code="resetPassword.password" />
						</p>
						<input class="required" type="password" id="passwd"
							name="password" size="20" placeholder="Password">
					</div>
					<div class="form-input">
						<p>
							<spring:message code="resetPassword.confirmPassword" />
						</p>
						<input class="required" type="password" id="c_password"
							name="c_password" size="20" placeholder="Confirm password">
					</div>
					<div class="form-input">
						<input type="submit" value="<spring:message code="resetPassword.resetBtn" />" >
						<input type="button" value="<spring:message code="resetPassword.clearBtn" />" onclick="reset();">
					</div>
				</form:form>
			</div>
			<!-- resetPassword-box -->
		</div>
	</div> --%>
	<!-- header -->
</body>
</html>