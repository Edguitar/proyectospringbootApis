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

import com.curso.java.models.IngresoProducto;
import com.curso.java.services.IngresoProductoService;
import com.curso.java.utils.WebUtil;

@RestController
@RequestMapping("/ingresoProducto")
public class IngresoProductoController extends BaseController{

	@Autowired
	private IngresoProductoService ingresoProductoService;

	@GetMapping()
	public ResponseEntity<?>obtener(){
	  List<IngresoProducto> ingresoProducto = ingresoProductoService.obtenerIngresoProductos();
	  if (ingresoProducto==null||ingresoProducto.isEmpty()) {
		  return ResponseEntity.noContent().build();
	  }
		return ResponseEntity.status(HttpStatus.OK).body(ingresoProducto);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody @Validated IngresoProducto compra, BindingResult result) {
		if(result.hasErrors()){
			return WebUtil.getErrors(result);
		}
		
		IngresoProducto newIngresoProducto= ingresoProductoService.guardarIngresoProducto(compra);
		if(newIngresoProducto==null) {
			throw new DataIntegrityViolationException("Error en ingreso producto");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newIngresoProducto);
	}


}
