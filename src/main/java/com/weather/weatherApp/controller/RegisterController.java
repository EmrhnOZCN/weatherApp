package com.weather.weatherApp.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.RolesEntity;
import com.weather.weatherApp.service.userService.IUserService;

@Controller
public class RegisterController {

	
	private IUserService iUserService;

	public RegisterController(IUserService iUserService) {
		super();
		this.iUserService = iUserService;
	}
	
	
	
	

	@GetMapping("/register")
	public String getRegister() {
		
		
		return "Weathers/sign";
	}
	
	@GetMapping("/signUp")
	public String getSignUp() {
		
		return "Weathers/sign";
	}
	
	
	@PostMapping("/registerPost")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String role) {
        MembersEntity membersEntity = new MembersEntity(email, "{noop}" + password, true);
        RolesEntity rolesEntity = new RolesEntity(email, role);
        System.out.println(email+""+password);
        System.out.println(email+""+role);
        
        
        iUserService.addMembers(membersEntity);
        iUserService.addRoles(rolesEntity);

        // Kayıt işlemlerini gerçekleştirdikten sonra, başarılı bir sayfaya yönlendirebiliriz.
        return "Weathers/login"; // Thymeleaf template adı (kayıt başarılı sayfası)
    }
	
	
}
