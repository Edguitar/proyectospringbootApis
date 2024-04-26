package com.curso.java.models;


import java.math.BigDecimal;
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
import jakarta.validation.constraints.Size;

@Entity
public class Articulo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private  String nombre;
	
	@Size (min = 2, max=150,message="La descripción debe tener min 2 y max 150 caracteres" )
	private  String descripcion;
	
	private  BigDecimal precio;
	
	private  Integer cantidad;
	
	private  Integer descuento;
	
	private  String url;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name="fecha_creacion")
	private  Date fecha;
	
	@ManyToOne
    @JoinColumn(name = "categoria_id")
	private Categoria categoria ;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
}
