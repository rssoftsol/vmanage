package com.imanage.dao.security;

import com.imanage.models.ClubDetails;
import com.imanage.models.security.PasswordResetReqBean;


public interface AccessDao {
	public String checkEmailId(String toEmailId);
	
	public int insertResetPasswordEntry(PasswordResetReqBean passwordResetReqBean);
	
	public PasswordResetReqBean checkForValidLink(String uId);
	
	public int resetPassword(ClubDetails clubDetails,String uId);
	
	public boolean validateOldPassword(ClubDetails clubDetails);
	
	public int updatePassword(ClubDetails clubDetails);
}
