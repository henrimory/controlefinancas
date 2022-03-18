package com.henrique.controlefinancas.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.henrique.controlefinancas.exception.RegraNegocioException;
import com.henrique.controlefinancas.model.entity.Usuario;
import com.henrique.controlefinancas.model.repository.UsuarioRepository;

import com.henrique.controlefinancas.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	
	private UsuarioRepository repository;	
	
	public UsuarioServiceImpl(UsuarioRepository  repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		
		return null;
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Ja existe um usuário cadastrado com este email.");
		}
		
	}
	
	

}
