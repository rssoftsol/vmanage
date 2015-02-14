package com.imanage.intimate.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.imanage.intimate.EmailBean;
import com.imanage.intimate.Intimator;

@Service("intimatorService")
public class IntimatorImpl implements Intimator {
	
	private static Intimator intimator;
	
	@Override
	public void intimateMember(Long mobileNo, String text) {
		// TODO Auto-generated method stub
		System.out.println("sending message with mobile no. "+mobileNo+" and text as"+text);
	}

	@Override
	public void intimateOwner(EmailBean emailBean, JavaMailSender mailSenderImpl) {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage = mailSenderImpl.createMimeMessage();
		System.out.println("sending email");
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, true);
			mimeMessageHelper.setFrom(emailBean.getFromEmailId());
			mimeMessageHelper.setTo(emailBean.getToEmailId());
			mimeMessageHelper
					.setSubject(emailBean.getMailSubject());
			mimeMessageHelper.setText(emailBean.getMailBody(), true);
			if(emailBean.getEmailAttachment() == null){
				mimeMessageHelper.setText(emailBean.getMailBody(), true);
			}else{
				mimeMessageHelper.setText(emailBean.getMailBody(), true);
				mimeMessageHelper.addAttachment(emailBean.getEmailAttachment().getName(), emailBean.getEmailAttachment());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSenderImpl.send(mimeMessage);
	}
}
