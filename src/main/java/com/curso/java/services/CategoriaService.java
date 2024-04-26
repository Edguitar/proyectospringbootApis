package com.curso.java.services;

import java.util.List;

import com.curso.java.models.Categoria;

public interface CategoriaService {
	
	List<Categoria> obtenerCategorias();
	
	Categoria guardarCategoria(Categoria categoria);

	Categoria ObtenerCategoriaPorId (String Id);
}
