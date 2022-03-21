package com.henrique.controlefinancas.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.henrique.controlefinancas.exception.RegraNegocioException;
//import com.henrique.controlefinancas.model.entity.Usuario;
import com.henrique.controlefinancas.model.repository.UsuarioRepository;
import com.henrique.controlefinancas.service.impl.UsuarioServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService service;
	
		
	//@Autowired
	@MockBean
	UsuarioRepository repository;
	
	@BeforeEach
	public void setUp() {
		//repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
		
	}
	
	@Test
	public void deveValidarEmail() {
		Assertions.assertDoesNotThrow(() -> {
			//cenário
			
			//UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
			
			//repository.deleteAll();
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
			
			//ação
			service.validarEmail("email@email.com");
			
		});
		
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		Assertions.assertThrows(RegraNegocioException.class, () -> {
			//cenário
			/*
			Usuario usuario = Usuario
					.builder()
					.nome("usuario")
					.email("email@email.com")
					.build();
			repository.save(usuario);
			*/
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
			
			//ação
			service.validarEmail("email@email.com");
		});		
		
	}

}
