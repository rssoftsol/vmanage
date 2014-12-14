package com.imanage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registerAction")
public class RegistrationActionController {
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loginHandler(HttpServletRequest request,HttpServletResponse res){
	
		String name = request.getParameter("clubname");  
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");  
	    String memtype = request.getParameter("membershipType");  
	    System.out.println("clubname: "+name);
	    System.out.println("username: "+username);
	    System.out.println("membershipType: "+memtype);
	    if("premium".equals(memtype)){
	        return new ModelAndView("register", "message", "Successfully registered");
	    }else{
	    	return new ModelAndView("register", "message","Sorry, Registeration failed");
	    }
	}
}
