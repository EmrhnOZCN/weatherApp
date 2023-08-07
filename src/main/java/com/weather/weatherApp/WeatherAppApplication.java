package com.weather.weatherApp;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {

		return args1 -> {
			System.out.println("Run");
		};
	}
	
}
