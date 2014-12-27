package com.imanage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.ClubDetails;
import com.imanage.models.LoginModel;
import com.imanage.services.register.ClubRegistrationService;

@Controller
@RequestMapping("/loginAction")
public class LoginActionController {
	
	@Autowired
	ClubRegistrationService clubRegService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loginHandler(@ModelAttribute("command")
	LoginModel loginModel){

		String message = "Error";
		try {
			ClubDetails existingClubDetails = clubRegService.findByUserName(loginModel.getUsername());
			if(existingClubDetails!=null && existingClubDetails.getPassword().equals(loginModel.getPassword())){
				message = "Welcome "+existingClubDetails.getUsername();
	            return new ModelAndView("membersdetail", "message", message);
	        }else{
	        	message = "Invalid User Name/Password";
	        	return new ModelAndView("login", "message",message);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("login", "message",message);
	}
}
