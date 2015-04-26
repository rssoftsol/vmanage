package com.imanage.services.smscredit;

import com.imanage.models.ESMSCreditBal;

public interface SMSCreditdBalService {
	void save(ESMSCreditBal smsCreditBal);
	
	void update(ESMSCreditBal smsCreditBal);
	
	void delete(ESMSCreditBal smsCreditBal);
	
	ESMSCreditBal getSmsCreditBal(String clubid);
}
