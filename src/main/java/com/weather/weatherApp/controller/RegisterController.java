package com.weather.weatherApp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
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

	private PasswordEncoder passwordEncoder;

	public RegisterController(IUserService iUserService,PasswordEncoder passwordEncoder) {
		super();
		this.iUserService = iUserService;
		this.passwordEncoder=passwordEncoder;
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



        RolesEntity rolesEntity = new RolesEntity(role);
		MembersEntity membersEntity = new MembersEntity(email,  passwordEncoder.encode(password), true,rolesEntity);

        
        
        iUserService.addMembers(membersEntity);


        // Kayıt işlemlerini gerçekleştirdikten sonra, başarılı bir sayfaya yönlendirebiliriz.
        return "Weathers/login"; // Thymeleaf template adı (kayıt başarılı sayfası)
    }
	
	
}
