package com.bubanking.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	
	@RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String login(ModelMap model){
		
		return "login";
	}
	
	@RequestMapping(value="/loginfailed.html")
	public @ResponseBody String logError(ModelMap model){
		model.addAttribute("error", "true");
		 System.err.println("/loginfailed.html");
		
		return "{success:false}";
		//return "login";
	}
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpServletRequest request, ModelMap model){
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		
		
		return "login";
	}
	@RequestMapping(value="/successed.html")
	public @ResponseBody String successed(HttpServletResponse response){
		System.err.println("/successed.html");
		//response.setStatus(HttpServletResponse.SC_OK);
		//return "successed";
		return "{success:true}";
	}
	
	@RequestMapping("/403")
	public String accessDenied(){
		return "login";
	}
}
