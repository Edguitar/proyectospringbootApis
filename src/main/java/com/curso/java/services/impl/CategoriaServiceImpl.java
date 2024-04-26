package com.curso.java.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.java.models.Categoria;
import com.curso.java.repositories.CategoriaRepository;
import com.curso.java.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	@Autowired CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> obtenerCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria guardarCategoria(Categoria categoria) {
		Optional<Categoria>  categoriaExiste = categoriaRepository.findById(categoria.getId());
		if (!categoriaExiste.isPresent()) {
			return categoriaRepository.save(categoria);
		}
		return null;
	}

	@Override
	public Categoria ObtenerCategoriaPorId(String id) {
		return categoriaRepository.findById(id).orElse(null);
	}



}
