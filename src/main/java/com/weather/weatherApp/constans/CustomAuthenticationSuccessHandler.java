package com.weather.weatherApp.constans;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Kimlik doğrulama başarılı olduğunda çalışacak yönlendirme işlemleri

        // Kullanıcının rolüne bağlı olarak yönlendirme yapma
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PREMIUM"))) {
            // Kullanıcının rolü "ROLE_PREMIUM" ise "/weather" yoluna yönlendirz
            response.sendRedirect("/weather");
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_FREE"))) {
            // Kullanıcının rolü "ROLE_FREE" ise "/weather" yoluna yönlendir
            response.sendRedirect("/weather");
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            // Kullanıcının rolü "ROLE_ADMIN" ise "/showAdminPanel" yoluna yönlendir
            response.sendRedirect("/showAdminPanel");
        } else {
            // Diğer tüm durumlarda, yani uygun rol bulunamazsa ana sayfaya ("/") yönlendir
            response.sendRedirect("/");
        }
    }
}
