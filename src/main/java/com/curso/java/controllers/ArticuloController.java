package com.curso.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.java.models.Articulo;
import com.curso.java.services.ArticuloService;
import com.curso.java.utils.WebUtil;

@RestController
@RequestMapping("/articulo")
public class ArticuloController extends BaseController{

	@Autowired
	private ArticuloService articuloService;

	@GetMapping()
	public ResponseEntity<?>obtener(){
	  List<Articulo> articulos = articuloService.obtenerArticulos();
	  if (articulos==null||articulos.isEmpty()) {
		  return ResponseEntity.noContent().build();
	  }
		return ResponseEntity.status(HttpStatus.OK).body(articulos);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody @Validated Articulo articulo, BindingResult result) {
		if(result.hasErrors()){
			return WebUtil.getErrors(result);
		}
		
		Articulo newArticulo= articuloService.guardarArticulo(articulo);
		if(newArticulo==null) {
			throw new DataIntegrityViolationException("Ya existe una Articulo:" + articulo.getNombre());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newArticulo);
	}


}
