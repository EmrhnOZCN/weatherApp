package com.weather.weatherApp.controller;

import com.weather.weatherApp.validation.CityNameConstraint;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.userdetails.UserDetails;
import com.weather.weatherApp.dto.WeatherDto;
import com.weather.weatherApp.service.weatherService.WeatherService;

import org.springframework.ui.Model;


@Controller
@Validated
public class WeatherController {


	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {

		this.weatherService = weatherService;
	}


	@GetMapping("/weather")
    public String showWeatherForm(@ModelAttribute("weather")  WeatherDto weather, Model model) {



        return "Weathers/weather-form";
    }

	@PostMapping("/weather")
	public String getWeatherByCityName(RuntimeException runtimeException,@RequestParam("cityName") @CityNameConstraint String cityName, Model model, Authentication authentication)
	{
		// Authentication nesnesi, kullanıcının kimlik bilgilerini içerir
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();



		WeatherDto result = weatherService.getWeatherByCityName(cityName,userDetails.getUsername());


		System.out.println("burda");


		// Burada username değişkeni üzerinden kullanıcının kimlik bilgilerini kullanabilirsiniz
		// Örneğin, bu bilgiyi loglamak, kullanıcıya özgü bir işlem yapmak için kullanabilirsiniz.


	    model.addAttribute("weather", result);
	    return "Weathers/weather-form";
	}








}
