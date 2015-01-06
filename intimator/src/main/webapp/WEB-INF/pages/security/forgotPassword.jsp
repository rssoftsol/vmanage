<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Forgot Password</title>
<div class="">
	<div class="">
		<!-- forgotPasswd-container -->
		<div class="">
			<label style="margin: 0px 0px 0px 22px; color: gray;">Forgot Password : </label>
			<!-- forgotPasswd-box -->
			<form:form id="forgotPasswordForm" name="forgotPasswordForm"
				action="forgotPasswordSubmit.htm" method="post"
				commandName="forgotPasswordBean">
				<p>
					Enter your e-mail address below and we'll send you an e-mail message containing your new password.
				</p>
				<div class="forgotPasswd-form-input">
					<label style="color: gray; font-size: 14px;">E-mail Address </label> <input type="text" class="submitClass"
						id="toEmailId" name="toEmailId" value=""
						onchange=""><span
						class=""></span>
				</div>
				<div class="">
					<input type="submit" id="sendEmailBtn" value="Send"><font
						color='red' size="10px;"><form:errors path='toEmailId' />
				</div>
			</form:form>
		</div>
		<!-- forgotPasswd-box -->
	</div>
</div>