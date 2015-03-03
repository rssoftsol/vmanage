package com.imanage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RegisterController {

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
