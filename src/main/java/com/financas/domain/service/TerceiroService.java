package com.financas.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Terceiro getByIdTerceiro(Long terceiroId) { 
		return terceiroRepository.findById(terceiroId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.Terceiro, terceiroId));
	}
	
	@Transactional
	public Terceiro salvarTerceiro(Terceiro terceiro) {
		return terceiroRepository.save(terceiro);
	}
		
	@Transactional
	public void remover(Terceiro terceiro) {
		terceiroRepository.delete(terceiro);
	}
	
}
