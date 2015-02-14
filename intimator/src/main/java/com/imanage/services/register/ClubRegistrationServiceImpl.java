package com.imanage.services.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imanage.models.ClubDetails;

@Service("clubRegistrationService")
public class ClubRegistrationServiceImpl implements ClubRegistrationService{
	
	@Autowired
	ClubRegistrationDao clubRegistrationDao;
	
	public void setClubRegistrationDao(ClubRegistrationDao clubRegistrationDao){
		this.clubRegistrationDao = clubRegistrationDao;
	}
	
	@Override
	public void save(ClubDetails clubDetails) {
		clubRegistrationDao.save(clubDetails);
	}

	@Override
	public void update(ClubDetails clubDetails) {
		clubRegistrationDao.update(clubDetails);
		
	}

	@Override
	public void delete(ClubDetails clubDetails) {
		clubRegistrationDao.delete(clubDetails);
	}

	@Override
	public ClubDetails findByUserName(String value) {
		return clubRegistrationDao.findBySpecific(value);
	}
	
	@Override
	public List<ClubDetails> findAll() {
		return clubRegistrationDao.findAll();
	}

}
