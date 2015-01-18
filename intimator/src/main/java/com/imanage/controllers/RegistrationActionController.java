package com.imanage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;

@Controller
@RequestMapping("/registerAction")
public class RegistrationActionController {
	
	@Autowired
	ClubRegistrationService clubRegService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@RequestMapping(method = RequestMethod.POST)
	public String registrationHandler(Model model, @ModelAttribute("clubDetails")
     ClubDetails clubDetails){
		clubDetails.setPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), ""));
		clubDetails.setRoleId(1);
		clubDetails.setNewPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), ""));
		String message = "Sorry, Registeration failed";
		System.out.println("clubDetails: "+clubDetails);
		try {
			ClubDetails existingClubDetails = clubRegService.findByUserName(clubDetails.getUsername());
			if(existingClubDetails == null){
				clubRegService.save(clubDetails);
				message = "Successfully registered";
			}else{
				message = "User Name already taken registered";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message",message);
	    return "register";
	}
}
