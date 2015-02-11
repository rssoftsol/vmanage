package com.imanage.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.imanage.dao.security.AccessDao;
import com.imanage.models.EmailConfigBean;
import com.imanage.security.ForgotPasswordManager;
import com.imanage.util.Utility;


@EnableScheduling
@Controller
public class ScheduledTaskController {
	
	Logger logger = Logger.getLogger(ScheduledTaskController.class.getName());

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private JavaMailSenderImpl mailSenderImpl;

	@Autowired
	EmailConfigBean emailConfigBean;
	
    //@Scheduled(cron="*/2 * * * * SAT-SUN")
	//@Scheduled(fixedDelay=5000)
    public void reportCurrentTime() {
    	
    	String uId = Utility.generateAlphanumericUId();
		String emailText = emailConfigBean.getEmailText1() + emailConfigBean.getWebsiteLink() + 
				emailConfigBean.getEmailText2() + emailConfigBean.getResetPasswordLink() + uId + 
				emailConfigBean.getEmailText3() + emailConfigBean.getResetPasswordDisplayLink()+ uId +
				emailConfigBean.getEmailText4();

		MimeMessage mimeMessage = mailSenderImpl.createMimeMessage();
		
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, true);
			mimeMessageHelper.setFrom(emailConfigBean.getEmailFrom());
			mimeMessageHelper.setTo("chandrashekhar.m.patil@gmail.com");
			mimeMessageHelper
					.setSubject(emailConfigBean.getEmailSubject());
			mimeMessageHelper.setText(emailText, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		logger.info("sending email");
		mailSenderImpl.send(mimeMessage);
	
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
