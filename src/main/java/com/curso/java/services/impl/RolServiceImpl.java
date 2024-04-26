package com.curso.java.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.java.models.Rol;
import com.curso.java.repositories.RolRepository;
import com.curso.java.services.RolService;

@Service
public class RolServiceImpl implements RolService{
	@Autowired RolRepository rolRepository;
	@Override
	public List<Rol> obtenerRoles(){

		return rolRepository.findAll();
	}
	@Override
	public Rol guardarRol(Rol rol) {
		if(!rolRepository.findByRol(rol.getRol()).isPresent()) {
			return rolRepository.save(rol); 
		}
		return null;
	}
	
	@Override
	public Rol actualizarRol(Rol rol) {
		if(rolRepository.findById(rol.getId()).isPresent()) {
			return rolRepository.save(rol);
		}else {
			return null; 
		}
	}
	
	@Override
	public Boolean eliminarRol(Long id) {
		if(rolRepository.findById(id).isPresent()) {
			rolRepository.deleteById(id);
			return true;
		}
		return false;
	}
	@Override
	public Rol obtenerRolPorId(Long id) {
		return rolRepository.findById(id).orElse(null);
	}
	
}
