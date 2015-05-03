package com.imanage.util.crud.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imanage.intimate.NotificationType;
import com.imanage.intimate.impl.NotifyingService;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;
import com.imanage.util.DateUtility;
import com.imanage.util.MemberModeEnum;
import com.imanage.util.crud.CRUDHandler;

@Component
public class CRUDHandlerImpl implements CRUDHandler {
	
	@Autowired
	MemberRegistrationService memberRegistrationService;
	
	@Autowired
	NotifyingService notifyingService;
	
	public String handleSMSSendRequest(Set<MemberDetails> members, String smsText,
			boolean all, ClubDetails clubDetails) {
		String message = "SMS Queued for sending";
		Set<MemberDetails> sendTo = null;
		NotificationType notificationType = all?NotificationType.OFFER_TO_ALL : NotificationType.OFFER_TO_MEMBERSHIP_EXPIRED;
		if(!all){
			sendTo = new HashSet<MemberDetails>();
			for(MemberDetails details : members){
	        	if(details.getExpirydate().getTime()<new Date().getTime()){
	        		sendTo.add(details);
	        	}
	        }
		}else{
			sendTo = members;
		}
		int bal = clubDetails.getSmsCreditBal().getBalance();
		if(sendTo.size() > bal){
			message = "error-Insufficient Balance";
		}else{
			/*for(MemberDetails details : sendTo){
        		String response = smsCallGet.sendMessage(smsText, details.getPhone().toString());
        		bal--;
	        }
			clubDetails.getSmsCreditBal().setBalance(bal);
			clubRegistrationService.update(clubDetails);*/
			notifyingService.intimate(clubDetails, notificationType, smsText);
		}
		
		return message;
	}
	
	public String handleAddRequest(MemberDetails memberDetails, MemberDetails existingMemberDetails) {
		String message = "error-Member "+memberDetails.getMemid()+" already exist";
		if(existingMemberDetails == null){
			memberDetails.setCreatedDate(DateUtility.getSQLCurrentTime());
			memberRegistrationService.save(memberDetails);
			return "Member Added successfully";
		}
		return message;
	}

	public String handleModifyRequest(MemberDetails memberDetails, MemberDetails existingMemberDetails) {
		String message = "error-Member "+memberDetails.getMemid()+" doesn't exist";
		if(existingMemberDetails != null){
			memberDetails.setCreatedDate(existingMemberDetails.getCreatedDate());
			memberDetails.setModifiedDate(DateUtility.getSQLCurrentTime());
			memberRegistrationService.update(memberDetails);
			return "Member record updated successfully";
		}
		return message;
	}

	public String handleDeleteRequest(MemberDetails memberDetails, MemberDetails existingMemberDetails) {
		String message = "error-Member "+memberDetails.getMemid()+" doesn't exist";
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
