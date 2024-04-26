package com.curso.java.models;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name="fecha_creacion")
	private  Date fecha;
	
	private  BigDecimal subtotal;
	
	private  BigDecimal iva;
	
	private  BigDecimal total;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private  Compra compra;
	
	private  String nroDocumento;
	
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	private List<DetalleCompra> detalleCompraList;	
	
	public List<DetalleCompra> getDetalleCompraList() {
		return detalleCompraList;
	}

	public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
		this.detalleCompraList = detalleCompraList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
	
	
	
}
