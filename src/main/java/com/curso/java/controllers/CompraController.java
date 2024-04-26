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

import com.curso.java.models.Compra;
import com.curso.java.services.CompraService;
import com.curso.java.utils.WebUtil;

@RestController
@RequestMapping("/compra")
public class CompraController extends BaseController{

	@Autowired
	private CompraService compraService;

	@GetMapping()
	public ResponseEntity<?>obtener(){
	  List<Compra> compras = compraService.obtenerCompras();
	  if (compras==null||compras.isEmpty()) {
		  return ResponseEntity.noContent().build();
	  }
		return ResponseEntity.status(HttpStatus.OK).body(compras);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody @Validated Compra compra, BindingResult result) {
		if(result.hasErrors()){
			return WebUtil.getErrors(result);
		}
		
		Compra newCompra= compraService.guardarCompra(compra);
		if(newCompra==null) {
			throw new DataIntegrityViolationException("Error en la compra revise el stock de sus productos");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newCompra);
	}


}
