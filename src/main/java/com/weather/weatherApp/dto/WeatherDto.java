package com.weather.weatherApp.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.weather.weatherApp.model.WeatherEntity;

// WeatherDto sınıfı, WeatherEntity sınıfının verilerini taşımak için kullanılan bir DTO (Data Transfer Object) sınıfıdır
public record WeatherDto(
		String cityName,
		String country,
		Integer temperature,
		List<String> weatherDescriptions,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
		LocalDateTime updatedTime,
		List<String> weatherIcons,
		Integer windSpeed,
		Integer humidity
) {
	// WeatherEntity nesnesini WeatherDto'ya dönüştüren dönüştürme metodu
	public static WeatherDto convert(WeatherEntity from) {
		return new WeatherDto(
				from.getCityName(),
				from.getCountry(),
				from.getTemperature(),
				from.getWeatherDescriptions(),
				from.getUpdatedTime(),
				from.getWeatherIcons(),
				from.getWindSpeed(),
				from.getHumidity()
		);
	}
}
