package com.imanage.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imanage.PDFGen;
import com.imanage.intimate.EmailBean;
import com.imanage.intimate.Intimator;
import com.imanage.models.ClubDetails;
import com.imanage.models.EmailConfigBean;
import com.imanage.models.MemberDetails;
import com.imanage.services.register.ClubRegistrationService;
import com.itextpdf.text.pdf.PdfWriter;

//@EnableScheduling
@Controller
//@RequestMapping("/schedular")
public class ScheduledTaskController {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	private JavaMailSenderImpl mailSenderImpl;
	
	@Autowired
	EmailConfigBean emailConfigBean;
	
	@Autowired
	Intimator intimator;
	
    @Scheduled(fixedRate = 500000,initialDelay=5000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
		File file = null;
        List<ClubDetails> clubs = clubRegistrationService.findAll();
        for(ClubDetails clubDetails : clubs){
        	try {
        		file = PDFGen.getPDFDoc(clubDetails.getMemberDetails(),
        				clubDetails.getClubname().replace(" ", ""), intimator);
        		EmailBean emailBean = new EmailBean().withFromEmailId(emailConfigBean.getEmailFrom()).
        				withToEmailId(clubDetails.getEmail()).withMailSubject(emailConfigBean.getPdfEmailSubject()).
        				withEmailAttachment(file).withMailBody(file == null?emailConfigBean.getEmailPDFNoRecordText():emailConfigBean.getEmailPDFInfoText());
        		intimator.intimateOwner(emailBean, mailSenderImpl);
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//file.delete();
			}
        }
    }
}
