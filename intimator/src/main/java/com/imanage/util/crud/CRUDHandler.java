package com.imanage.util.crud;

import java.util.Set;

import com.imanage.models.MemberDetails;

public interface CRUDHandler {

	public String processCRUDRequest(String mode, MemberDetails memberDetails, 
			Set<MemberDetails> existingMemberDetails);
	
}
