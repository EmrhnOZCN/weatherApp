package com.weather.weatherApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.WeatherEntity;
import com.weather.weatherApp.service.weatherService.IWeatherService;
import com.weather.weatherApp.service.adminService.IAdminService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class AdminController {

	private IAdminService adminService;
	private IWeatherService iWeatherService;
	private static final int PAGE_SIZE = 4;

	public AdminController(IAdminService adminService, IWeatherService iWeatherService) {
		super();
		this.adminService = adminService;
		this.iWeatherService = iWeatherService;
	}

	@GetMapping("/showAdminPanel")
	public String showAdmin(@RequestParam(defaultValue = "0") int page, Model model) {
		// Yönetici paneli sayfasında görüntülenecek verileri al

		// En son güncellenen 4 hava durumu kaydını getir
		List<WeatherEntity> weather = adminService.getAll();






		// Sayfalama için gerekli parametreleri ayarla
		Pageable pageable = PageRequest.of(page, PAGE_SIZE);
		Page<MembersEntity> userPage = adminService.getAllMembers(pageable);

		// Sayfalama sonucunda gösterilecek kullanıcı listesini al
		List<MembersEntity> users = userPage.getContent();

		// Tüm kullanıcı sayısını al
		long userCount = adminService.getUserCount();

		// Son 24 saatte yapılan hava durumu sorgu sayısını al
		long queryCount = adminService.getQueryCount();

		// Tüm rolleri al




		// Model'e gerekli verileri ekle
		model.addAttribute("weather", weather);

		model.addAttribute("userCount", userCount);
		model.addAttribute("queryCount", queryCount);
		model.addAttribute("users", users);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", userPage.getTotalPages());

		// Yönetici paneli sayfasını döndür
		return "Weathers/admin";
	}

	@PostMapping("/deleteUser")
	public String deleteUser(@RequestParam Long userId) {
		// Kullanıcı silme işlemini gerçekleştirmek için AdminService sınıfındaki uygun metodu çağırın
		adminService.deleteUser(userId);
		// Kullanıcıyı silen işlem başarıyla gerçekleştiyse, kullanıcıyı yönlendireceğiniz bir sayfa döndürebilirsiniz.
		// Örneğin, kullanıcıları tekrar listeleyen bir sayfaya yönlendirebilirsiniz.
		return "redirect:/showAdminPanel";
	}

}
