package com.curso.java.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private JWTAuthorizationFilter authorizationFilter;
	
	@Autowired
	private JWTAuthEntryPoint jwtAuthEntryPoint;

	@Bean
	SecurityFilterChain filterChain (HttpSecurity http,AuthenticationManager authManager) throws Exception {
		
		JWTAuthenticationFilter jwtAuthenticationFilter=new JWTAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl(Constants.LOGIN_URL);
		
		return http
				.csrf(crsf->crsf.disable())
				.exceptionHandling(exception->exception.authenticationEntryPoint(jwtAuthEntryPoint))
				.authorizeHttpRequests(authorize->authorize
						//.requestMatchers("/","/login").permitAll()
						.requestMatchers(HttpMethod.POST,"/usuario").permitAll()
						.anyRequest().authenticated())
//				.httpBasic(withDefaults())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
	
//	@Bean
//	UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager= new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles()
//				.build());
//		
//		return manager;
//	}
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder=http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
		
		return authenticationManagerBuilder.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
