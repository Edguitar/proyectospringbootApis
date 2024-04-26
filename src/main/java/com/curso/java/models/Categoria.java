package com.curso.java.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Categoria {
	@Id
	private String id;

	@NotNull
	@Size (min=1, message="El nombre debe tener al menos 1 caracter")
	private String nombre;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
