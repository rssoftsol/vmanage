package com.imanage.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.util.DateUtility;

@Controller
public class RegistrationActionController {
	
	@Autowired
	ClubRegistrationService clubRegService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/create.htm")
	public String subscribe(Model model) {
		model.addAttribute("clubDetails", new ClubDetails());
		model.addAttribute("command", new ClubDetails());
		model.addAttribute("menumode", "R");
		return "register";
	}
	
	@RequestMapping(value="/createAction.htm", method = RequestMethod.POST)
	public String registrationHandler(Model model, @ModelAttribute("command")
     @Valid ClubDetails clubDetails, BindingResult result, RedirectAttributes attributes){
		String view = "";
		if(result.hasErrors()){
			view = "register";
			clubDetails.setPassword("");
			model.addAttribute("command", clubDetails);
			model.addAttribute("menumode", "R");
			return view;
		}
		clubDetails.setPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), clubDetails.getUsername().toLowerCase()));
		clubDetails.setRoleId(1);
		clubDetails.setIsAccountative("Y");
		clubDetails.setNewPassword(passwordEncoder.encodePassword(clubDetails.getPassword(), clubDetails.getUsername().toLowerCase()));
		clubDetails.setSmsText("Dear Member, your "+clubDetails.getClubname()+" membership is getting expired today. Kindly renew");
		String message = "Sorry, Registeration failed";
		System.out.println("clubDetails: "+clubDetails);
		try {
			ClubDetails existingClubDetails = clubRegService.findByUserName(clubDetails.getUsername());
			if(existingClubDetails == null){
				clubDetails.setCreatedDate(DateUtility.getSQLCurrentTime());
				//put logger here
				clubRegService.save(clubDetails);
				message = "Successfully registered";
				attributes.addFlashAttribute("popupInfoMessage",message);
				clubDetails = new ClubDetails();
			}else{
				message = "User Name already taken";
				model.addAttribute("popupErrorMessage",message);
				model.addAttribute("menumode", "R");
				return "register";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("menumode", "R");
	    return "redirect:/create.htm";//new ModelAndView("register", "command", clubDetails);
	}
	
	@RequestMapping(value="/myprofile/edit", method = RequestMethod.GET)
	public ModelAndView myProfilePage(Model model, RedirectAttributes attributes) {
		ModelAndView mav = new ModelAndView("myprofile");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
		mav.addObject("command", clubDetails);
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("user", authentication.getName());
		return mav;
	}
	
	@RequestMapping(value="/myprofile/deActivate", method = RequestMethod.POST)
	public ModelAndView deActivateAccount() {
		ModelAndView mav = new ModelAndView("myprofile");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String message = "";
		ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
		if("Y".equalsIgnoreCase(clubDetails.getIsAccountative())){
			clubDetails.setIsAccountative("N");
			clubDetails.setModifiedDate(DateUtility.getSQLCurrentTime());
			clubRegService.update(clubDetails);
			message = "Account Deactivated";
			mav.addObject("popupInfoMessage", message);
		}else{
			message = "Account is already Deactivate";
			mav.addObject("popupErrorMessage", message);
		}
		
		mav.addObject("user", authentication.getName());
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("command", clubDetails);
		return mav;
	}
	
	@RequestMapping(value="/myprofile/reActivate", method = RequestMethod.POST)
	public ModelAndView reActivateAccount() {
		ModelAndView mav = new ModelAndView("myprofile");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String message = "";
		ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
		if("Y".equalsIgnoreCase(clubDetails.getIsAccountative())){
			message = "Account is already Active";
			mav.addObject("popupErrorMessage", message);
		}else{
			clubDetails.setIsAccountative("Y");
			clubDetails.setModifiedDate(DateUtility.getSQLCurrentTime());
			clubRegService.update(clubDetails);
			message = "Account Activated";
			mav.addObject("popupInfoMessage", message);
		}
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("user", authentication.getName());
		mav.addObject("command", clubDetails);
		return mav;
	}
	
	@RequestMapping(value="/myprofile/editAction.htm", method = RequestMethod.POST)
	public String myProfileAction(@ModelAttribute("command") 
    @Valid ClubDetails clubDetails, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String mav = "myprofile";
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if(result.hasErrors()){
			model.addAttribute("command", clubDetails);
			return mav;
		}
		ClubDetails clubDetailstmp = clubRegService.findByUserName(authentication.getName());
		//clubDetails.setMemberDetails(clubDetailstmp.getMemberDetails());
		clubDetails.setRoleId(1);
		clubDetails.setCreatedDate(clubDetailstmp.getCreatedDate());
		clubDetails.setModifiedDate(DateUtility.getSQLCurrentTime());
		clubDetails.setIsAccountative(clubDetailstmp.getIsAccountative());
		//put logger here
		clubRegService.update(clubDetails);
		model.addAttribute("command", clubDetails);
		redirectAttributes.addFlashAttribute("popupInfoMessage", "Details updated");
		model.addAttribute("user", authentication.getName());
		model.addAttribute("isActive", clubDetails.getIsAccountative());
		return "redirect:/myprofile/edit";
	}
	
	@ModelAttribute
	public void addCommonAttribute(Model model){
		model.addAttribute("date", new java.util.Date().toString());
		model.addAttribute("mainmode", "MYPROFILE");
	}
	
	 @ExceptionHandler(Exception.class)
		public ModelAndView handleAllException(Exception ex) {
	    	ex.printStackTrace();
			ModelAndView model = new ModelAndView("error/exception_error");
			return model;
	 }

}
