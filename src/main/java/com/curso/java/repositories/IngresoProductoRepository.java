package com.curso.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.java.models.IngresoProducto;

@Repository
public interface IngresoProductoRepository extends JpaRepository<IngresoProducto, Long> {
}
