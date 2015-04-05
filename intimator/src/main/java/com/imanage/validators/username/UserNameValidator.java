package com.imanage.validators.username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imanage.dao.security.AccessDao;

@Component
public class UserNameValidator implements ConstraintValidator<IsUserNameValid, String> {

	@Autowired
	private AccessDao accessDao;
	
	@Override
	public void initialize(IsUserNameValid userNameValid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext ctx) {
		// TODO Auto-generated method stub
		if(userName == null) return true;
		if(accessDao.checkEmailId(userName) != null){
			return true;
		}
		return false;
	}
	
	
}
