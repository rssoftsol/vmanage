package com.imanage.services.smssender;

import java.util.List;

import com.imanage.models.ESMSSender;

public interface SMSSenderDao {
	void save(ESMSSender esmsDetails);
	
	void update(ESMSSender esmsDetails);
	
	void delete(ESMSSender esmsDetails);
	
	List<ESMSSender> findSMSSender(String clubid);

}
