package com.curso.java.models;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Email
	private  String email;
	
	@Size (min = 5, max=20,message="El nombre de usuario debe tener min 5 y max 20 caracteres" )
	private  String nombres;
	
	private  String apellidos;
	
	private  String password;
	
	private  String telefono;
	
	private  String direccion;
	
//	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name="fecha_creacion")
	private  Date fecha;
	@ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
	private  Rol rol;
	
	public Usuario() {
		
	}
	
	
	public Usuario(long id, String email, String nombres, String apellidos, String password, String telefono,
			String direccion, Date fecha, Rol rol) {
		super();
		this.id = id;
		this.email = email;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.password = password;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fecha = fecha;
		this.rol = rol;
	}

	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(getRol().getRol()));
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getEmail();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
