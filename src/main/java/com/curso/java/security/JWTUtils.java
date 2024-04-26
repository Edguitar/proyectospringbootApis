package com.curso.java.security;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.curso.java.models.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

	public String genereteJwtToken(Usuario usuario)
	{
		
		Collection<?> roles=usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		String tk=Jwts
				.builder()
				.setIssuedAt(new Date())
				.setIssuer(Constants.ISSUER_INFO)
				.setSubject(usuario.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+Constants.TOKEN_EXPIRATION_TIME))
				.claim(Constants.AUTHORITIES,roles)
				.signWith(Keys.hmacShaKeyFor(Constants.SUPER_SECRET_KEY.getBytes()),SignatureAlgorithm.HS512)
				.compact();
		System.out.println("tk----------------------->"+tk);
		
		return tk;
	}
	
	
	public String  getUserNameFromJwtToken(String token) {
		
		try {
			Claims claims=Jwts.parserBuilder()
					.setSigningKey(Constants.SUPER_SECRET_KEY.getBytes())
					.build()
					.parseClaimsJws(token).getBody();
			
			String email=claims.getSubject();
			
			return email;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public boolean validateJwtToken(String authToken) {
		System.out.println("authToken " + authToken);
		try {
			Jwts.parserBuilder()
			.setSigningKey(Constants.SUPER_SECRET_KEY.getBytes())
			.build()
			.parseClaimsJws(authToken);
			return true;
		} catch (SecurityException e) {
			System.out.println("Invalid JWT signature: {} " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token: {} " + e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("JWT token is expired: {} " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("JWT token is unsupported: {} " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty: {} " + e.getMessage());
		}
 
		return false;
	}
}
