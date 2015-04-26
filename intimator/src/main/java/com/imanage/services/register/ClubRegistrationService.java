package com.imanage.services.register;

import java.util.List;

import com.imanage.models.ClubDetails;


public interface ClubRegistrationService {
	void save(ClubDetails clubDetails);
	
	void update(ClubDetails clubDetails);
	
	void delete(ClubDetails clubDetails);
	
	ClubDetails findByUserName(String value);
	
	ClubDetails findByClubId(Integer clubId);
	
	List<ClubDetails> findAll();
}
