package com.curso.java.security;

import java.io.IOException;
import java.net.http.HttpRequest;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.JstlUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtils jstlUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token= parseJwt(request);
			if(token !=null && jstlUtils.validateJwtToken(token)) {
				String username=jstlUtils.getUserNameFromJwtToken(token);
				UserDetails userDetails=userDetailsService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} catch (Exception e) {
			System.out.println("No se puede Autenticar"+e);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader(Constants.HEADER_AUTHORIZACION_KEY);
	    if (headerAuth != null && !headerAuth.isEmpty() && headerAuth.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
	        return headerAuth.substring(7);
	    } else {
	        return null;
	    }
	}


	
}
