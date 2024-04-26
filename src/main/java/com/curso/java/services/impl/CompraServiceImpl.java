package com.curso.java.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.java.models.Articulo;
import com.curso.java.models.Compra;
import com.curso.java.models.DetalleCompra;
import com.curso.java.repositories.CompraRepository;
import com.curso.java.services.ArticuloService;
import com.curso.java.services.CompraService;

@Service
public class CompraServiceImpl implements CompraService {
	@Autowired
	CompraRepository compraRepository;
	@Autowired
	ArticuloService articuloService;

	@Override
	public List<Compra> obtenerCompras() {
		return compraRepository.findAll();
	}

	@Override
	public Compra guardarCompra(Compra compra) {
		boolean stock = true;
		compra.setFecha(new Date());
		compra.setNroDocumento("Documento - " + compraRepository.count() + 1);
		for (DetalleCompra detalle : compra.getDetalleCompraList()) {
			Articulo articulo = articuloService.obtenerArticuloPorId(detalle.getArticulo().getId());
			if (articulo != null && articulo.getCantidad()>=detalle.getCantidad()) {
				articulo.setCantidad(articulo.getCantidad() - detalle.getCantidad());
				articuloService.actualizarArticulo(articulo);
			}else {
				stock=false;
			}
			detalle.setCompra(compra);
		}
		if(stock) {
			return compraRepository.save(compra);
		}
		return null;
	}
}
