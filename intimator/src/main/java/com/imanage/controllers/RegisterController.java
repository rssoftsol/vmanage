package com.imanage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.ClubDetails;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView redirectToLogin() {
		ModelAndView mav = new ModelAndView("register","command",new ClubDetails());
		//mav.addObject("sClubDetails", new ClubDetails());
		return mav;
	}
}
