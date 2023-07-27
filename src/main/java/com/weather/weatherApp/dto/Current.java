package com.weather.weatherApp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Current(
		
		@JsonProperty("observation_time")
        String observationTime,
        Integer temperature,
        @JsonProperty("weather_code")
        Integer weatherCode,
        @JsonProperty("weather_icons")
        List<String> weatherIcons,
        @JsonProperty("weather_descriptions")
        List<String> weatherDescriptions,
        @JsonProperty("wind_speed")
        Integer windSpeed,
        @JsonProperty("wind_degree")
        Integer windDegree,
        @JsonProperty("wind_dir")
        String windDirection,
        Integer pressure,
        Integer precip,
        Integer humidity,
        @JsonProperty("cloudcover")
        String cloudCover,
        @JsonProperty("feelslike")
        String feelsLike,
        @JsonProperty("uv_index")
        String uvIndex,
        String visibility,
        @JsonProperty("is_day")
        String isDay )



{

	
	
	
}
