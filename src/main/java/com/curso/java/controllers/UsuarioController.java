package com.curso.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.java.dtos.UsuarioDto;
import com.curso.java.dtos.UsuarioResponse;
import com.curso.java.models.Usuario;
import com.curso.java.services.UsuarioService;
import com.curso.java.utils.WebUtil;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends BaseController{
	@Autowired
	private UsuarioService usuarioService;
	


	@GetMapping()
	public ResponseEntity<?>obtener(){
	  List<UsuarioResponse> usuarios = usuarioService.obtenerUsuarios();
	  if (usuarios==null||usuarios.isEmpty()) {
		  return ResponseEntity.noContent().build();
	  }
		return ResponseEntity.ok(usuarios);
	}
	
	// Mapeando el UsuarioDto
		@GetMapping("/mapper")
		public ResponseEntity<?> Obtner()
		{
			List<UsuarioDto> usuarios = usuarioService.findAll();
			if(usuarios == null || usuarios.isEmpty())
			{
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(usuarios);
		}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody @Validated Usuario usuario, BindingResult result) {
		if(result.hasErrors()){
			return WebUtil.getErrors(result);
		}
		UsuarioResponse newUsuario= usuarioService.guardarUsuario(usuario);
		if(newUsuario==null) {
			throw new DataIntegrityViolationException("Ya existe un Usuario con el  EMAIL:" + usuario.getEmail());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
	}
	
	@PutMapping
	public ResponseEntity<?> actualizar(@RequestBody Usuario usuario) {
		UsuarioResponse usuarioUpdate = usuarioService.actualizarUsuario(usuario);
		if (usuarioUpdate==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario que desea actualizar no existe");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioUpdate); 
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> eliminar(@PathVariable(value ="id") Long id) {
		if(usuarioService.eliminarUsuario(id)) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
	}

}
