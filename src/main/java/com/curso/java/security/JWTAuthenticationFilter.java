package com.curso.java.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.curso.java.dtos.UserDto;
import com.curso.java.models.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private JWTUtils jwtUtils;
	
	
	public JWTAuthenticationFilter(JWTUtils jwtUtils) {
		super();
		this.jwtUtils = jwtUtils;
	}

	@Override
	public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		try {
			Usuario usuario=new ObjectMapper().readValue(request.getInputStream(), Usuario.class);	
			
			UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(),new ArrayList<>());
			return getAuthenticationManager().authenticate(upat);
		} catch (Exception e) {

			System.out.println("attemptAuthentication------------>"+e.getMessage());
throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			org.springframework.security.core.Authentication authResult) throws IOException, ServletException {
		
		Usuario usuario=(Usuario) authResult.getPrincipal();
		String token=jwtUtils.genereteJwtToken(usuario);
		
		String nombres=usuario.getNombres().concat(" ").concat(usuario.getApellidos());
		UserDto userDto = new UserDto(nombres, usuario.getUsername(),token, usuario.getRol().getDescripcion());
		
		ObjectWriter ow= new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonRespString=ow.writeValueAsString(userDto);
		response.addHeader(Constants.HEADER_AUTHORIZACION_KEY,Constants.TOKEN_BEARER_PREFIX+token);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(jsonRespString);
		response.getWriter().flush();
		
	}
}
