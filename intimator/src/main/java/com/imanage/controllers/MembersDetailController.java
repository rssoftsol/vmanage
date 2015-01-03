package com.imanage.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;
import com.imanage.services.register.ClubRegistrationService;

@Controller
@RequestMapping("/members")
public class MembersDetailController {
	
	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@RequestMapping(value="/browsemembers", method = RequestMethod.GET)
	public ModelAndView browseMembersPage() {
		ModelAndView mav = new ModelAndView("membersdetail");
		return mav;
	}
	
	@RequestMapping(value="/addmember", method=RequestMethod.GET)
    public ModelAndView addMembersPage() {
		ModelAndView mav = new ModelAndView("addmembers", "addmembercommand", new MemberDetails());
	    //mav.addObject("sMemberDetails", new MemberDetails());
	    return mav;
	}
	
	@RequestMapping(value="/addMemberAction", method=RequestMethod.POST)
    public ModelAndView addMember(@ModelAttribute("addmembercommand")
    MemberDetails memberDetails) {
		ClubDetails clubDetails = clubRegistrationService.findByUserName("SHEKHAR");
		Set<MemberDetails> membersSet = new HashSet<MemberDetails>();
		membersSet.add(memberDetails);
		clubDetails.setMemberDetails(membersSet);
		clubRegistrationService.update(clubDetails);
		ModelAndView mav = new ModelAndView("addmembers","message","Member updated successfully");
	    //mav.addObject("sMemberDetails", new MemberDetails());
	    return mav;
	}
}
