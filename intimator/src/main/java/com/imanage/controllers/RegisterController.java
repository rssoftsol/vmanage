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
@RequestMapping("/registration.htm")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public String redirectToLogin(Model model) {
		model.addAttribute("clubDetails", new ClubDetails());
		return "register";
	}
}
