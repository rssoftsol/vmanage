package com.imanage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;

@Controller
@RequestMapping("/registerAction")
public class RegistrationActionController {
	
	@Autowired
	ClubRegistrationService clubRegService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registrationHandler(@ModelAttribute("command")
     ClubDetails clubDetails){
		String message = "Sorry, Registeration failed";
		try {
			ClubDetails existingClubDetails = clubRegService.findByUserName(clubDetails.getUsername());
			if(existingClubDetails == null){
				clubRegService.save(clubDetails);
				message = "Successfully registered";
			}else{
				message = "User Name already taken registered";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ModelAndView("register", "message",message);
	}
}
