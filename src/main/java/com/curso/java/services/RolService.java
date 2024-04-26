package com.curso.java.services;

import java.util.List;

import com.curso.java.models.Rol;


public interface RolService {
	List<Rol> obtenerRoles();
	
	Rol guardarRol(Rol rol);

	Rol actualizarRol(Rol rol);
	
	Boolean eliminarRol (Long id);
	
	Rol obtenerRolPorId (Long id);
	
}
