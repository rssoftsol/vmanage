<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include
	file="/WEB-INF/views/includes/security/forgotPassword_javascript.jsp"%> --%>
<div class="header">
	<div class="invalidLink-container">
		<!-- invalidLink-container -->
		<div class="invalidLink-box">
			<!-- invalidLink-box -->
			<form:form action="" cssClass="linkForm">
				<c:choose>
					<c:when test="${ not empty passwdResetSuccess }">
							<c:if test="${ passwdResetSuccess == 'ok' }">
								<label id="resetSuccessLabel" class="passwdResetLabelCss"><spring:message
										code="resetPassword.resetSuccess" /></label>
								<a href="login" style="margin: 0 0 0 40px;"> <spring:message
										code="resetPassword.loginLinkLabel" />
								</a>
							</c:if>
							<c:if test="${ passwdResetSuccess == 'passwdChngSuccess' }">
								<label id="resetSuccessLabel" class="passwdResetLabelCss" style="width: 230px;"><spring:message
										code="changePassword.updateSuccess" /></label>
								<a href="home" style="margin: 0 0 0 40px;"> <spring:message
										code="changePassword.gotoHome" />
								</a>
							</c:if>
							<c:if test="${ passwdResetSuccess == 'error' }">
								<label id="resetSuccessLabel" class="passwdResetErrorLabelCss"><spring:message
										code="resetPassword.resetProblem" /></label>
								<a href="login" style=""> <spring:message
										code="resetPassword.loginLinkLabel" />
								</a>
							</c:if>
					</c:when>
					<c:otherwise>
						<label id="resetSuccessLabel" class="invalidLabelCss"><spring:message
								code="resetPassword.invalidLink" /></label>
						<a href="login" style=""> <spring:message
								code="resetPassword.loginLinkLabel" />
						</a>
					</c:otherwise>
				</c:choose>
			</form:form>
		</div>
		<!-- forgotPasswd-box -->
	</div>
</div>