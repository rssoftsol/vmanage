package com.imanage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imanage.models.ClubDetails;
import com.imanage.models.LoginModel;
import com.imanage.services.register.ClubRegistrationService;

@Controller
@RequestMapping("/loginAction")
public class LoginActionController {
	
	@Autowired
	ClubRegistrationService clubRegService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String loginHandler(Model model, @ModelAttribute("loginModel")
	LoginModel loginModel){

		String message = "Error";
		String successPage = "";
		try {
			ClubDetails existingClubDetails = clubRegService.findByUserName(loginModel.getUsername());
			if(existingClubDetails!=null && existingClubDetails.getPassword().equals(loginModel.getPassword())){
				message = "Welcome "+existingClubDetails.getUsername();
				model.addAttribute("message", message);
				successPage = "membersdetail";
	        }else{
	        	message = "Invalid User Name/Password";
	        	successPage = "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successPage;
	}
}
