package com.imanage.services.smscredit;

import com.imanage.models.ESMSCreditBal;

public interface SMSCreditdBalDao {
	void save(ESMSCreditBal esmsDetails);
	
	void update(ESMSCreditBal esmsDetails);
	
	void delete(ESMSCreditBal esmsDetails);
	
	ESMSCreditBal getSMSCreditBalance(String clubid);

}
