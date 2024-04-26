package com.curso.java.services;

import java.util.List;

import com.curso.java.models.Articulo;

public interface ArticuloService {
	List<Articulo> obtenerArticulos();
	
	Articulo guardarArticulo(Articulo articulo);

	Articulo obtenerArticuloPorId (Long id);

	Articulo actualizarArticulo(Articulo articulo);
}
