package com.curso.java.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size (min=1, message="El Rol debe tener al menos 1 caracter")
	private String rol;
	
	@Size(min=5, max=15,message ="La Descripcion debe tener al menos 5 y máximo 15 caracteres") 
	private String descripcion;

	public long getId() {
		return id;
	}

	public Rol() {

	}

	public Rol(long id, String rol, String descripcion) {
		super();
		this.id = id;
		this.rol = rol;
		this.descripcion = descripcion;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
