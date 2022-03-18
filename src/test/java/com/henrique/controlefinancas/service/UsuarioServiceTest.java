package com.henrique.controlefinancas.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.henrique.controlefinancas.exception.RegraNegocioException;
import com.henrique.controlefinancas.model.entity.Usuario;
import com.henrique.controlefinancas.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveValidarEmail() {
		
		//cenário
		repository.deleteAll();
		
		//ação
		service.validarEmail("email@email.com");
		
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		Assertions.assertThrows(RegraNegocioException.class, () -> {
			//cenário
			Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
			repository.save(usuario);
			
			//ação
			service.validarEmail("email@email.com");
		});		
		
	}

}