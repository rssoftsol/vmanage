package com.imanage.util.crud;

import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;

public interface CRUDHandler {

	public String processCRUDRequest(String mode, MemberDetails memberDetails, 
			MemberRegistrationService memberRegistrationService);
	
}
