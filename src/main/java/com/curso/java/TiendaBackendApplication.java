package com.curso.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.java.dtos.UsuarioDto;
import com.curso.java.models.Usuario;

@SpringBootApplication
public class TiendaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaBackendApplication.class, args);
		
		Usuario usuario=new Usuario();
		usuario.setNombres("Edgar");
		usuario.setApellidos("Subia");
		usuario.setDireccion("Atun");
		
		System.out.println(usuario);
		
		UsuarioDto usuarioDto=UsuarioDto
				.builder()
				.nombres("Nombres")
				.email("nuevo")
				.build();
		System.out.println(usuarioDto);
	}

}
