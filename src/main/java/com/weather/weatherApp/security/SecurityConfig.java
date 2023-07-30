package com.weather.weatherApp.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.weather.weatherApp.constans.CustomAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	// Özel kimlik doğrulama başarı işleyiciyi enjekte eden bir yapılandırıcı
	public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
		this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// JDBC ile kullanıcı veritabanına erişimi sağlayacak UserDetailsManager'ı yapılandırma
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource,PasswordEncoder passwordEncoder) {
		// Veritabanı kaynaklarını kullanarak JdbcUserDetailsManager oluşturma
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		// Kullanıcı adı ve şifre sorgularını belirleme
		jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_name, pw, active FROM members WHERE user_name = ?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_name, role FROM roles WHERE user_name = ?");



		return jdbcUserDetailsManager;
	}

	// Spring Security'nin HTTP güvenlik yapılandırması için bir filtre zinciri yapılandırma
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(configurer ->
						configurer
								// "/weather" yoluna erişim için gereken rolleri belirleme
								.requestMatchers("/weather").hasAnyRole("PREMIUM", "FREE", "ADMIN")
								// "/showAdminPanel" yoluna erişim için "ADMIN" rolü gerekliliğini belirleme
								.requestMatchers("/showAdminPanel").hasRole("ADMIN")
								// "/registerPost" yoluna herkese açık (yetki gerektirmez) erişim izni verme
								.requestMatchers("/registerPost").permitAll()
								// "/register" yoluna herkese açık (yetki gerektirmez) erişim izni verme
								.requestMatchers("/register").permitAll()
								// "/signUp" yoluna herkese açık (yetki gerektirmez) erişim izni verme
								.requestMatchers("/signUp").permitAll()
								// Diğer tüm istekler için kimlik doğrulama gerekliliğini belirleme
								.anyRequest().authenticated()
				)
				// Özel form tabanlı kimlik doğrulama yapılandırması
				.formLogin(form ->
						form
								// Özel giriş sayfası olarak "/showLogin" sayfasını belirleme
								.loginPage("/showLogin")
								// Kullanıcının kimlik bilgilerini doğrulamak için kullanılacak URL
								.loginProcessingUrl("/authenticateTheUser")
								// Kimlik doğrulama başarılı olduğunda çalışacak özel işleyiciyi belirleme
								.successHandler(customAuthenticationSuccessHandler)
								// Giriş sayfasına herkese açık (yetki gerektirmez) erişim izni verme
								.permitAll()
				)
				// Çıkış işlemi yapılandırması, çıkış herkese açık (yetki gerektirmez) olacak
				.logout(logout -> logout.permitAll())

				// Erişim reddedildiğinde yönlendirilecek sayfayı belirleme
				.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

		// CSRF korumasını devre dışı bırakma (bazı senaryolarda devre dışı bırakılabilir, ancak genellikle önerilmez)
		// http.csrf().disable();

		// Basit kimlik doğrulama (HTTP temel kimlik doğrulama) kullanarak istemci tarafından
		// kullanıcı adı ve şifre ile doğrulama yapılmasını sağlama
		// http.httpBasic(Customizer.withDefaults());

		// Oluşturulan yapılandırmayı döndürme
		return http.build();
	}
}
