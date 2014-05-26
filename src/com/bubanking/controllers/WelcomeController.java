package com.bubanking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value="/index.html",method=RequestMethod.GET)
	public String index(){
		return "index";
	}
	@RequestMapping(value="/content.html" ,method=RequestMethod.GET)
	public String loadContent(String name){
		return name;
	}

	
}
