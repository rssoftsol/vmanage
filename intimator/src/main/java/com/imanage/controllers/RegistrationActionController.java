package com.imanage.controllers;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imanage.models.ClubDetails;
import com.imanage.models.ESMSCreditBal;
import com.imanage.models.ESMSSender;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.services.smssender.SMSSenderService;
import com.imanage.util.DateUtility;

@Controller
public class RegistrationActionController {
	
	@Autowired
	ClubRegistrationService clubRegService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SMSSenderService smsSenderService;
	
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
		String smsText = "Dear Member, your "+clubDetails.getClubname()+" membership is  expired. Kindly renew";
		clubDetails.setSmsText(smsText);
		ESMSCreditBal smsCreditBal = new ESMSCreditBal("PROMOTINAL", 500, "P");
		smsCreditBal.setClubDetails(clubDetails);
		clubDetails.setSmsCreditBal(smsCreditBal);
		Set<ESMSSender> senderList = new HashSet<ESMSSender>();
		ESMSSender smsSender = new ESMSSender("PROMOTIONAL", "P", smsText);
		smsSender.setClubDetails(clubDetails);
		senderList.add(smsSender);
		clubDetails.setSmsSenders(senderList);
		String message = "Sorry, Registeration failed";
		System.out.println("clubDetails: "+clubDetails);
		ClubDetails existingClubDetails = clubRegService.findByUserName(clubDetails.getUsername());
		if(existingClubDetails == null){
			clubDetails.setCreatedDate(DateUtility.getSQLCurrentTime());
			//put logger here
			clubRegService.save(clubDetails);
			//smsSenderService.save(smsSender);
			message = "Successfully registered";
			attributes.addFlashAttribute("popupInfoMessage",message);
			clubDetails = new ClubDetails();
		}else{
			clubDetails.setPassword("");
			message = "User Name already taken";
			model.addAttribute("popupErrorMessage",message);
			model.addAttribute("menumode", "R");
			return "register";
		}
		
		model.addAttribute("menumode", "R");
	    return "redirect:/create.htm";//new ModelAndView("register", "command", clubDetails);
	}
	
	
	@RequestMapping(value="/myprofile/view", method = RequestMethod.GET)
	public ModelAndView myProfilePage() {
		ModelAndView mav = new ModelAndView("myprofile");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
		mav.addObject("command", clubDetails);
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("user", authentication.getName());
		mav.addObject("rw", "R");
		return mav;
	}
	
	@RequestMapping(value="/myprofile/edit", method = RequestMethod.GET)
	public ModelAndView myProfileEditPage() {
		ModelAndView mav = new ModelAndView("myprofile");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
		mav.addObject("command", clubDetails);
		mav.addObject("isActive", clubDetails.getIsAccountative());
		mav.addObject("user", authentication.getName());
		mav.addObject("rw", "W");
		return mav;
	}
	
	@RequestMapping(value="/myprofile/dropdownChange", method = RequestMethod.POST)
	public ModelAndView myProfileDropdownChange(@ModelAttribute("command") ClubDetails clubDetails) {
		ModelAndView mav = new ModelAndView("myprofile");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		
		ClubDetails clubDetailsOrg = clubRegService.findByUserName(authentication.getName());

		clubDetailsOrg.setSmsText(clubDetailsOrg.getSMSSender(clubDetails.getSmsCreditBal().getSenderId()).getSmsText());
		clubDetailsOrg.setSmsCreditBal(new ESMSCreditBal(clubDetails.getSmsCreditBal().getSenderId(), 
				clubDetailsOrg.getSmsCreditBal().getBalance(), 
				clubDetailsOrg.getSMSSender(clubDetails.getSmsCreditBal().getSenderId()).getRoute()));
		
		mav.addObject("command", clubDetailsOrg);
		mav.addObject("isActive", clubDetailsOrg.getIsAccountative());
		mav.addObject("user", authentication.getName());
		mav.addObject("rw", "W");
		return mav;
	}
	
	@RequestMapping(value="/myprofile/deActivate", method = RequestMethod.POST)
	public String deActivateAccount(RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String message = "";
		ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
		if("Y".equalsIgnoreCase(clubDetails.getIsAccountative())){
			clubDetails.setIsAccountative("N");
			clubDetails.setModifiedDate(DateUtility.getSQLCurrentTime());
			clubRegService.update(clubDetails);
			message = "Account Deactivated";
			redirectAttributes.addFlashAttribute("popupInfoMessage", message);
		}else{
			message = "Account is already Deactivate";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
		}
		return "redirect:/myprofile/view";
	}
	
	@RequestMapping(value="/myprofile/reActivate", method = RequestMethod.POST)
	public String reActivateAccount(RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String message = "";
		ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
		if("Y".equalsIgnoreCase(clubDetails.getIsAccountative())){
			message = "Account is already Active";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
		}else{
			clubDetails.setIsAccountative("Y");
			clubDetails.setModifiedDate(DateUtility.getSQLCurrentTime());
			clubRegService.update(clubDetails);
			message = "Account Activated";
			redirectAttributes.addFlashAttribute("popupInfoMessage", message);
		}
		return "redirect:/myprofile/view";
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
		clubDetails.setSmsCreditBal(clubDetailstmp.getSmsCreditBal());
		clubDetails.setSmsSenders(clubDetailstmp.getSmsSenders());
		ESMSSender esmsSender = clubDetails.getSMSSender("PROMOTIONAL");
		esmsSender.setSmsText(clubDetails.getSmsText());
		//put logger here
		clubRegService.update(clubDetails);
		model.addAttribute("command", clubDetails);
		redirectAttributes.addFlashAttribute("popupInfoMessage", "Details updated");
		model.addAttribute("user", authentication.getName());
		model.addAttribute("isActive", clubDetails.getIsAccountative());
		return "redirect:/myprofile/view";
	}
	
	@RequestMapping(value="/passwordchange.htm", method=RequestMethod.GET)
    public ModelAndView passwordReset() {
		ModelAndView mav = new ModelAndView("passwordchange");
		return mav;
	}
    
    @RequestMapping(value="/passwordchangeAction.htm", method=RequestMethod.POST)
    public String passwordResetAction(@RequestParam("oldPassword") String oldPassword, 
    		@RequestParam("newPassword") String newPassword, RedirectAttributes redirectAttributes) {
    	Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
    	ClubDetails clubDetails = clubRegService.findByUserName(authentication.getName());
    	if(!clubDetails.getPassword().equals(
    			passwordEncoder.encodePassword(oldPassword, clubDetails.getUsername().toLowerCase()))){
    		redirectAttributes.addFlashAttribute("popupErrorMessage", "Invalid Old password");
    	}else{
    		clubDetails.setPassword(passwordEncoder.encodePassword(newPassword, 
    				clubDetails.getUsername().toLowerCase()));
    	}
    	clubRegService.update(clubDetails);
    	redirectAttributes.addFlashAttribute("popupInfoMessage", "Password changed");
		return "redirect:/myprofile/view";
	}
    
	@ModelAttribute
	public void addCommonAttribute(Model model){
		model.addAttribute("date", new java.util.Date().toString());
		model.addAttribute("mainmode", "MYPROFILE");
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
	    	ex.printStackTrace();
			ModelAndView model = new ModelAndView("error/exception_error_public");
			return model;
	}

}
