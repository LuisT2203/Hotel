package com.hotel.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UsuarioDetailsService();
	}
	@Bean
	BCryptPasswordEncoder passwordEndercoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider =
				new DaoAuthenticationProvider();
		authenticationProvider.
			setUserDetailsService(userDetailsService());
		authenticationProvider.
			setPasswordEncoder(passwordEndercoder());
		
		return authenticationProvider;
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		http.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/index1")
				.authenticated()
				.anyRequest().permitAll()
				).csrf(AbstractHttpConfigurer::disable)
//				.formLogin(
//						login -> login
//						.loginPage("/login")
//						.usernameParameter("correo")
//						.passwordParameter("clave")
//						.defaultSuccessUrl("/index1")
//						.permitAll()
//						)
				.logout(
						logout -> logout.logoutSuccessUrl("/")
						.permitAll()
						)
		;
		
		
		return http.build();
	}

}
