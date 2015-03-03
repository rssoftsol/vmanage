package com.imanage.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.imanage.intimate.Intimator;
import com.imanage.intimate.impl.ExpiryIntimator;
import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;

//@EnableScheduling
@Controller
public class ScheduledTaskController {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	Intimator intimator;
	
    //@Scheduled(fixedRate = 500000,initialDelay=5000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        List<ClubDetails> clubs = clubRegistrationService.findAll();
        for(ClubDetails clubDetails : clubs){
        	try {
        		if("N".equalsIgnoreCase(clubDetails.getIsAccountative())) continue;
        		new ExpiryIntimator().intimate(clubDetails);	
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
        }
    }
}
