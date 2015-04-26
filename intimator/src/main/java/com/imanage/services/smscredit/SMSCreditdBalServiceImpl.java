package com.imanage.services.smscredit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imanage.models.ESMSCreditBal;

@Service("smsCreditBalService")
public class SMSCreditdBalServiceImpl implements SMSCreditdBalService{
	
	@Autowired
	SMSCreditdBalDao smsCreditdBalDao;
	
	public void setClubRegistrationDao(SMSCreditdBalDao smsCreditdBalDao){
		this.smsCreditdBalDao = smsCreditdBalDao;
	}
	
	@Override
	public void save(ESMSCreditBal smsCreditBal) {
		smsCreditdBalDao.save(smsCreditBal);
	}

	@Override
	public void update(ESMSCreditBal smsCreditBal) {
		smsCreditdBalDao.update(smsCreditBal);
		
	}

	@Override
	public void delete(ESMSCreditBal smsCreditBal) {
		smsCreditdBalDao.delete(smsCreditBal);
	}

	@Override
	public ESMSCreditBal getSmsCreditBal(String clubid) {
		return smsCreditdBalDao.getSMSCreditBalance(clubid);
	}

}
