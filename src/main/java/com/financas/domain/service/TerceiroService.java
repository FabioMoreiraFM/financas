package com.financas.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.financas.domain.exception.EntidadeEmUsoException;
import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.exception.EnumEntidadeException;
import com.financas.domain.model.Terceiro;
import com.financas.domain.repository.TerceiroRepository;

@Service
public class TerceiroService {

	@Autowired
	private TerceiroRepository terceiroRepository;
	
	public List<Terceiro> listar() { 
		return terceiroRepository.findAll();
	}

	public Terceiro buscar(Long terceiroId) { 
		return terceiroRepository.findById(terceiroId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.Terceiro, terceiroId));
	}
	
	@Transactional
	public Terceiro salvar(Terceiro terceiro) {
		return terceiroRepository.save(terceiro);
	}
		
	@Transactional
	public void remover(Terceiro terceiro) {
		try {
			terceiroRepository.delete(terceiro);
			terceiroRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(EnumEntidadeException.Terceiro, terceiro.getId());
		}
	}
	
}
