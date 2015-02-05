package com.imanage.controllers;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.imanage.Session;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetailBean;
import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;
import com.imanage.services.register.ClubRegistrationService;

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
			data = data + memberDetail.getMemid()+"~"+memberDetail.getPhone()+"~"+memberDetail.getName()
					+"~"+memberDetail.getExpirydate()+"!";
		}
		mav.addObject("mode", "BM");
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
		        return new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
		    }        
	
			});
	}
	
	@RequestMapping(value="/addmember", method=RequestMethod.GET)
    public ModelAndView addMembersPage() {
		ModelAndView mav = new ModelAndView("addmember");
		mav.addObject("mode", "AM");
		return mav;
	}
	
	@RequestMapping(value="/modifymember", method=RequestMethod.GET)
    public ModelAndView modifyMembersPage() {
		ModelAndView mav = new ModelAndView("modify_member");
		mav.addObject("mode", "MM");
		return mav;
	}
	
	@RequestMapping(value="/deletemember", method=RequestMethod.GET)
    public ModelAndView deleteMembersPage() {
		ModelAndView mav = new ModelAndView("delete_member");
		mav.addObject("mode", "DM");
		return mav;
	}
	
	@RequestMapping(value="/view/{memid}", method=RequestMethod.GET, produces={"application/json"})
	@ResponseBody
    public MemberDetailBean viewMemberDetails(@PathVariable("memid") String id, HttpSession session) {
		ClubDetails clubDetails = clubRegistrationService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		Set<MemberDetails> memberDetails = clubDetails.getMemberDetails();
		MemberDetailBean memberDetailBean = null;
		for(MemberDetails memberDetail : memberDetails){
			if(id.equalsIgnoreCase(memberDetail.getMemid())){
				memberDetailBean = new MemberDetailBean(memberDetail.getPhone(), 
						memberDetail.getName(), memberDetail.getExpirydate(), memberDetail.getId());
				break;
			}
		}
		return memberDetailBean;
	}
	
	@RequestMapping(value="/memberAction/{mode}", method=RequestMethod.POST)
    public ModelAndView addMember(@ModelAttribute("addmembercommand")
    MemberDetails memberDetails, HttpSession session, @PathVariable("mode") String mode) {
		/*ClubDetails clubDetails = clubRegistrationService.findByUserName("SHEKHAR");
		Set<MemberDetails> membersSet = new HashSet<MemberDetails>();
		membersSet.add(memberDetails);
		clubDetails.setMemberDetails(membersSet);
		clubRegistrationService.update(clubDetails);
		ModelAndView mav = new ModelAndView("addmembers","message","Member updated successfully");
	    //mav.addObject("sMemberDetails", new MemberDetails());
	    return mav;*/
		
		String message = "";
		ClubDetails clubDetails = clubRegistrationService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		memberDetails.setClubDetails(clubDetails);
		/*Set<MemberDetails> membersSet = clubDetails.getMemberDetails();
		membersSet.add(memberDetails);
		clubDetails.setMemberDetails(membersSet);*/
		
		return processCRUDRequest(mode, memberDetails, clubDetails);
	}
	
	@ModelAttribute
	public void addCommonAttribute(Model model, HttpSession session){
		model.addAttribute("user", ((Session)session.getAttribute("session")).getUsername());
		model.addAttribute("date", new java.util.Date().toString());
	}
	
	private ModelAndView processCRUDRequest(String mode, MemberDetails memberDetails, ClubDetails clubDetails){
		String message = "";
		String view = "";
		switch (mode.charAt(0)) {
		case 'A':
			if(memberRegistrationService.findByMemid(memberDetails.getMemid()) == null){
				memberDetails.setClubDetails(clubDetails);
				memberRegistrationService.save(memberDetails);
				message = "Member added successfully";
			}else{
				message = "Member "+memberDetails.getMemid()+" already exist";
			}
			view = "addmember";
			break;
		case 'M':
			if(memberRegistrationService.findByMemid(memberDetails.getMemid()) == null){
				message = "Member "+memberDetails.getMemid()+" doesn't exist";
			}else{
				memberRegistrationService.update(memberDetails);
				message = "Member updated successfully";
			}
			view = "modify_member";
			break;
		case 'D':
			if(memberRegistrationService.findByMemid(memberDetails.getMemid()) == null){
				message = "Member "+memberDetails.getMemid()+" doesn't exist";
			}else{
				memberRegistrationService.delete(memberDetails);
				message = "Member deleted successfully";
			}
			view = "delete_member";
			break;
		default:
			break;
		}
		ModelAndView mav = new ModelAndView(view, "result", message);
		mav.addObject("mode", mode);
		return mav;
	}
	
}
