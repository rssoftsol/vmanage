package com.imanage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imanage.models.LoginModel;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String redirectToLogin(Model model) {
		model.addAttribute("login",new LoginModel());
		model.addAttribute("menumode", "L");
		return "login";
	}
}
