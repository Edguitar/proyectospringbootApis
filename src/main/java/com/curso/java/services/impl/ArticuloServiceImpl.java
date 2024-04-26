package com.curso.java.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.java.models.Articulo;
import com.curso.java.repositories.ArticuloRepository;
import com.curso.java.services.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService{
	@Autowired ArticuloRepository articuloRepository;
	
	@Override
	public List<Articulo> obtenerArticulos() {
		return articuloRepository.findAll();
	}

	@Override
	public Articulo guardarArticulo(Articulo articulo) {
		articulo.setFecha(new Date());
		Optional <Articulo> articuloExiste=articuloRepository.findByNombre(articulo.getNombre());
		if (!articuloExiste.isPresent()) {
			return articuloRepository.save(articulo);
		}
		return null;
	}

	@Override
	public Articulo obtenerArticuloPorId(Long id) {
		return articuloRepository.findById(id).orElse(null);
	}

	@Override
	public Articulo actualizarArticulo(Articulo articulo) {
		return	articuloRepository.save(articulo);
	}



}
