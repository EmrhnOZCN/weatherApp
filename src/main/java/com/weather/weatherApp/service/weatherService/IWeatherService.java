package com.weather.weatherApp.service.weatherService;

import java.util.List;

import com.weather.weatherApp.dto.WeatherDto;
import com.weather.weatherApp.dto.WeatherResponse;
import com.weather.weatherApp.model.WeatherEntity;

public interface IWeatherService {

	
	WeatherDto getWeatherByCityName(String city);
	
	String getWeatherStackUrl(String city);
	
	WeatherEntity getWeatherFromWeatherStack(String city);
	
	WeatherEntity saveWeatherEntity(String city,WeatherResponse response);
	
	
	
}
