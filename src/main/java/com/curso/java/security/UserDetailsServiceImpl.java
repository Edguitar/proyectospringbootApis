package com.curso.java.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curso.java.models.Usuario;
import com.curso.java.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario=  usuarioRepository.findByemail(username)
				.orElseThrow(()->new UsernameNotFoundException ("El usuario con email"+username+"no existe"));
		
		return usuario;
	}
}
