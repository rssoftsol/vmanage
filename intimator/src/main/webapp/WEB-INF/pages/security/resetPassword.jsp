<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@ include
	file="/WEB-INF/views/includes/security/resetPassword_javascript.jsp"%> --%>
<title><spring:message code="resetPassword.title" /></title>
</head>
<body>
	<div class="header">
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
	</div>
	<!-- header -->
</body>
</html>