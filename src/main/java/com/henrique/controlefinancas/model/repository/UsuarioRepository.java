package com.henrique.controlefinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.controlefinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
		
	boolean existsByEmail(String email);

}
