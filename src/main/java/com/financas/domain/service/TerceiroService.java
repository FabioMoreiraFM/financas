package com.financas.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.exception.EnumEntidadeException;
import com.financas.domain.model.TerceiroPF;
import com.financas.domain.model.TerceiroPJ;
import com.financas.domain.repository.TerceiroPFRepository;
import com.financas.domain.repository.TerceiroPJRepository;

@Service
public class TerceiroService {

	@Autowired
	private TerceiroPFRepository terceiroPFRepository;
	
	@Autowired
	private TerceiroPJRepository terceiroPJRepository;
	
	public List<TerceiroPF> listarPF() { 
		return terceiroPFRepository.findAll();
	}
	
	public List<TerceiroPJ> listarPJ() { 
		return terceiroPJRepository.findAll();
	}
	
	public TerceiroPF getByIdTerceiroPF(Long terceiroPfId) { 
		return terceiroPFRepository.findById(terceiroPfId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.TerceiroPF, terceiroPfId));
	}
	
	public TerceiroPJ getByIdTerceiroPJ(Long terceiroPJId) { 
		return terceiroPJRepository.findById(terceiroPJId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.TerceiroPJ, terceiroPJId));
	}
	
	@Transactional
	public TerceiroPF salvarTerceiroPF(TerceiroPF terceiroPF) {
		return terceiroPFRepository.save(terceiroPF);
	}
		
	@Transactional
	public TerceiroPJ salvarTerceiroPJ(TerceiroPJ terceiroPJ) {
		return terceiroPJRepository.save(terceiroPJ);
	}
	
	@Transactional
	public void removerPF(TerceiroPF terceiroPF) {
		terceiroPFRepository.delete(terceiroPF);
	}
	
	@Transactional
	public void removerPJ(TerceiroPJ terceiroPJ) {
		terceiroPJRepository.delete(terceiroPJ);
	}
	
}
