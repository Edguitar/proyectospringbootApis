package com.curso.java.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curso.java.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Optional<Usuario> findByemail(@Param("email") String email);
}
