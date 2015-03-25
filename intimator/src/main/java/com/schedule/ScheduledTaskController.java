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
	
    @Scheduled(cron="0 30 9 * * *")
	//@Scheduled(fixedRate=500000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        List<ClubDetails> clubs = clubRegistrationService.findAll();
        for(ClubDetails clubDetails : clubs){
        	try {
        		if("N".equalsIgnoreCase(clubDetails.getIsAccountative())) continue;
        		//put logger here
        		System.out.println("Active club found:"+clubDetails);
        		intimator.intimate(clubDetails);	
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
        }
    }
}
