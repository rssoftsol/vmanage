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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imanage.Session;
import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;

@Controller
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
			model.addAttribute("menumode", "R");
			return mav;
		}
		clubDetails.setPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), clubDetails.getUsername()));
		clubDetails.setRoleId(1);
		clubDetails.setIsAccountative("Y");
		clubDetails.setNewPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), clubDetails.getUsername()));
		String message = "Sorry, Registeration failed";
		System.out.println("clubDetails: "+clubDetails);
		try {
			ClubDetails existingClubDetails = clubRegService.findByUserName(clubDetails.getUsername());
			if(existingClubDetails == null){
				clubRegService.save(clubDetails);
				message = "Successfully registered";
				model.addAttribute("popupInfoMessage",message);
				clubDetails = new ClubDetails();
			}else{
				message = "User Name already taken";
				model.addAttribute("popupErrorMessage",message);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("menumode", "R");
	    return new ModelAndView("register", "command", clubDetails);
	}
	
	@RequestMapping(value="/myprofile/edit", method = RequestMethod.GET)
	public ModelAndView myProfilePage(HttpSession session, Model model, RedirectAttributes attributes) {
		ModelAndView mav = new ModelAndView("myprofile");
		ClubDetails clubDetails = clubRegService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		mav.addObject("command", clubDetails);
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("user", ((Session)session.getAttribute("session")).getUsername());
		return mav;
	}
	
	@RequestMapping(value="/myprofile/deActivate", method = RequestMethod.POST)
	public ModelAndView deActivateAccount(HttpSession session) {
		ModelAndView mav = new ModelAndView("myprofile");
		String message = "";
		ClubDetails clubDetails = clubRegService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		if("Y".equalsIgnoreCase(clubDetails.getIsAccountative())){
			clubDetails.setIsAccountative("N");
			clubRegService.update(clubDetails);
			message = "Account Deactivated";
			mav.addObject("popupInfoMessage", message);
		}else{
			message = "Account is already Deactivate";
			mav.addObject("popupErrorMessage", message);
		}
		
		mav.addObject("user", ((Session)session.getAttribute("session")).getUsername());
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("command", clubDetails);
		return mav;
	}
	
	@RequestMapping(value="/myprofile/reActivate", method = RequestMethod.POST)
	public ModelAndView reActivateAccount(HttpSession session) {
		ModelAndView mav = new ModelAndView("myprofile");
		String message = "";
		ClubDetails clubDetails = clubRegService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		if("Y".equalsIgnoreCase(clubDetails.getIsAccountative())){
			message = "Account is already Active";
			mav.addObject("popupErrorMessage", message);
		}else{
			clubDetails.setIsAccountative("Y");
			clubRegService.update(clubDetails);
			message = "Account Reactivated";
			mav.addObject("popupInfoMessage", message);
		}
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("user", ((Session)session.getAttribute("session")).getUsername());
		mav.addObject("command", clubDetails);
		return mav;
	}
	
	@RequestMapping(value="/myprofile/editAction.htm", method = RequestMethod.POST)
	public ModelAndView myProfileAction(@ModelAttribute("command") 
    @Valid ClubDetails clubDetails, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView("myprofile");
		if(result.hasErrors()){
			mav.addObject("command", clubDetails);
			return mav;
		}
		//clubDetails.setMemberDetails(clubDetailstmp.getMemberDetails());
		clubDetails.setRoleId(1);
		clubRegService.update(clubDetails);
		mav.addObject("command", clubDetails);
		mav.addObject("popupInfoMessage", "Details updated");
		mav.addObject("user", ((Session)session.getAttribute("session")).getUsername());
		mav.addObject("isActive", clubDetails.getIsAccountative());
		return mav;
	}
	
	@ModelAttribute
	public void addCommonAttribute(Model model, HttpSession session){
		model.addAttribute("date", new java.util.Date().toString());
		model.addAttribute("mainmode", "MYPROFILE");
	}

}
