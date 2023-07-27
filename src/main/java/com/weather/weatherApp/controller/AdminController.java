package com.weather.weatherApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.weather.weatherApp.dto.WeatherDto;
import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.RolesEntity;
import com.weather.weatherApp.model.WeatherEntity;
import com.weather.weatherApp.repository.RolesRepository;
import com.weather.weatherApp.service.IWeatherService;
import com.weather.weatherApp.service.adminService.IAdminService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Controller
public class AdminController {

	
	private IAdminService adminService;
	
	private IWeatherService iWeatherService;
	
	private static final int PAGE_SIZE=4	;

	public AdminController(IAdminService adminService,IWeatherService iWeatherService) {
		super();
		this.adminService = adminService;
		this.iWeatherService=iWeatherService;
	}
	
	
	@GetMapping("/showAdminPanel")
	public String showAdmin(@RequestParam(defaultValue = "0") int page,Model model) {
		
		List<WeatherEntity> weather = adminService.getAll();
		
		Pageable pageable = PageRequest.of(page, PAGE_SIZE);
		Page<MembersEntity> userPage = adminService.getAllMembers(pageable);
		
		List<MembersEntity> users = userPage.getContent();
		
		
		
		long userCount = adminService.getUserCount();
		
		long queryCount = adminService.getQueryCount();
		
		List<RolesEntity> rolesEntities = adminService.getAllRoles();
		
		System.out.println("burda");
		
			
		// Model'e gerekli verileri ekle
        model.addAttribute("weather", weather);
        model.addAttribute("rolesEntities", rolesEntities);
        model.addAttribute("userCount", userCount);
        model.addAttribute("queryCount", queryCount);
       
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
		
		return "Weathers/admin";
	}
	
	@PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        // Silme işlemini gerçekleştirmek için UserService sınıfındaki uygun metodu çağırın
        
		adminService.deleteUser(userId);
        // Kullanıcıyı silen işlem başarıyla gerçekleştiyse, kullanıcıyı yönlendireceğiniz bir sayfa döndürebilirsiniz.
        // Örneğin, kullanıcıları tekrar listeleyen bir sayfaya yönlendirebilirsiniz.
        return "redirect:/showAdminPanel";
    }
	
}
