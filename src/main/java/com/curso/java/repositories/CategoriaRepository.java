package com.curso.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.java.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String>{

}
