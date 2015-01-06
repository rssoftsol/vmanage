package com.imanage.servicesimpl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imanage.dao.security.ClubDetailsDao;
import com.imanage.models.ClubDetails;
import com.imanage.services.security.ClubDetailsService;

@Service("clubDetailsService")
public class ClubDetailsServiceImpl implements ClubDetailsService{

	@Autowired
	ClubDetailsDao clubDetailsDao;
	
	public ClubDetails findByUserName(String userName){
		ClubDetails clubDetails = clubDetailsDao.checkLoginDetails(userName);
		return clubDetails;
	}
}
