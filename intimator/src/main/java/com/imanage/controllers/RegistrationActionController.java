package com.imanage.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;
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

import com.imanage.Session;
import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;

@Controller
@RequestMapping("/myprofile")
public class RegistrationActionController {
	
	@Autowired
	ClubRegistrationService clubRegService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/create.htm")
	public String redirectToLogin(Model model) {
		model.addAttribute("clubDetails", new ClubDetails());
		model.addAttribute("command", new ClubDetails());
		model.addAttribute("menumode", "R");
		return "register";
	}
	
	@RequestMapping(value="/createAction.htm", method = RequestMethod.POST)
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
				message = "User Name already taken";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message",message);
	    return new ModelAndView("register");
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView myProfilePage(HttpSession session) {
		ModelAndView mav = new ModelAndView("myprofile");
		ClubDetails clubDetails = clubRegService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		mav.addObject("command", clubDetails);
		return mav;
	}
	
	@RequestMapping(value="/editAction", method = RequestMethod.POST)
	public ModelAndView myProfileAction(@ModelAttribute("command") 
    @Valid ClubDetails clubDetails, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView("myprofile");
		if(result.hasErrors()){
			mav.addObject("command", clubDetails);
			return mav;
		}
		ClubDetails clubDetailstmp = clubRegService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		//clubDetails.setMemberDetails(clubDetailstmp.getMemberDetails());
		clubDetails.setRoleId(1);
		clubRegService.update(clubDetails);
		mav.addObject("command", clubDetails);
		mav.addObject("popupMessage", "Details updated");
		return mav;
	}
	
	@ModelAttribute
	public void addCommonAttribute(Model model, HttpSession session){
		model.addAttribute("user", ((Session)session.getAttribute("session")).getUsername());
		model.addAttribute("date", new java.util.Date().toString());
		model.addAttribute("mainmode", "MYPROFILE");
	}

}
