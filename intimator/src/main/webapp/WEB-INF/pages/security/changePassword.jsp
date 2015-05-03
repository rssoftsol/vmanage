<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include
	file="/WEB-INF/views/includes/security/changePassword_javascript.jsp"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<title><spring:message code="changePassword.title" /></title>

</head>
<body>
	<div class="header">
		<div class="changePassword-container">
			<!-- changePassword-container -->

			<div class="changePassword-box">
				<label style="margin: 0px 0px 0px 22px; color: gray;"><spring:message
						code="changePassword.title" /> : </label> <br> <br> <!-- login-box -->
						<form:form method="post" action="changePasswordSubmit.htm"
							id="changePasswordForm" name="changePasswordForm"
							commandName="customerBean" cssClass="changeForm">
							<input type="hidden" id="passwdChngFailure"
								name="passwdChngFailure" value="${passwdChngFailure}">
							<input type="hidden" id="loginName" name="loginName"
								value="<sec:authentication property='principal.username' />" />
							<label id="passwdChngFailureId"
								style="font-size: 12px; color: red; display: none;"><spring:message
									code="changePassword.updateFailure" /></label>
							<div class="form-input">
								<p>
									<spring:message code="changePassword.oldPassword" />
								</p>
								<input class="required" type="password" id="oldPasswd"
									name=password size="20" placeholder="Old Password">
							</div>
							<div class="form-input">
								<p>
									<spring:message code="changePassword.newPassword" />
								</p>
								<input class="required" type="password" id="newPasswd"
									name="newPassword" size="20" placeholder="New password">
							</div>
							<div class="form-input">
								<p>
									<spring:message code="changePassword.confirmPassword" />
								</p>
								<input class="required" type="password" id="c_password"
									name="c_password" size="20" placeholder="Confirm password">
							</div>
							<div class="form-input">
								<input type="submit"
									value="<spring:message code="changePassword.changeBtn" />">
									<input type="button"
									value="<spring:message code="changePassword.clearBtn" />"
									onclick="reset();">
							</div>
						</form:form>
			</div>
			<!-- changePassword-box -->
		</div>
	</div>
	<!-- header -->
</body>
</html>