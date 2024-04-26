package com.curso.java.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

	private long id;
	
	private  String email;
	
	private  String nombres;
	
	private  String apellidos;
	
	private  String password;
	
	private  String telefono;
	
	private  String direccion;
	
	private String rol;
}
