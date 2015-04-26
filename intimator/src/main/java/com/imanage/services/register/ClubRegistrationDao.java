package com.imanage.services.register;

import java.util.List;

import com.imanage.models.ClubDetails;


public interface ClubRegistrationDao {
	void save(ClubDetails clubDetails);
	
	void update(ClubDetails clubDetails);
	
	void delete(ClubDetails clubDetails);
	
	ClubDetails findBySpecific(String value);
	
	List<ClubDetails> findAll();
	
	ClubDetails findByClubId(Integer clubId);

}
