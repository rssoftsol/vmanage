package com.imanage.services.register;

import com.imanage.models.ClubDetails;


public interface ClubRegistrationDao {
	void save(ClubDetails clubDetails);
	
	void update(ClubDetails clubDetails);
	
	void delete(ClubDetails clubDetails);
	
	ClubDetails findBySpecific(String value);

}
