package com.imanage.controllers;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imanage.Session;
import com.imanage.exception.ExcelException;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.util.DateUtility;
import com.imanage.util.MemberModeEnum;
import com.imanage.util.crud.impl.CRUDHandlerImpl;
import com.imanage.util.excel.uploadexcel.impl.UploadMembersExcelImpl;

@Controller
@RequestMapping("/members")
public class MembersDetailController {
	
	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@Autowired
	MemberRegistrationService memberRegistrationService;
	
	@RequestMapping(value="/browsemembers", method = RequestMethod.GET)
	public String browseMembersPage(HttpSession session, Model model, RedirectAttributes attributes) {
		String data = "";
		ClubDetails clubDetails = clubRegistrationService.findByUserName(((Session)session.getAttribute("session")).getUsername());
		String view =null;
		if("Y".equalsIgnoreCase(clubDetails.getIsAccountative())){
			view = "membersdetail";
			
			for(MemberDetails memberDetail : clubDetails.getMemberDetails()){
				data = data + memberDetail.getMemid()+"~"+memberDetail.getName()+"~"+memberDetail.getPhone()
						+"~"+memberDetail.getExpirydate()+"!";
			}
			model.addAttribute("mode", "BROWSE");
			model.addAttribute("dataset", data);
		}else{
			attributes.addFlashAttribute("popupInfoMessage","Please Activate the Account first");
			return "redirect:/myprofile/edit";
		}
		return view;
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
		mav.addObject("mode", MemberModeEnum.getMemberModeEnum(mode));
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
		mav.addObject("popupErrorMessage","No member exist with given Id:"+id);
		return mav;
	}
	
	@RequestMapping(value="/view/{mode}", method=RequestMethod.POST)
    public ModelAndView viewMemberDetails(@PathVariable("mode") String mode) {
		ModelAndView mav = new ModelAndView("member");
		mav.addObject("mode", mode);
		mav.addObject("popupErrorMessage","Please provide Member Id");
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
	
	@RequestMapping(value="/member/upload", method=RequestMethod.GET)
    public ModelAndView uploadExcelPage() {
		ModelAndView mav = new ModelAndView("uploadexcel");
		return mav;
	}
	
	@RequestMapping(value="/member/uploadAction", method=RequestMethod.POST)
    public ModelAndView uploadExcelPage(@RequestParam("file") MultipartFile file) {
		ModelAndView mav = null;
		if (file.isEmpty()){
			mav = new ModelAndView("uploadexcel");
			mav.addObject("popupErrorMessage", "Please select the file first");
			return mav;
		}
		Set<String> memberIds = new HashSet<String>();
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		ClubDetails clubDetails = clubRegistrationService.findByUserName(authentication.getName());
		for(MemberDetails memberDetails : clubDetails.getMemberDetails()){
			memberIds.add(memberDetails.getMemid());
		}
		UploadMembersExcelImpl uploadExcel = new UploadMembersExcelImpl();
		uploadExcel.setMemberDetails(memberIds);
		try {
			uploadExcel.upload(file);
		} catch (ExcelException e) {
			mav = new ModelAndView("uploadexcel");
			mav.addObject("popupErrorMessage", e.getMessage());
			return mav;
		}
		if(uploadExcel.allDataInExcelIsValid()){
			processMembersString(uploadExcel.getValidMembersString(), clubDetails);
			mav = new ModelAndView("uploadexcel");
			mav.addObject("popupInfoMessage", "Upload completed");
			return mav;
		}else{
			mav = new ModelAndView("confirmupload");
			if("".equalsIgnoreCase(uploadExcel.getValidMembersString()))
				mav.addObject("UPLOADINFO", "All member's of excel has invalid data as listed above");
			else
				mav.addObject("UPLOADINFO", "Member's listed above has invalid data");
			mav.addObject("VALIDMEMBERS", uploadExcel.getValidMembersString());
			mav.addObject("INVALIDMEMBERS", uploadExcel.getInvalidMembersString());
			return mav;
		}
	}
	
	@RequestMapping(value="/confirmupload", method=RequestMethod.POST)
    public ModelAndView confirmUpload(@RequestParam("validmembers") String validmembers) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		ClubDetails clubDetails = clubRegistrationService.findByUserName(authentication.getName());
		processMembersString(validmembers, clubDetails);
		ModelAndView mav = new ModelAndView("uploadexcel");
		mav.addObject("popupInfoMessage", "Upload completed");
		return mav;
	}
	
	@RequestMapping(value="/cancelupload", method=RequestMethod.POST)
    public ModelAndView cancelUpload() {
		ModelAndView mav = new ModelAndView("uploadexcel");
		mav.addObject("popupInfoMessage", "Upload Aborted");
		return mav;
	}
	
	 /**
     * Handle request to download an Excel document
     */
    @RequestMapping(value = "/mymembers.xls", method = RequestMethod.POST)
    public ModelAndView redirectTodownloadExcel(HttpServletRequest request,
            HttpServletResponse response) {
    	return new ModelAndView("excelView");
    }
	
	private void processMembersString(String members, ClubDetails clubDetails){
		String[] validmembersArr = members.split("!");
		for(int i=0;i<validmembersArr.length;i++){
			String[] memberArr = validmembersArr[i].split("~");
			try {
				MemberDetails memberDetails = new MemberDetails(
						memberArr[0], memberArr[1], Long.valueOf(memberArr[2]), 
						new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(memberArr[3]).getTime()));
				memberDetails.setClubDetails(clubDetails);
				memberDetails.setCreatedDate(DateUtility.getSQLCurrentTime());
				memberRegistrationService.save(memberDetails);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
