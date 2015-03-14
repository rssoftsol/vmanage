package com.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.imanage.intimate.impl.ExpiryIntimator;
import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;

@ComponentScan
@EnableScheduling
@Component
public class ScheduledTaskController {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired 
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	ExpiryIntimator intimator;
	
	@PostConstruct
	public void dosomething(){
		System.out.println("i am initalized");
	}
	
    /*@Scheduled(cron="0 46 11 * * *?")*/
	@Scheduled(cron="0 0 12 * * *")
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        List<ClubDetails> clubs = clubRegistrationService.findAll();
        for(ClubDetails clubDetails : clubs){
        	try {
        		if("N".equalsIgnoreCase(clubDetails.getIsAccountative())) continue;
        		//put logger here
        		intimator.intimate(clubDetails);	
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
        }
    }
}
