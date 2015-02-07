package com.imanage.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registrationHandler(Model model, @ModelAttribute("command")
     @Valid ClubDetails clubDetails, BindingResult result){
		if(result.hasErrors()){
			ModelAndView mav = new ModelAndView("register");
			clubDetails.setPassword("");
			mav.addObject("command", clubDetails);
			return mav;
		}
		clubDetails.setPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), clubDetails.getUsername()));
		clubDetails.setRoleId(1);
		clubDetails.setNewPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), clubDetails.getUsername()));
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
	    return new ModelAndView("register");
	}
}
