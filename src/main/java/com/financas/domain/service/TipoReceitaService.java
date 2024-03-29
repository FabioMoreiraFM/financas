package com.financas.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.financas.domain.exception.EntidadeEmUsoException;
import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.exception.EnumEntidadeException;
import com.financas.domain.model.TipoReceita;
import com.financas.domain.repository.TipoReceitaRepository;

@Service
public class TipoReceitaService {
	
	@Autowired
	private TipoReceitaRepository tipoReceitaRepository;
	
	public List<TipoReceita> getTiposReceitas() {
		return tipoReceitaRepository.findAll();
	}
	
	public TipoReceita getTipoReceita(Long idTipoReceita) {
		return tipoReceitaRepository.findById(idTipoReceita)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.TipoReceita, idTipoReceita));
	}
	
	@Transactional
	public TipoReceita salvar(TipoReceita tipoReceita) {
		return tipoReceitaRepository.save(tipoReceita);
	}
	
	@Transactional
	public void remover(TipoReceita tipoReceita) {
		try {
			tipoReceitaRepository.delete(tipoReceita);
			tipoReceitaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(EnumEntidadeException.TipoReceita, tipoReceita.getId());
		}
	}
}
