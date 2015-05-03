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

import com.imanage.intimate.impl.NotifyingService;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.util.DateUtility;
import com.imanage.util.sms.SmsCallGet;

@ComponentScan
@EnableScheduling
@Component
public class ScheduledTaskController {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired 
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	NotifyingService notifyingService;
	
	@Autowired
	SmsCallGet smsCallGet;
	
	@PostConstruct
	public void dosomething(){
		System.out.println("i am initalized");
	}
	
    @Scheduled(cron="0 30 10 * * *")
	//@Scheduled(fixedRate=500000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
        List<ClubDetails> clubs = clubRegistrationService.findAll();
        for(ClubDetails clubDetails : clubs){
        	try {
        		if("N".equalsIgnoreCase(clubDetails.getIsAccountative())) continue;
        		//put logger here
        		int count = 0;
        		for(MemberDetails memberDetails : clubDetails.getMemberDetails()){
        			if(memberDetails.getExpirydate().getTime() == DateUtility.getTodaysDate()){
        				count++;
        			}
        		}
        		if(count>clubDetails.getSmsCreditBal().getBalance()){
        			smsCallGet.sendMessage("Scheduled Notifiction failed due to low sms balance. Please refill SMS credits and rerun it from Admin tool. Contact IT(75063386587) in case of any doubt", clubDetails.getPhonenumber().toString());
        			continue;
        		}
        		System.out.println("Active club found:"+clubDetails);
        		notifyingService.setRoute("T");
        		notifyingService.setSenderId(clubDetails.getSmsCreditBal().getSenderId());
        		notifyingService.intimate(clubDetails);	
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
        }
    }
}
