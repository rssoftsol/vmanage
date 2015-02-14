package com.imanage.models;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfigBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7599638012054745679L;

	@Value("${emailConfig.host}")
	private String emailHost;

	@Value("${emailConfig.emailText1}")
	private String emailText1;

	@Value("${emailConfig.websiteLink}")
	private String websiteLink;

	@Value("${emailConfig.emailText2}")
	private String emailText2;

	@Value("${emailConfig.resetPasswordLink}")
	private String resetPasswordLink;

	@Value("${emailConfig.emailText3}")
	private String emailText3;

	@Value("${emailConfig.resetPasswordDisplayLink}")
	private String resetPasswordDisplayLink;

	@Value("${emailConfig.emailText4}")
	private String emailText4;

	@Value("${emailConfig.emailFrom}")
	private String emailFrom;

	@Value("${emailConfig.emailSubject}")
	private String emailSubject;
	
	@Value("${emailConfig.emailPDFInfoText}")
	private String emailPDFInfoText;
	
	@Value("${emailConfig.emailPDFNoRecordText}")
	private String emailPDFNoRecordText;

	@Value("${emailConfig.pdfEmailSubject}")
	private String pdfEmailSubject;
	
	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailText1() {
		return emailText1;
	}

	public void setEmailText1(String emailText1) {
		this.emailText1 = emailText1;
	}

	public String getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	public String getEmailText2() {
		return emailText2;
	}

	public void setEmailText2(String emailText2) {
		this.emailText2 = emailText2;
	}

	public String getResetPasswordLink() {
		return resetPasswordLink;
	}

	public void setResetPasswordLink(String resetPasswordLink) {
		this.resetPasswordLink = resetPasswordLink;
	}

	public String getEmailText3() {
		return emailText3;
	}

	public void setEmailText3(String emailText3) {
		this.emailText3 = emailText3;
	}

	public String getResetPasswordDisplayLink() {
		return resetPasswordDisplayLink;
	}

	public void setResetPasswordDisplayLink(String resetPasswordDisplayLink) {
		this.resetPasswordDisplayLink = resetPasswordDisplayLink;
	}

	public String getEmailText4() {
		return emailText4;
	}

	public void setEmailText4(String emailText4) {
		this.emailText4 = emailText4;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailPDFInfoText() {
		return emailPDFInfoText;
	}

	public void setEmailPDFInfoText(String emailPDFInfoText) {
		this.emailPDFInfoText = emailPDFInfoText;
	}

	public String getPdfEmailSubject() {
		return pdfEmailSubject;
	}

	public void setPdfEmailSubject(String pdfEmailSubject) {
		this.pdfEmailSubject = pdfEmailSubject;
	}

	public String getEmailPDFNoRecordText() {
		return emailPDFNoRecordText;
	}

	public void setEmailPDFNoRecordText(String emailPDFNoRecordText) {
		this.emailPDFNoRecordText = emailPDFNoRecordText;
	}

	
}
