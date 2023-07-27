package com.weather.weatherApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	// RestTemplate, dış servislerle HTTP istekleri yapmak için kullanılan Spring bileşenidir.
	// Bu metot, RestTemplate bean'ini uygulama bağlamına ekler.
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
