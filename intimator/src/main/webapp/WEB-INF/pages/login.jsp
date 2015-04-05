<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="style/style.jsp" %>
<%@include file="commonmenu.jsp" %>
<title>Intimator-Login</title>
</head>
<body class="header">
	<div class="container">
			<!-- mainLogin-container -->
				<%-- <c:if test="${failureMessage != ''}"> --%>
					<label id="loginFailureLabel"
						style="margin: 0px 0px 0px 22px; font-size: 12px; color: red;display: none;">Login Failed!</label>
				<%-- </c:if> --%>
				<!-- login-box -->
				
				<form method="post" action="j_spring_security_check" name="f" class="form-signin">
					<h2>Login  </h2>or
				<a href="create.htm">
						<b>Subscribe</b>
				</a>
			<!-- register-box -->
					<div><label><font color="red">${failureMessage}</font></label></div>
					<label for="j_username" class="sr-only">Usename</label>
					<input type="text" name="j_username" id="j_username" value=""
							class="form-control" placeholder="User Name" required autofocus
							maxlength="20">
							
							<span style="display: none;"
							id="loginName" class="vLogin">&nbsp;</span>
					<label for="j_password" class="sr-only">Password</label>
					
					<input type="password" name="j_password" id="j_password"
							onkeydown=""
							maxlength="20" class="form-control" placeholder="Password" required><span style="display: none;" id="password">&nbsp;</span>
					<div >
						<input type="submit" class="btn btn-lg btn-primary btn-block" value="LOGIN" onclick="">
							<a id="forgot-password-link"
							href="forgotPassword.htm"><b>Forgot Password?</b></a>
					</div>
				</form>
			<!-- mainLogin-box -->

					</div>
	<!-- header -->
</body>
</html>
<script>
$('#login').addClass('active');
</script>