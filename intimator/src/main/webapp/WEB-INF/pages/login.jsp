<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<title>intimator</title>
</head>
<body>
	<div class="">
		<div class="">
			<!-- mainLogin-container -->

			<div class="">
				<label style="margin: 0px 0px 0px 22px; color: gray;">Login : </label>
				<%-- <c:if test="${failureMessage != ''}"> --%>
					<br> <br>
					<label id="loginFailureLabel"
						style="margin: 0px 0px 0px 22px; font-size: 12px; color: red;display: none;">Login Failed!</label>
				<%-- </c:if> --%>
				<!-- login-box -->
				<form method="post" action="j_spring_security_check" name="f">
					<div class="">
						<p>
							Usename :
						</p>
						<input type="text" name="j_username" id="j_username" value=""
							class="textfield" onkeydown=""
							maxlength="20"><span style="display: none;"
							id="loginName" class="vLogin">&nbsp;</span>
					</div>
					<div class="">
						<p>
							Password :
						</p>
						<input type="password" name="j_password" id="j_password"
							onkeydown=""
							maxlength="20"><span style="display: none;" id="password">&nbsp;</span>
					</div>
					<div >
						<input type="submit" value="LOGIN" onclick="">
							<a id="forgot-password-link"
							style="color: #16A6BAA; float: left; margin-top: 10px; margin-left: 10px; vertical-align: text-bottom; cursor: pointer"
							href="forgotPassword.htm">Forgot Password?</a>
					</div>
				</form>
			</div>
			<!-- mainLogin-box -->

			<div class="mainregister-box">
				<!-- register-box -->
				<div class=""></div>
				<a href="registration.htm">
					<div class="">
						Register
					</div>
				</a>
			</div>
			<!-- register-box -->
		</div>
	</div>
	<!-- header -->
</body>
</html>