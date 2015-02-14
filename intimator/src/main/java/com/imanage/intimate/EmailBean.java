package com.imanage.intimate;

import java.io.File;

public class EmailBean {
	String fromEmailId;
	String toEmailId;
	String mailBody;
	String mailSubject;
	File emailAttachment;
	
	public String getFromEmailId() {
		return fromEmailId;
	}
	public EmailBean withFromEmailId(String fromEmailId) {
		this.fromEmailId = fromEmailId;
		return this;
	}
	public String getToEmailId() {
		return toEmailId;
	}
	public EmailBean withToEmailId(String toEmailId) {
		this.toEmailId = toEmailId;
		return this;
	}
	public String getMailBody() {
		return mailBody;
	}
	public EmailBean withMailBody(String mailBody) {
		this.mailBody = mailBody;
		return this;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public EmailBean withMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
		return this;
	}
	public File getEmailAttachment() {
		return emailAttachment;
	}
	public EmailBean withEmailAttachment(File emailAttachment) {
		this.emailAttachment = emailAttachment;
		return this;
	}
	
}
