package com.curso.java.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.curso.java.dtos.UsuarioDto;
import com.curso.java.models.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	@Mappings({
		@Mapping(target="rol.rol", source= "rol")
	})
	Usuario toEntity(UsuarioDto usuarioDto);
	
	
	@Mappings({
		@Mapping(target="rol", source= "rol.rol")
	})
	UsuarioDto toDto(Usuario entity);
	
}
