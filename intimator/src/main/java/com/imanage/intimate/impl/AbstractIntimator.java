package com.imanage.intimate.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.imanage.intimate.EmailBean;
import com.imanage.intimate.Intimator;
import com.imanage.models.EmailConfigBean;
import com.imanage.util.sms.SmsCallGet;

public abstract class AbstractIntimator<T> implements Intimator {
	protected Long mobileNo;
	protected String text;
	protected EmailBean emailBean;
	@Autowired
	EmailConfigBean emailConfigBean;
	@Autowired
	private JavaMailSenderImpl mailSenderImpl;
	
	public abstract void intimate(T t);
	
	public abstract void setEmailBean(T t);

	public void doInitialization(T t){
		setEmailBean(t);
	}
	@Override
	public void intimateBySMS() {
		// TODO Auto-generated method stub
		System.out.println("sending message with mobile no. " + mobileNo
				+ " and text as" + text);
		SmsCallGet smsCallGet = new SmsCallGet();
		smsCallGet.sendMessage(text, String.valueOf(mobileNo));
	}

	@Override
	public void intimateByEmail() {

		MimeMessage mimeMessage = mailSenderImpl.createMimeMessage();

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, true);
			mimeMessageHelper.setFrom(emailBean.getFromEmailId());
			mimeMessageHelper.setTo("chandrashekhar.m.patil@gmail.com");
			mimeMessageHelper.setSubject(emailBean.getMailSubject());

			/*
			 * FileSystemResource file = new
			 * FileSystemResource("C:\\Sample.pdf");
			 * mimeMessageHelper.addAttachment(file.getFilename(), file);
			 * 
			 * mailSenderImpl.send(mimeMessage);
			 */

			mimeMessageHelper.setText(emailBean.getMailBody(), true);
			if(emailBean.getEmailAttachment()!=null){
				mimeMessageHelper.addAttachment(emailBean.getEmailAttachment()
					.getFilename(), emailBean.getEmailAttachment());
			}
			mailSenderImpl.send(mimeMessage);
			//put logger here
			System.out.println("Sent successfully...!");
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			//put logger here
		} catch (MailSendException e){
			System.out.println(e.getMessage());
			//put logger here
		}

	}
}