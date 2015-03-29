package com.imanage.util.crud.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;
import com.imanage.util.DateUtility;
import com.imanage.util.MemberModeEnum;
import com.imanage.util.crud.CRUDHandler;

@Component
public class CRUDHandlerImpl implements CRUDHandler {
	
	@Autowired
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
	
	private MemberDetails getMatchingMemberFromSet(Set<MemberDetails> existingMembers, MemberDetails memberDetails){
		MemberDetails tmpmemberDetails = null;
		for (Iterator<MemberDetails> iterator = existingMembers.iterator(); iterator.hasNext();) {
			tmpmemberDetails = iterator.next();
			if(tmpmemberDetails.getMemid().equalsIgnoreCase(memberDetails.getMemid())){
				break;
			}
			tmpmemberDetails = null;
		}
		return tmpmemberDetails;
	}
	
	@Override
	public String processCRUDRequest(String mode,
			MemberDetails memberDetails, Set<MemberDetails> existingMembers) {
		MemberModeEnum modeEnum = MemberModeEnum.getMemberModeEnum(mode);
		MemberDetails existingMemberDetails = getMatchingMemberFromSet(existingMembers, memberDetails);
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
		
		return message;
	}
	
}
