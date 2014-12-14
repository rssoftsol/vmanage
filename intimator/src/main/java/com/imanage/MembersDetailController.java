package com.imanage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.beans.MemberDetails;

@Controller
@RequestMapping("/members")
public class MembersDetailController {
	
	@RequestMapping(value="/browsemembers", method = RequestMethod.GET)
	public ModelAndView browseMembersPage(HttpServletRequest request,HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("membersdetail");
		return mav;
	}
	
	@RequestMapping(value="/addmember", method=RequestMethod.GET)
    public ModelAndView addMembersPage() {
		ModelAndView mav = new ModelAndView("addmembers");
	    mav.addObject("sPhone", new MemberDetails());
	    return mav;
	}
}
