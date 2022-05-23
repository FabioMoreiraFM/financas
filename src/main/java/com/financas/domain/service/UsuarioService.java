package com.financas.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.financas.domain.exception.EntidadeEmUsoException;
import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.exception.EnumEntidadeException;
import com.financas.domain.model.Usuario;
import com.financas.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario buscar(Long idUsuario) {
		return usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.Usuarios, idUsuario));
	}
	
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
		
	
	public void remover(Usuario usuario) {
		try {
			usuarioRepository.delete(usuario);
			usuarioRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(EnumEntidadeException.Terceiro, usuario.getId());
		}
	}
	
}
