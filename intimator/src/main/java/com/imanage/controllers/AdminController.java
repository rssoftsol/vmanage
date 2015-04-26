package com.imanage.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imanage.models.ClubDetails;
import com.imanage.models.ESMSCreditBal;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.services.smscredit.SMSCreditdBalService;
import com.imanage.util.sms.GetSmsCreditStatus;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	SMSCreditdBalService smsCreditdBalService;
	
	@Autowired
	GetSmsCreditStatus getSmsCreditStatus;
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
		int balance = clubDetails.getSmsCreditBal().getBalance()+smsCreditBal.getBalance();
		if(balance > Integer.parseInt(getSmsCreditStatus.getSmsCreditStatus())){
			message = "Insufficient Balance";
			redirectAttributes.addFlashAttribute("popupErrorMessage", message);
			redirectAttributes.addFlashAttribute("command", smsCreditBal);
			return "redirect:/admin/updatesmsbalance";
		}
		ESMSCreditBal smsCreditBalOriginal = clubDetails.getSmsCreditBal();
		smsCreditBalOriginal.setBalance(balance);
		smsCreditdBalService.update(smsCreditBalOriginal);
		return "redirect:/admin/updatesmsbalance";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
    	ex.printStackTrace();
		ModelAndView model = new ModelAndView("error/exception_error_admin");
		return model;
	}
}
