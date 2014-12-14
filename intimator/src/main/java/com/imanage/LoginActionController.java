package com.imanage;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/loginAction")
public class LoginActionController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loginHandler(HttpServletRequest request,HttpServletResponse res){
		String name=request.getParameter("name");  
        String password=request.getParameter("password");  
          
        if(password.equals("admin")){
        	String message = "HELLO "+name;  
            return new ModelAndView("membersdetail", "message", message);
        }else{
        	return new ModelAndView("login", "message","Sorry, username or password error");
        }
	}
}
