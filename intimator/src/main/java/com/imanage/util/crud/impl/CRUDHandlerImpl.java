package com.imanage.util.crud.impl;

import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;
import com.imanage.util.DateUtility;
import com.imanage.util.MemberModeEnum;
import com.imanage.util.crud.CRUDHandler;

public class CRUDHandlerImpl implements CRUDHandler {
	
	MemberRegistrationService memberRegistrationService;
	public String handleAddRequest(MemberDetails memberDetails, MemberDetails existingMemberDetails) {
		String message = "Member "+memberDetails.getMemid()+" already exist";
		if(existingMemberDetails == null){
			memberDetails.setCreatedDate(DateUtility.getSQLCurrentTime());
			memberRegistrationService.save(memberDetails);
			return "Member Added successfully";
		}
		return message;
	}

	public String handleModifyRequest(MemberDetails memberDetails, MemberDetails existingMemberDetails) {
		String message = "Member "+memberDetails.getMemid()+" doesn't exist";
		if(existingMemberDetails != null){
			memberDetails.setCreatedDate(existingMemberDetails.getCreatedDate());
			memberDetails.setModifiedDate(DateUtility.getSQLCurrentTime());
			memberRegistrationService.update(memberDetails);
			return "Member record updated successfully";
		}
		return message;
	}

	public String handleDeleteRequest(MemberDetails memberDetails, MemberDetails existingMemberDetails) {
		String message = "Member "+memberDetails.getMemid()+" doesn't exist";
		if(existingMemberDetails != null){
			memberRegistrationService.delete(memberDetails);
			return "Member record deleted successfully";
		}
		return message;
	}

	public void handleBrowseRequest(MemberDetails memberDetails) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ModelAndView processCRUDRequest(String mode,
			MemberDetails memberDetails, MemberRegistrationService memberRegistrationService) {
		MemberModeEnum modeEnum = MemberModeEnum.getMemberModeEnum(mode);
		MemberDetails existingMemberDetails = memberRegistrationService.findByMemid(memberDetails.getMemid());
		this.memberRegistrationService = memberRegistrationService;
		String message = "";
		//put logger here
		switch (modeEnum) {
		case ADD:
			message = handleAddRequest(memberDetails, existingMemberDetails);
			break;
		case MODIFY:
			message = handleModifyRequest(memberDetails, existingMemberDetails);
			break;
		case DELETE:
			message = handleDeleteRequest(memberDetails, existingMemberDetails);
			break;
		default:
			//To Do
			break;
		}
		ModelAndView mav = new ModelAndView("member", "popupInfoMessage", message);
		mav.addObject("commandd", new MemberDetails());
		mav.addObject("mode", mode);
		return mav;
	}
	
}
