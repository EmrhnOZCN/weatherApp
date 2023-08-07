package com.weather.weatherApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.weather.weatherApp.model.MembersEntity;

//. Bu annotasyon, belirli bir sınıfta JSON verisini Java nesnesine dönüştürürken,
// dönüştürülmeyen veya sınıfta tanımlanmamış ekstra alanları görmezden gelmek için kullanılır.
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(
		Request request,
		
		Location location,
		
		Current current
		

		
		
		
		
		
		
		
		
		){

	
	
	
}
	