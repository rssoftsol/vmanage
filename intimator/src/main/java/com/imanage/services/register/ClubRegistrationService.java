package com.imanage.services.register;

import com.imanage.models.ClubDetails;


public interface ClubRegistrationService {
	void save(ClubDetails clubDetails);
	
	void update(ClubDetails clubDetails);
	
	void delete(ClubDetails clubDetails);
	
	ClubDetails findByUserName(String value);
}
