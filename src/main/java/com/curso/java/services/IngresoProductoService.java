package com.curso.java.services;

import java.util.List;

import com.curso.java.models.IngresoProducto;

public interface IngresoProductoService {
	List<IngresoProducto> obtenerIngresoProductos();
	
	IngresoProducto guardarIngresoProducto(IngresoProducto ingresoProducto);

	IngresoProducto obtenerIngresoProductoPorId (Long id);
}
