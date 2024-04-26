package com.curso.java.services;

import java.util.List;

import com.curso.java.dtos.UsuarioDto;
import com.curso.java.dtos.UsuarioResponse;
import com.curso.java.models.Usuario;

public interface UsuarioService {
	List<UsuarioResponse> obtenerUsuarios();
	
	List<UsuarioDto> findAll();
	
	UsuarioResponse guardarUsuario(Usuario usuario);
	
	UsuarioResponse actualizarUsuario(Usuario usuario);
	
	Boolean eliminarUsuario(Long id);
	
}
