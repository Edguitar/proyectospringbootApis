package com.curso.java.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.java.models.IngresoProducto;
import com.curso.java.repositories.IngresoProductoRepository;
import com.curso.java.services.IngresoProductoService;

@Service
public class IngresoProductoServiceImpl implements IngresoProductoService {
	@Autowired
	IngresoProductoRepository ingresoProductoRepository;

	@Override
	public List<IngresoProducto> obtenerIngresoProductos() {
		return ingresoProductoRepository.findAll();
	}

	@Override
	public IngresoProducto guardarIngresoProducto(IngresoProducto ingresoProducto) {
		ingresoProducto.setFecha(new Date());
		
//		Optional<IngresoProducto> articuloExiste = ingresoProductoRepository.getDetalle(ingresoProducto.getId());
//		if (!articuloExiste.isPresent()) {
//			return ingresoProductoRepository.save(ingresoProducto);
//		}
		return ingresoProductoRepository.save(ingresoProducto);
	}



	@Override
	public IngresoProducto obtenerIngresoProductoPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



}
