package com.imanage.services.security;

import com.imanage.models.ClubDetails;
import com.imanage.models.security.PasswordResetReqBean;



public interface ResetPasswordService {

	public PasswordResetReqBean checkForValidLink(String uId);
	
	public int resetPassword(ClubDetails clubDetails,String uId);
	
	public boolean validateOldPassword(ClubDetails clubDetails);
	
	public int updatePassword(ClubDetails clubDetails);
	
}
