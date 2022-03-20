package com.henrique.controlefinancas.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.henrique.controlefinancas.exception.ErroAutenticacao;
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
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não Encontrado para o email o email informado.");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		return usuario.get();
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
