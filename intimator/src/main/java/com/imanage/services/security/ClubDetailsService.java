package com.imanage.services.security;

import com.imanage.models.ClubDetails;

public interface ClubDetailsService {

	public ClubDetails findByUserName(String userName); 
}
