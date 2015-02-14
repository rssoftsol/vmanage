package com.imanage.intimate.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.imanage.intimate.EmailBean;
import com.imanage.intimate.Intimator;

@Service("intimatorService")
public class IntimatorImpl implements Intimator {

	@Override
	public void intimateMember(Long mobileNo, String text) {
		// TODO Auto-generated method stub
		System.out.println("sending message with mobile no. " + mobileNo
				+ " and text as" + text);
	}

	@Override
	public void intimateOwner(EmailBean emailBean, JavaMailSender mailSenderImpl) {

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
			mimeMessageHelper.addAttachment(emailBean.getEmailAttachment()
					.getFilename(), emailBean.getEmailAttachment());
			mailSenderImpl.send(mimeMessage);
			System.out.println("Sent successfully...!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
