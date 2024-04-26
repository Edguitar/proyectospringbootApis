package com.curso.java.models;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class IngresoProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private  Integer cantidad;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name="fecha_ingreso")
	private  Date fecha;
	
	private  Integer total;
	
	@ManyToOne
    @JoinColumn(name = "articulo_id")
	private Articulo articulo ;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
}
