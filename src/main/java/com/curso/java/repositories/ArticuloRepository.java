package com.curso.java.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curso.java.models.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
	Optional<Articulo> findByNombre(@Param("nombre") String Nombre);
}
