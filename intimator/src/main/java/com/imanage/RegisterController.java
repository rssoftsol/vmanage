package com.imanage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public String redirectToLogin(ModelMap model) {
		return "register";
	}
}
