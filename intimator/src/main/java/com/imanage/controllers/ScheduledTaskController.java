package com.imanage.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.imanage.PDFGen;
import com.imanage.intimate.EmailBean;
import com.imanage.intimate.Intimator;
import com.imanage.models.ClubDetails;
import com.imanage.models.EmailConfigBean;
import com.imanage.services.register.ClubRegistrationService;

@EnableScheduling
@Controller
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
        FileSystemResource file = null;
        List<ClubDetails> clubs = clubRegistrationService.findAll();
        for(ClubDetails clubDetails : clubs){
        	try {
        		if("N".equalsIgnoreCase(clubDetails.getIsAccountative())) continue;
        		file = PDFGen.getPDFDoc(clubDetails.getMemberDetails(),
        				clubDetails.getClubname().replace(" ", ""), intimator);
        		if(null!=file){
        			EmailBean emailBean = new EmailBean().withFromEmailId(emailConfigBean.getEmailFrom()).
            				withToEmailId(clubDetails.getEmail()).withMailSubject(emailConfigBean.getPdfEmailSubject()).
            				withEmailAttachment(file).withMailBody(emailConfigBean.getEmailPDFInfoText());
        			
        			intimator.intimateOwner(emailBean, mailSenderImpl);
        		}
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
        }
    }
}
