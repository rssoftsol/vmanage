package com.imanage.services.security;

import com.imanage.models.security.ForgotPasswordBean;


public interface ForgotPasswordService {

	public void sendMail(ForgotPasswordBean forgotPasswordBean);
	
	public String checkEmailId(String toEmailId);
	
}
