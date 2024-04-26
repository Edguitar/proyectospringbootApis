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

import com.curso.java.models.Categoria;
import com.curso.java.services.CategoriaService;
import com.curso.java.utils.WebUtil;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends BaseController{

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping()
	public ResponseEntity<?>obtener(){
	  List<Categoria> categorias = categoriaService.obtenerCategorias();
	  if (categorias==null||categorias.isEmpty()) {
		  return ResponseEntity.noContent().build();
	  }
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody @Validated Categoria categoria, BindingResult result) {
		if(result.hasErrors()){
			return WebUtil.getErrors(result);
		}
		
		Categoria newCategoria= categoriaService.guardarCategoria(categoria);
		if(newCategoria==null) {
			throw new DataIntegrityViolationException("Ya existe una Categoria:" + categoria.getNombre());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
	}


}
