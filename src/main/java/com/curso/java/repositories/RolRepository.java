package com.curso.java.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curso.java.models.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	
	@Query("SELECT r FROM Rol r WHERE r.rol = :rol")
	Optional<Rol> findByRol(@Param("rol") String rol);
}
