package com.weather.weatherApp.constans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constanst {
	// API için sabit değerler
	public static String API_URL;

	public static String API_KEY;

	public static String ACCESS_KEY_PARAM = "?access_key=";

	public static String QUERY_KEY_PARAM = "&query=";


	//@Value anotasyonu ile, application.properties dosyasındaki ayar değerlerini buradaki sabitlere atar
	@Value("${weather-stack.api-url}")
	public void setAPI_URL(String apiUrl) {
		API_URL = apiUrl;
	}

	@Value("${weather-stack.api-key}")
	public void setAPI_KEY(String apiKey) {
		API_KEY = apiKey;
	}
}
