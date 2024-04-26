package com.curso.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.java.models.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
