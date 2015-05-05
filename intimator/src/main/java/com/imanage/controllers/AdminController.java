package com.imanage.controllers;

import java.util.List;
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

import com.imanage.intimate.NotificationType;
import com.imanage.intimate.impl.NotifyingService;
import com.imanage.models.ClubDetails;
import com.imanage.models.ESMSCreditBal;
import com.imanage.models.ESMSSender;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.services.smscredit.SMSCreditdBalService;
import com.imanage.services.smssender.SMSSenderService;
import com.imanage.util.DateUtility;
import com.imanage.util.SMSErrorCodes;
import com.imanage.util.Utility;
import com.imanage.util.sms.GetSmsCreditStatus;
import com.imanage.util.sms.SMSStatus;
import com.imanage.util.sms.SmsCallGet;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	SMSCreditdBalService smsCreditdBalService;
	
	@Autowired
	NotifyingService notifyingService;
	
	@Autowired
	GetSmsCreditStatus getSmsCreditStatus;
	
	@Autowired
	SMSSenderService smsSenderService;
	    
	@Autowired
	SmsCallGet smsCallGet;
	
	@Autowired
	SMSStatus smsStatus;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="")
	public String getSubscribedClubs_(Model model){
		
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value="/users")
	public String getSubscribedClubs(Model model){
		String dataset = "";
		List<ClubDetails> clubsList = clubRegistrationService.findAll();
		for(ClubDetails clubDetails : clubsList){
			dataset = dataset + clubDetails.getClub_id()+"~"+clubDetails.getClubname()+"~"+clubDetails.getPhonenumber()
					+"~"+clubDetails.getMemberDetails().size()+"!";
		}
		model.addAttribute("dataset", dataset);
		return "admin";
	}
	
	
	
	@RequestMapping(value="/updatesmsbalance.htm")
	public String updateSMSCreditBalance(Model model){
		model.addAttribute("command", new ESMSCreditBal());
		return "updatesmsbalance";
	}
	
	@RequestMapping(value="/updateSmsBalanceAction.htm")
	public String updateSMSCreditBalance(Model model, RedirectAttributes redirectAttributes
			, @ModelAttribute("command") @Valid ESMSCreditBal smsCreditBal, BindingResult result){
		String message = "";
		if(result.hasErrors()){
			model.addAttribute("command", smsCreditBal);
			return "updatesmsbalance";
		}
		ClubDetails clubDetails = clubRegistrationService.findByClubId(smsCreditBal.getClubId());
		if(clubDetails == null){
			message = "Invalid Club ID";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			redirectAttributes.addFlashAttribute("command", smsCreditBal);
			return "redirect:/admin/updatesmsbalance";
		}
		int balance = 0;
		List<ClubDetails> clubs = clubRegistrationService.findAll();
		for(ClubDetails club : clubs){
			balance = balance + club.getSmsCreditBal().getBalance();
		}
		balance = balance + smsCreditBal.getBalance();
		if(balance > Integer.parseInt(getSmsCreditStatus.getSmsCreditStatus())){
			message = "Insufficient Balance";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			redirectAttributes.addFlashAttribute("command", smsCreditBal);
			return "redirect:/admin/updatesmsbalance.htm";
		}
		ESMSCreditBal smsCreditBalOriginal = clubDetails.getSmsCreditBal();
		smsCreditBalOriginal.setBalance(balance);
		smsCreditdBalService.update(smsCreditBalOriginal);
		return "redirect:/admin/updatesmsbalance.htm";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
    	ex.printStackTrace();
		ModelAndView model = new ModelAndView("error/exception_error_admin");
		return model;
	}
	
	@RequestMapping(value="/smstrigger.htm")
	public String loadManualReminder(){
		return "smstrigger";
	}
	
	@RequestMapping(value="/sendManualReminder.htm")
	public String sendManualReminder(@RequestParam("clubId") String clubId, RedirectAttributes redirectAttributes){
		String message = "Error Occured!!!";
		if(clubId == null || clubId.trim().equalsIgnoreCase("")){
			message = "Invalid Club ID";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			return "redirect:/admin/smstrigger.htm";
		}
		ClubDetails clubDetails = clubRegistrationService.findByClubId(Integer.valueOf(clubId));
		if(clubDetails == null){
			message = "Invalid Club ID";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			return "redirect:/admin/smstrigger.htm";
		}
		if(clubDetails.getSmsCreditBal().getRoute().equals("P")){
			if(DateUtility.getHourOfTheDay()>20 || DateUtility.getHourOfTheDay()<9){
				redirectAttributes.addFlashAttribute("popupErrorMessage", "Promotional route can be used only between 9 AM and 8 PM");
				return "redirect:/admin/smstrigger.htm";
			}
		}
		if(!Utility.checkSMSBalance(clubDetails, NotificationType.MANUAL_REMINDER)){
			message = "Insufficent Balance, contact IT!!!";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			return "redirect:/admin/smstrigger.htm";
		}
		
		notifyingService.intimate(clubDetails, NotificationType.MANUAL_REMINDER, 
				clubDetails.getSmsText());
		message = "Reminder messages queued for sending!!!";
		redirectAttributes.addFlashAttribute("popupInfoMessage", message);
		return "redirect:/admin/smstrigger.htm";
	}
	
	@RequestMapping(value="/sendDailyReminder.htm")
	public String reRunDailyReminder(@RequestParam("clubId") String clubId, RedirectAttributes redirectAttributes){
		String message = "Error Occured!!!";
		if(clubId == null || clubId.trim().equalsIgnoreCase("")){
			message = "Invalid Club ID";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			return "redirect:/admin/smstrigger.htm";
		}
		ClubDetails clubDetails = clubRegistrationService.findByClubId(Integer.valueOf(clubId));
		if(clubDetails == null){
			message = "Invalid Club ID";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			return "redirect:/admin/smstrigger.htm";
		}
		if(clubDetails.getSmsCreditBal().getRoute().equals("P")){
			if(DateUtility.getHourOfTheDay()>20 || DateUtility.getHourOfTheDay()<9){
				redirectAttributes.addFlashAttribute("popupErrorMessage", "Proomotional route can be used only between 9 AM and 8 PM");
				return "redirect:/admin/smstrigger.htm";
			}
		}
		if(!Utility.checkSMSBalance(clubDetails, NotificationType.SCHEDULAR_REMINDER)){
			message = "Insufficent Balance, contact IT!!!";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			return "redirect:/admin/smstrigger.htm";
		}
		notifyingService.intimate(clubDetails, NotificationType.SCHEDULAR_REMINDER, 
				clubDetails.getSmsText());
		message = "Reminder sent!!!";
		redirectAttributes.addFlashAttribute("popupInfoMessage", message);
		return "redirect:/admin/smstrigger.htm";
	}
	
	@RequestMapping(value="/createSmsSender.htm", method=RequestMethod.GET)
    public ModelAndView sms() {
		ModelAndView mav = new ModelAndView("addsmssender");
		mav.addObject("command", new ESMSSender());
		return mav;
	}
    
    @RequestMapping(value="/createSmsSenderAction.htm", method=RequestMethod.POST)
    public String sendSms(@ModelAttribute("command") 
    @Valid ESMSSender esmsSenders, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
    	if(result.hasErrors()){
    		model.addAttribute("command", esmsSenders);
    		return "addsmssender";
    	}
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		//put logger here
		ClubDetails clubDetails = clubRegistrationService.findByUserName(authentication.getName());
		Set<ESMSSender> smsSenders = clubDetails.getSmsSenders();
		for(ESMSSender smsDetail : smsSenders){
			if(smsDetail.getId().equalsIgnoreCase(esmsSenders.getId())){
				redirectAttributes.addFlashAttribute("popupErrorMessage", "Sender Id already exist");
				return "redirect:/admin/createSmsSender.htm";
			}
		}
		smsCallGet.setSenderId(esmsSenders.getId());
		smsCallGet.setRoute("T");
		String response = smsCallGet.sendMessage(clubDetails.getPhonenumber().toString(), esmsSenders.getSmsText());
		if(SMSErrorCodes.getSMSErrorCodeMeaning(response) == null){
			String status = smsStatus.getSmsStatus(response, clubDetails.getPhonenumber().toString(), DateUtility.getTodaysDate("yyyyMMdd"));
			if(status!= null && !status.equalsIgnoreCase("PROMO NOT ALLOWED")){
				redirectAttributes.addFlashAttribute("popupInfoMessage", "Sender Id registered Successfully");
			}else{
				redirectAttributes.addFlashAttribute("popupErrorMessage", "Invalid message content");
			}
		}else{
			redirectAttributes.addFlashAttribute("popupErrorMessage", "Sender Id registeration Failed, "
					+ "response returned:"+response);
			return "redirect:/admin/createSmsSender.htm";
			 
		}
		esmsSenders.setClubDetails(clubDetails);
		smsSenderService.save(esmsSenders);
		return "redirect:/admin/createSmsSender.htm";
	}
    
    @RequestMapping(value="/passwordReset.htm", method=RequestMethod.GET)
    public ModelAndView passwordReset() {
		ModelAndView mav = new ModelAndView("passwordreset");
		return mav;
	}
    
    @RequestMapping(value="/passwordResetAction.htm", method=RequestMethod.POST)
    public String passwordResetAction(@RequestParam("username") String username, 
    		@RequestParam("password") String password, RedirectAttributes redirectAttributes) {
    	ClubDetails clubDetails = clubRegistrationService.findByUserName(username);
    	if(clubDetails == null){
    		redirectAttributes.addFlashAttribute("popupErrorMessage", "User doesn't exist");
    	}else{
    		clubDetails.setPassword(passwordEncoder.encodePassword(password, clubDetails.getUsername().toLowerCase()));
    	}
    	clubRegistrationService.update(clubDetails);
    	redirectAttributes.addFlashAttribute("popupInfoMessage", "Password changed");
		return "redirect:/admin/passwordReset.htm";
	}
}
