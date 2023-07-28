package com.weather.weatherApp.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "weatherList")
public class WeatherEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;


	@ManyToOne
	@JoinColumn(name = "user_id")
	private MembersEntity membersEntity;
	private String requestedCityName;
	
	private String cityName;
	
	private String country;
	
	private Integer temperature;
	
	private LocalDateTime updatedTime;
	
	
	
	private LocalDateTime responseLocalTime;
	
	private List<String> weatherDescriptions;

	
	private List<String> weatherIcons;
	
	private Integer windSpeed;
	
	Integer humidity;
	
	
	public WeatherEntity(String id, String requestedCityName, String cityName, String country, Integer temperature,
			LocalDateTime updatedTime,  LocalDateTime responseLocalTime,
			List<String> weatherDescriptions,List<String> weatherIcons,Integer windSpeed,Integer humidity,MembersEntity membersEntity) {
		super();
		this.id = id;
		this.requestedCityName = requestedCityName;
		this.cityName = cityName;
		this.country = country;
		this.temperature = temperature;
		this.updatedTime = updatedTime;
		
		this.responseLocalTime = responseLocalTime;
		this.weatherDescriptions = weatherDescriptions;
		
		this.weatherIcons = weatherIcons;
		
		this.windSpeed = windSpeed;
		
		this.humidity = humidity;

		this.membersEntity = membersEntity;
	}

	public WeatherEntity( String requestedCityName, String cityName, String country, Integer temperature,
			List<String> weatherDescriptions, LocalDateTime responseLocalTime,
			LocalDateTime updatedTime,List<String> weatherIcons,Integer windSpeed,Integer humidity,MembersEntity membersEntity) {
		this.id = "";
		this.requestedCityName = requestedCityName;
		this.cityName = cityName;
		this.country = country;
		this.temperature = temperature;
		this.updatedTime = updatedTime;
		this.responseLocalTime = responseLocalTime;
		this.weatherDescriptions = weatherDescriptions;
		this.weatherIcons = weatherIcons;
		this.windSpeed = windSpeed;
		
		this.humidity= humidity;
		this.membersEntity=membersEntity;
	}

	public WeatherEntity() {
		
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestedCityName() {
		return requestedCityName;
	}

	public void setRequestedCityName(String requestedCityName) {
		this.requestedCityName = requestedCityName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public LocalDateTime getResponseLocalTime() {
		return responseLocalTime;
	}

	public void setResponseLocalTime(LocalDateTime responseLocalTime) {
		this.responseLocalTime = responseLocalTime;
	}

	public List<String> getWeatherDescriptions() {
		return weatherDescriptions;
	}

	public void setWeatherDescriptions(List<String> weatherDescriptions) {
		this.weatherDescriptions = weatherDescriptions;
	}

	public List<String> getWeatherIcons() {
		return weatherIcons;
	}

	public void setWeatherIcons(List<String> weatherIcons) {
		this.weatherIcons = weatherIcons;
	}

	public Integer getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Integer windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}


	public MembersEntity getMembersEntity() {
		return membersEntity;
	}

	public void setMembersEntity(MembersEntity membersEntity) {
		this.membersEntity = membersEntity;
	}
}
