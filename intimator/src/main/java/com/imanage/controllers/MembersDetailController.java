package com.imanage.controllers;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

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
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;
import com.imanage.services.members.MemberRegistrationService;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.util.MemberModeEnum;
import com.imanage.util.crud.impl.CRUDHandlerImpl;
import com.imanage.util.excel.members.MembersDetailExcelReader;
import com.imanage.util.excel.members.MembersDetailUploadBean;

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
		/*if(!"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType())){
			mav = new ModelAndView("uploadexcel");
			mav.addObject("popupMessage", "Invalid file format");
			return mav;
		}*/
		String validMembers = "";
		String invalidMembers = "";
		try {
			if (!file.isEmpty()) {
				Set<String> memberIds = new HashSet<String>();
				Authentication authentication = SecurityContextHolder.getContext()
						.getAuthentication();
				ClubDetails clubDetails = clubRegistrationService.findByUserName(authentication.getName());
				for(MemberDetails memberDetails : clubDetails.getMemberDetails()){
					memberIds.add(memberDetails.getMemid());
				}
				MembersDetailExcelReader membersDetailExcelReader = new MembersDetailExcelReader();
				membersDetailExcelReader.memberDetails = memberIds;
				Vector<MembersDetailUploadBean> excelData = membersDetailExcelReader.
						 importExcelSheet(file.getInputStream());
				System.out.println("excelData--->"+excelData);
				if(excelData == null){
					mav = new ModelAndView("uploadexcel");
					mav.addObject("popupErrorMessage", "Invalid file format");
					return mav;
				}else{
					for(MembersDetailUploadBean membersDetailUploadBean : excelData){
						if(membersDetailUploadBean.hasError){
							invalidMembers = invalidMembers + membersDetailUploadBean.memberId+"~"+
									membersDetailUploadBean.name+"~"+membersDetailUploadBean.phonenumber+"~"+
									membersDetailUploadBean.expiryDate+"~"+membersDetailUploadBean.getErrorString()+"!";
						}else{
							validMembers = validMembers + membersDetailUploadBean.memberId+"~"+
									membersDetailUploadBean.name+"~"+membersDetailUploadBean.phonenumber+"~"+
									membersDetailUploadBean.expiryDate+"!";
						}
					}
					if("".equalsIgnoreCase(invalidMembers)){
						processMembersString(validMembers, clubDetails);
						mav = new ModelAndView("uploadexcel");
						mav.addObject("popupInfoMessage", "Upload completed");
						return mav;
					}else{
						mav = new ModelAndView("confirmupload");
						if("".equalsIgnoreCase(validMembers))
							mav.addObject("UPLOADINFO", "All member's of excel has invalid data as listed above");
						else
							mav.addObject("UPLOADINFO", "Member's listed above has invalid data");
						System.out.println("VALIDMEMBERS--->"+validMembers);
						System.out.println("INVALIDMEMBERS--->"+invalidMembers);
						mav.addObject("VALIDMEMBERS", validMembers);
						mav.addObject("INVALIDMEMBERS", invalidMembers);
						return mav;
					}
				}
			}else{
				mav = new ModelAndView("uploadexcel");
				mav.addObject("popupErrorMessage", "Please select the file first");
				return mav;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//mav.addObject("popupMessage", "Upload completed");
		return mav;
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
    public void downloadExcel(HttpServletRequest request,
            HttpServletResponse response) {
        // create some sample data
    	//ServletContext context = request.getServletContext();
        ClassLoader classLoader = MembersDetailController.class.getClassLoader();
		String path = classLoader.getResource("/resources").getPath();
		String fullPath = path+"/sampleexcel/mymembers.xls";
		File downloadFile = new File(fullPath);
        
        String mimeType = null;//context.getMimeType(fullPath);
            // set to binary type
        mimeType = "application/octet-stream";
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        try {
        	FileInputStream inputStream = new FileInputStream(downloadFile);
			OutputStream outStream = response.getOutputStream();
			final int BUFFER_SIZE = 4096;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
 
			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
			    outStream.write(buffer, 0, bytesRead);
			}
 
			inputStream.close();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				
				memberRegistrationService.save(memberDetails);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
