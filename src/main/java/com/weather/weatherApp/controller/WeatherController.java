package com.weather.weatherApp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weatherApp.dto.WeatherDto;
import com.weather.weatherApp.model.WeatherEntity;
import com.weather.weatherApp.service.WeatherService;

import org.springframework.ui.Model;
import jakarta.validation.constraints.NotBlank;

@Controller
public class WeatherController {
	
	
	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
	
		this.weatherService = weatherService;
	}
	
	
	@GetMapping("/weather")
    public String showWeatherForm(@ModelAttribute("weather") WeatherDto weather,Model model) {

		
		
        return "Weathers/weather-form";
    }

	@PostMapping("/weather")
	public String getWeatherByCityName(@RequestParam("cityName") String cityName,Model model) {
		
	    WeatherDto result = weatherService.getWeatherByCityName(cityName);
	    
	    

	    model.addAttribute("weather", result);
	    return "Weathers/weather-form";
	}
	
	
	
	
	
	
	
	
}
