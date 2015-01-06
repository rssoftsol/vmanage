package com.imanage.security;

import java.sql.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.imanage.dao.security.AccessDao;
import com.imanage.models.EmailConfigBean;
import com.imanage.models.security.ForgotPasswordBean;
import com.imanage.models.security.PasswordResetReqBean;
import com.imanage.services.security.ForgotPasswordService;
import com.imanage.util.Utility;

@Service("forgotPasswordService")
public class ForgotPasswordManager implements ForgotPasswordService {

	Logger logger = Logger.getLogger(ForgotPasswordManager.class.getName());

	@Autowired
	private JavaMailSenderImpl mailSenderImpl;

	@Autowired
	private AccessDao accessDao;
	
	@Autowired
	EmailConfigBean emailConfigBean;

	public void sendMail(ForgotPasswordBean forgotPasswordBean) {
 
		String uId = Utility.generateAlphanumericUId();
		Date time = new Date(new java.util.Date().getTime());
		String loginName = "rahul";//checkEmailId(forgotPasswordBean.getToEmailId());
		logger.info("Time : "+time.getTime());
		PasswordResetReqBean passwordResetReqBean = new PasswordResetReqBean();
		passwordResetReqBean.setuId(uId);
		passwordResetReqBean.setUserName(loginName);
		passwordResetReqBean.setTime(time);
		logger.info("LoginName : "+loginName);
		int updatedRows = accessDao.insertResetPasswordEntry(passwordResetReqBean);
		logger.info("updatedRows : "+updatedRows); 
		if(updatedRows>0){
			String emailText = emailConfigBean.getEmailText1() + emailConfigBean.getWebsiteLink() + 
					emailConfigBean.getEmailText2() + emailConfigBean.getResetPasswordLink() + uId + 
					emailConfigBean.getEmailText3() + emailConfigBean.getResetPasswordDisplayLink()+ uId +
					emailConfigBean.getEmailText4();

			MimeMessage mimeMessage = mailSenderImpl.createMimeMessage();
			
			try {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
						mimeMessage, true);
				mimeMessageHelper.setFrom(emailConfigBean.getEmailFrom());
				mimeMessageHelper.setTo(forgotPasswordBean.getToEmailId());
				mimeMessageHelper
						.setSubject(emailConfigBean.getEmailSubject());
				mimeMessageHelper.setText(emailText, true);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			logger.info("sending email");
			mailSenderImpl.send(mimeMessage);
		}
	}

	@Override
	public String checkEmailId(String toEmailId) {
		String loginName = accessDao.checkEmailId(toEmailId);
		return loginName;
	}

}