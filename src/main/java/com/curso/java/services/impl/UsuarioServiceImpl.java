package com.curso.java.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.java.dtos.UsuarioDto;
import com.curso.java.dtos.UsuarioResponse;
import com.curso.java.mappers.UsuarioMapper;
import com.curso.java.models.Usuario;
import com.curso.java.repositories.UsuarioRepository;
import com.curso.java.services.RolService;
import com.curso.java.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private RolService rolService;
	@Override
	public List<UsuarioResponse> obtenerUsuarios(){
		List<UsuarioResponse> listaUsuarios = new ArrayList<>();
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		if(!usuarios.isEmpty()) {
			for(Usuario usuario: usuarios) {
				listaUsuarios.add(doDto(usuario));
			}
		}
		return listaUsuarios;
	}
	
	@Override
	public List<UsuarioDto> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(user -> usuarioMapper.toDto(user)).collect(Collectors.toList());
	}

	@Override
	public UsuarioResponse guardarUsuario(Usuario usuario) {
		usuario.setFecha(new Date());
		if(!usuarioRepository.findByemail(usuario.getEmail()).isPresent()) {
			Usuario newUsuario= usuarioRepository.save(usuario); 
			newUsuario.setRol(rolService.obtenerRolPorId(usuario.getRol().getId()));
			return doDto(newUsuario);
		}
		return null;
	}
	@Override
	public UsuarioResponse actualizarUsuario(Usuario usuario) {
		usuario.setFecha(new Date());
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			Usuario updateUsuario = usuarioRepository.save(usuario);
			return doDto(updateUsuario);
		}else {
			return null; 
		}
	}
	
	@Override
	public Boolean eliminarUsuario(Long id) {
		if(usuarioRepository.findById(id).isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private UsuarioResponse doDto(Usuario usuario) {
		return  new UsuarioResponse(usuario.getEmail(), usuario.getNombres() + " " + usuario.getApellidos(), usuario.getTelefono(), usuario.getDireccion(), usuario.getRol().getRol());
	}
}
