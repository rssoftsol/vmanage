package com.imanage.services.smssender;

import java.util.List;

import com.imanage.models.ESMSSender;

public interface SMSSenderService {
	void save(ESMSSender esmsSender);
	
	void update(ESMSSender esmsSender);
	
	void delete(ESMSSender esmsSender);
	
	List<ESMSSender> findSMSSender(String clubid);
}
