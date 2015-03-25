package com.imanage.intimate;

import org.springframework.core.io.FileSystemResource;

public class EmailBean {
	String fromEmailId;
	String toEmailId;
	String mailBody;
	String mailSubject;
	FileSystemResource emailAttachment;
	
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
	public FileSystemResource getEmailAttachment() {
		return emailAttachment;
	}
	public EmailBean withEmailAttachment(FileSystemResource emailAttachment) {
		this.emailAttachment = emailAttachment;
		return this;
	}
	
	@Override
	public String toString() {
		return "fromEmailId: "+fromEmailId+" -toEmailId:"+toEmailId+" -mailSubject:"+ mailSubject+" -mailBody:"+mailBody;
	}
	
}
