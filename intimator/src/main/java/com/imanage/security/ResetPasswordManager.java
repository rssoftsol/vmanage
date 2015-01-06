package com.imanage.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.imanage.dao.security.AccessDao;
import com.imanage.models.ClubDetails;
import com.imanage.models.security.PasswordResetReqBean;
import com.imanage.services.security.ResetPasswordService;

@SuppressWarnings("deprecation")
@Service("resetPasswordService")
public class ResetPasswordManager implements ResetPasswordService {

	Logger logger = Logger.getLogger(ResetPasswordManager.class.getName());

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccessDao accessDao;
	
	@Override
	public int resetPassword(ClubDetails clubDetails,String uId) {
		String password = clubDetails.getPassword();
		String encodedPassword = passwordEncoder.encodePassword(password, "");
		clubDetails.setPassword(encodedPassword);
		int rowsUpdated = accessDao.resetPassword(clubDetails,uId);
		return rowsUpdated;
	}

	@Override
	public PasswordResetReqBean checkForValidLink(String uId) {
		PasswordResetReqBean resetReqBean = accessDao.checkForValidLink(uId);
		return resetReqBean;
	}

	@Override
	public boolean validateOldPassword(ClubDetails clubDetails) {
		String password = clubDetails.getPassword();
		String encodedPassword = passwordEncoder.encodePassword(password, "");
		clubDetails.setPassword(encodedPassword);
		boolean success = accessDao.validateOldPassword(clubDetails);
		return success;
	}

	@Override
	public int updatePassword(ClubDetails clubDetails) {
		String password = clubDetails.getNewPassword();
		String encodedPassword = passwordEncoder.encodePassword(password, "");
		clubDetails.setNewPassword(encodedPassword);
		int updatedRows = accessDao.updatePassword(clubDetails);
		return updatedRows;
	}

}