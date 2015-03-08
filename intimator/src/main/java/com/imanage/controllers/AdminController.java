package com.imanage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imanage.models.ClubDetails;
import com.imanage.services.register.ClubRegistrationService;

@Controller
public class AdminController {

	@Autowired
	ClubRegistrationService clubRegistrationService;
	
	@RequestMapping(value="/adminnnn")
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
}
