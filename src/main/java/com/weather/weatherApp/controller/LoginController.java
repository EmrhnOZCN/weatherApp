package com.weather.weatherApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	
	@GetMapping("/showLogin")
	public String getLogin() {
		
		
		return "Weathers/login";
	}
	
	@GetMapping("/access-denied")
	public String getAccessDenied() {
		
		
		return "Weathers/access-denied";
	}
	
}
