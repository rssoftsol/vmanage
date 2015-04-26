package com.imanage.services.smssender;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imanage.models.ESMSSender;

@Service("smsSenderService")
public class SMSSenderServiceImpl implements SMSSenderService{
	
	@Autowired
	SMSSenderDao smsSenderDao;
	
	public void setClubRegistrationDao(SMSSenderDao smsSenderDao){
		this.smsSenderDao = smsSenderDao;
	}
	
	@Override
	public void save(ESMSSender smsSender) {
		smsSenderDao.save(smsSender);
	}

	@Override
	public void update(ESMSSender smsSender) {
		smsSenderDao.update(smsSender);
		
	}

	@Override
	public void delete(ESMSSender smsSender) {
		smsSenderDao.delete(smsSender);
	}

	@Override
	public List<ESMSSender> findSMSSender(String clubid) {
		return smsSenderDao.findSMSSender(clubid);
	}

}
