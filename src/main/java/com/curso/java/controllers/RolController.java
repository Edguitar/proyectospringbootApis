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

import com.curso.java.models.Rol;
import com.curso.java.services.RolService;
import com.curso.java.utils.WebUtil;

@RestController
@RequestMapping("/rol")
public class RolController extends BaseController{

	@Autowired
	private RolService rolservice;

	@GetMapping()
	public ResponseEntity<?>obtener(){
	  List<Rol> roles = rolservice.obtenerRoles();
	  if (roles==null||roles.isEmpty()) {
		  return ResponseEntity.noContent().build();
	  }
		return ResponseEntity.ok(roles);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody @Validated Rol rol, BindingResult result) {
		if(result.hasErrors()){
			return WebUtil.getErrors(result);
		}
		
		Rol newRol= rolservice.guardarRol(rol);
		if(newRol==null) {
			throw new DataIntegrityViolationException("Ya existe Un Rol:" + rol.getRol());
			//return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya esxiste un Rol");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newRol);
	}
	
	@PutMapping
	public ResponseEntity<?> actualizar(@RequestBody Rol rol) {
		Rol rolUpdate = rolservice.actualizarRol(rol);
		if (rolUpdate==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya esxiste un Rol");
		}
		return ResponseEntity.status(HttpStatus.OK).body(rolUpdate); 
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> eliminar(@PathVariable(value ="id") Long id) {
		if(rolservice.eliminarRol(id)) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
	}


}
