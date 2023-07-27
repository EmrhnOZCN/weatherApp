package com.weather.weatherApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.weather.weatherApp.model.MembersEntity;


@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(
		Request request,
		
		Location location,
		
		Current current
		
		
		
		
		
		
		
		
		
		
		){

	
	
	
}
	