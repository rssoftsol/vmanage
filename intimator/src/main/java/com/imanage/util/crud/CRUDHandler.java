package com.imanage.util.crud;

import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;

public interface CRUDHandler {
	/*void handleAddRequest(MemberDetails memberDetails);
	void handleModifyRequest(MemberDetails memberDetails);
	void handleDeleteRequest(MemberDetails memberDetails);
	void handleBrowseRequest(MemberDetails memberDetails);*/
	public ModelAndView processCRUDRequest(String mode, MemberDetails memberDetails, 
			MemberRegistrationService memberRegistrationService);
	
}
