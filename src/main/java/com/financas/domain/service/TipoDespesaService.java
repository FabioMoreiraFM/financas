package com.financas.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.financas.domain.exception.EntidadeEmUsoException;
import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.exception.EnumEntidadeException;
import com.financas.domain.model.TipoDespesa;
import com.financas.domain.repository.TipoDespesaRepository;

@Service
public class TipoDespesaService {

	@Autowired
	private TipoDespesaRepository tipoDespesaRepository;
	
	public List<TipoDespesa> getTiposDespesas() {
		return tipoDespesaRepository.findAll();
	}
	
	public TipoDespesa buscar(Long idTipoDespesa) {
		return tipoDespesaRepository.findById(idTipoDespesa)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.TipoDespesa, idTipoDespesa));
	}
	
	@Transactional
	public TipoDespesa salvar(TipoDespesa tipoDespesa) {
		return tipoDespesaRepository.save(tipoDespesa);
	}
	
	@Transactional
	public void remover(TipoDespesa tipoDespesa) {
		try {
			tipoDespesaRepository.delete(tipoDespesa);
			tipoDespesaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(EnumEntidadeException.TipoDespesa, tipoDespesa.getId());
		}
	}
	
}
