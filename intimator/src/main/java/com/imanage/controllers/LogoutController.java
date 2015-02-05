package com.imanage.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.LoginModel;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String redirectToLogin(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("login",new LoginModel());
		model.addAttribute("menumode", "L");
		return "login";
	}
}
