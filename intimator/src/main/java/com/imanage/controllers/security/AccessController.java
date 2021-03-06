package com.imanage.controllers.security;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.ClubDetails;
import com.imanage.models.security.ForgotPasswordBean;
import com.imanage.models.security.PasswordResetReqBean;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.services.security.ResetPasswordService;
import com.imanage.util.Utility;

@Controller
@RequestMapping
public class AccessController {

	Logger logger = Logger.getLogger(AccessController.class.getName());

	@Autowired
	com.imanage.services.security.ForgotPasswordService forgotPasswordService;

	@Autowired
	ResetPasswordService resetPasswordService;
	
	@Autowired
	ClubRegistrationService clubRegistrationService;

	@RequestMapping("/login")
	public String login(Model model,
			@RequestParam(required = false) String message) {
		/*
		 * message = "Session Expired...! Please login again.";
		 * model.addAttribute("failureMessage", message);
		 */
		model.addAttribute("menumode", "L");
		return "login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "roleBasedAuthenticationUrl" })
	public String roleBasedAuthenticationUrl(Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication
				.getAuthorities();
		String userRole = authList.get(0).getAuthority();
		logger.info("userRole : " + userRole);
		if (userRole.equalsIgnoreCase("ROLE_ADMIN")) {
			return "redirect:/members/browsemembers";
		} else {
			return "redirect:membersdetail";
		}
	}

	@RequestMapping(value = "/loginFailure")
	public String loginFailure(Model model) {
		String message = "Login failed";
		model.addAttribute("failureMessage", message);
		return "login";
	}

	@RequestMapping(value = "/forgotPassword.htm")
	public String forgotPassword(Model model) {
		model.addAttribute("forgotPasswordBean", new ForgotPasswordBean());
		return "security/forgotPassword";
	}

	@RequestMapping(value = "/forgotPasswordSubmit.htm")
	public String forgotPasswordSubmit(Model model,
			@Valid ForgotPasswordBean forgotPasswordBean, BindingResult result) {
		if(result.hasErrors()){
			model.addAttribute("forgotPasswordBean", forgotPasswordBean);
			return "security/forgotPassword";
		}
		logger.info("forgotPasswordBean: " + forgotPasswordBean.getToEmailId());
		forgotPasswordService.sendMail(forgotPasswordBean);
		if(forgotPasswordBean.getToEmailId() != null){
			return "redirect:login";
		}else{
			return "redirect:/forgotPassword.htm";
		}
	}

	@RequestMapping(value = "/checkEmailId.htm")
	public @ResponseBody
	String checkEmailId(@RequestBody String jsonToEmailId) /*throws JSONException */{
		/*JSONObject jsonObject = new JSONObject(jsonToEmailId);
		String toEmailId = (String) jsonObject.get("toEmailId");
		String loginName = "";
		loginName = forgotPasswordService.checkEmailId(toEmailId);*/
		return "";
	}

	@RequestMapping(value = "/resetPassword.htm")
	public String resetPassword(Model model, @RequestParam String uId) {
		PasswordResetReqBean resetReqBean = resetPasswordService
				.checkForValidLink(uId);
		if (resetReqBean.getUserName() != null
				&& !resetReqBean.getUserName().trim().equals("")) {
			double diffDays = Utility.getDateDifference(resetReqBean.getTime());
			ClubDetails clubDetails = new ClubDetails();
			clubDetails.setUsername(resetReqBean.getUserName());
			if (diffDays > 1.00) {
				return "security/resetPasswdLink";
			} else {
				model.addAttribute("clubDetailsBean", clubDetails);
				model.addAttribute("uId", uId);
				return "security/resetPassword";
			}
		} else {
			return "security/resetPasswdLink";
		}
	}

	@RequestMapping(value = { "/resetPasswdLink.htm",
			"admin/resetPasswdLink.htm" })
	public String resetPasswdLink(Model model, @RequestParam String message) {
		logger.info("message : " + message);
		model.addAttribute("passwdResetSuccess", message);
		return "security/resetPasswdLink";
	}

	@RequestMapping(value = "/resetPasswordSubmit.htm", method = RequestMethod.POST)
	public String resetPasswordSubmit(Model model,
			@ModelAttribute("clubDetailsBean") ClubDetails clubDetails, @RequestParam String uId) {
		logger.info("clubDetails: " + clubDetails.getPassword() + " uId : " + uId);
		String message = "";
		int rowsUpdated = resetPasswordService.resetPassword(clubDetails, uId);
		if (rowsUpdated > 0) {
			message = "ok";
		} else {
			message = "error";
		}
		return "redirect:resetPasswdLink.htm?message=" + message;

	}

	@RequestMapping(value = "admin/changePassword.htm")
	public String changePassword(Model model) {
		model.addAttribute("clubDetailsBean", new ClubDetails());
		return "changePassword";
	}

	@RequestMapping(value = "admin/changePasswordFailure.htm")
	public String changePasswordFailure(Model model) {
		model.addAttribute("passwdChngFailure", "error");
		return "changePassword";
	}

	@RequestMapping(value = "admin/changePasswordSubmit.htm", method = RequestMethod.POST)
	public String changePasswordSubmit(Model model,
			@ModelAttribute ClubDetails clubDetails) {
		String message = "";
		boolean success = resetPasswordService.validateOldPassword(clubDetails);
		if (success) {
			int updatedRows = resetPasswordService.updatePassword(clubDetails);
			if (updatedRows > 0) {
				message = "passwdChngSuccess";
			} else {
				message = "error";
			}
			return "redirect:resetPasswdLink.htm?message=" + message;
		} else {
			return "redirect:changePasswordFailure.htm";
		}
	}
	
	@ModelAttribute
	public void addCommonAttribute(Model model){
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		
		if(authentication!=null && authentication.getName()!=null){
			model.addAttribute("user", authentication.getName());
			model.addAttribute("date", new Date().toString());
			model.addAttribute("mode", "BROWSE");
			model.addAttribute("mainmode", "MEMBER");
		}
	}
	
	 @ExceptionHandler(Exception.class)
		public ModelAndView handleAllException(Exception ex) {
	    	ex.printStackTrace();
			ModelAndView model = new ModelAndView("error/exception_error_public");
			return model;
	 }
	 
}