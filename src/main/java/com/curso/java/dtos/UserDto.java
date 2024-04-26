package com.curso.java.dtos;

public class UserDto {

	private String nombres;
	
	private String username;
	
	private String token;
	
	private String rol;

	public UserDto(String nombres, String username, String token, String rol) {
		super();
		this.nombres = nombres;
		this.username = username;
		this.token = token;
		this.rol = rol;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
