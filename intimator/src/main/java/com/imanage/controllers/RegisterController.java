package com.imanage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.ClubDetails;

@Controller
@RequestMapping("/")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET,value="/registration.htm")
	public String redirectToLogin(Model model) {
		model.addAttribute("clubDetails", new ClubDetails());
		model.addAttribute("menumode", "R");
		return "register";
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/aboutus")
	public String redirectToAboutUs(Model model) {
		model.addAttribute("menumode", "A");
		return "aboutus";
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/contactus")
	public String redirectToContactUs(Model model) {
		model.addAttribute("menumode", "C");
		return "contactus";
	}
}
