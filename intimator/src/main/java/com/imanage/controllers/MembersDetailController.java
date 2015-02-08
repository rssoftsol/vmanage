package com.imanage.controllers;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.Session;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetailBean;
import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.util.crud.impl.CRUDHandlerImpl;

@Controller
@RequestMapping("/members")
public class MembersDetailController {
	
	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	MemberRegistrationService memberRegistrationService;
	
	@RequestMapping(value="/browsemembers", method = RequestMethod.GET)
	public ModelAndView browseMembersPage(HttpSession session) {
		String data = "";
		ModelAndView mav = new ModelAndView("membersdetail");
		ClubDetails clubDetails = clubRegistrationService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		for(MemberDetails memberDetail : clubDetails.getMemberDetails()){
			data = data + memberDetail.getMemid()+"~"+memberDetail.getName()+"~"+memberDetail.getPhone()
					+"~"+memberDetail.getExpirydate()+"!";
		}
		mav.addObject("mode", "BROWSE");
		mav.addObject("dataset", data);
		return mav;
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(java.sql.Date.class, new PropertyEditorSupport() {
		    public void setAsText(String value) {
		        try {
		            setValue(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(value).getTime()));
		        } catch(ParseException e) {
		            setValue(null);
		        }
		    }
		    public String getAsText() {
		    	if(getValue() != null){
		    		return new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
		    	}
		    	return "";
		    }        
	
			});
	}
	
	@RequestMapping(value="/member/{mode}", method=RequestMethod.GET)
    public ModelAndView addMembersPage(@PathVariable("mode") String mode) {
		ModelAndView mav = new ModelAndView("member");
		mav.addObject("mode", mode);
		mav.addObject("commandd", new MemberDetails());
		return mav;
	}
	
	@RequestMapping(value="/view/{mode}/{memid}", method=RequestMethod.POST)
    public ModelAndView viewMemberDetails(@PathVariable("memid") String id, @PathVariable("mode") String mode, 
    		HttpSession session) {
		ModelAndView mav = new ModelAndView("member");
		mav.addObject("mode", mode);
		ClubDetails clubDetails = clubRegistrationService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		Set<MemberDetails> memberDetails = clubDetails.getMemberDetails();
		
		for(MemberDetails memberDetail : memberDetails){
			if(id.equalsIgnoreCase(memberDetail.getMemid())){
				mav.addObject("commandd", memberDetail);
				return mav;
			}
		}
		mav.addObject("commandd", new MemberDetails());
		mav.addObject("popupMessage","No member exist with given Id:"+id);
		return mav;
	}
	
	@RequestMapping(value="/view/{mode}", method=RequestMethod.POST)
    public ModelAndView viewMemberDetails(@PathVariable("mode") String mode) {
		ModelAndView mav = new ModelAndView("member");
		mav.addObject("mode", mode);
		mav.addObject("popupMessage","Please provide Member Id");
		mav.addObject("commandd", new MemberDetails());
		return mav;
	}
	
	@RequestMapping(value="/memberAction/{mode}", method=RequestMethod.POST)
    public ModelAndView memberAction(@ModelAttribute("commandd") 
    @Valid MemberDetails memberDetails, BindingResult result, HttpSession session, @PathVariable("mode") String mode) {
		if(result.hasErrors()){
			ModelAndView mav = new ModelAndView("member");
			mav.addObject("commandd", memberDetails);
			mav.addObject("mode", mode);
			return mav;
		}
		ClubDetails clubDetails = clubRegistrationService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		memberDetails.setClubDetails(clubDetails);
		return new CRUDHandlerImpl().processCRUDRequest(mode, memberDetails, memberRegistrationService);
	}
	
	@ModelAttribute
	public void addCommonAttribute(Model model, HttpSession session){
		model.addAttribute("user", ((Session)session.getAttribute("session")).getUsername());
		model.addAttribute("date", new java.util.Date().toString());
		model.addAttribute("mainmode", "MEMBER");
		model.addAttribute("headermsg", "Provide Member Details"); 
	}
	
}
