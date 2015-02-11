package com.imanage.util.crud;

import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;

public interface CRUDHandler {

	public ModelAndView processCRUDRequest(String mode, MemberDetails memberDetails, 
			MemberRegistrationService memberRegistrationService);
	
}
