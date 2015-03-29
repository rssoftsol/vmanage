package com.imanage.models.security;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.imanage.validators.username.IsUserNameValid;

public class ForgotPasswordBean {

	private String fromEmailId;
	private String toEmailId;
	private String subject;
	private String message;
	@NotEmpty(message="User Name cannot be empty") @IsUserNameValid
	private String userName;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFromEmailId() {
		return fromEmailId;
	}

	public void setFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
	}

	public String getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}