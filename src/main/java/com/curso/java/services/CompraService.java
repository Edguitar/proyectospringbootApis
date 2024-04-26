package com.curso.java.services;

import java.util.List;

import com.curso.java.models.Compra;

public interface CompraService {
	List<Compra> obtenerCompras();
	
	Compra guardarCompra(Compra compra);

}
