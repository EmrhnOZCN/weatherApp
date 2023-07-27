package com.weather.weatherApp.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.weather.weatherApp.constans.CustomAuthenticationSuccessHandler;
@Configuration
public class SecurityConfig {
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    
    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager()   {
//		
//		UserDetails john = User.builder()
//				.username("abc@gmail.com")
//				.password("{noop}1234")
//				.roles("FREE")
//				.build();
//		//şifrenin doğrulama gerektirmemesi için {noop} ön eki kullanılır
//		
//		UserDetails maria = User.builder()
//				.username("abcd@gmail.com")
//				.password("{noop}12345")
//				.roles("FREE","VIP")
//				.build();
//		
//		
//		
//		
//		
//		
//		
//		
//		return new InMemoryUserDetailsManager(john,maria);
//	}
    
    
    @Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		 jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_name,pw,active FROM members WHERE user_name = ?");
		 
		 
		 jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT  user_name,role FROM roles WHERE user_name = ?");
		 
		
		 
		 return  jdbcUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		http.authorizeHttpRequests(configurer -> 
		 configurer
	        .requestMatchers("/weather").hasAnyRole("PREMIUM","FREE","ADMIN")
	        .requestMatchers("/showAdminPanel").hasRole("ADMIN")
	        .requestMatchers("/registerPost").permitAll()
	        .requestMatchers("/register").permitAll()
	        .requestMatchers("/signUp").permitAll()
	        
         
	        .anyRequest().authenticated()
	)
								.formLogin(form ->
										form
											.loginPage("/showLogin")
											.loginProcessingUrl("/authenticateTheUser")
											.successHandler(customAuthenticationSuccessHandler)
											.permitAll())
								
								.logout(logout -> logout.permitAll())
								
								
								.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"))
								
								
								
								
								;	
								
		
		
		return http.build();
		
//		//.csrf(csrf -> csrf.disable()) metodu, Cross-Site Request Forgery (CSRF) 
//		korumasını devre dışı bırakır. CSRF saldırılarına karşı koruma sağlamak 
//		için genellikle bu ayarın etkinleştirilmesi önerilir, ancak bazı 
//		senaryolarda devre dışı bırakılabilir.
		
//		
//		.httpBasic(Customizer.withDefaults()) metodu, basit kimlik doğrulama (HTTP temel kimlik doğrulama) kullanarak
//		istemci tarafından kullanıcı adı ve şifre ile doğrulama yapılmasını sağlar.
	}
}
