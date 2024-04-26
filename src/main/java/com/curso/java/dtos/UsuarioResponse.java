package com.curso.java.dtos;

public class UsuarioResponse {
	
	private String email;
	private String nombres;
	private String telefono;
	private String direccion;
	private String rol;
	
	public UsuarioResponse(String email, String nombre, String telefono, String direccion, String rol) {
		super();
		this.email = email;
		this.nombres = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.rol = rol;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombres;
	}
	public void setNombre(String nombre) {
		this.nombres = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	

}
