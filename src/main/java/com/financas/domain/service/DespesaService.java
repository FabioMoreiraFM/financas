package com.financas.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.exception.EnumEntidadeException;
import com.financas.domain.model.Despesa;
import com.financas.domain.model.Terceiro;
import com.financas.domain.model.TipoDespesa;
import com.financas.domain.model.Usuario;
import com.financas.domain.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TerceiroService terceiroService;
	
	@Autowired
	private TipoDespesaService tipoDespesaService;
	
	public List<Despesa> listar() {
		return despesaRepository.findAll();
	}
	
	public Despesa buscar(Long despesaId) {
		return despesaRepository.findById(despesaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.Despesas, despesaId));
	}
	
	public Despesa salvar(Despesa despesa) {
		Usuario usuario = usuarioService.buscar(despesa.getUsuario().getId());
		Terceiro credor = terceiroService.buscar(despesa.getCredor().getId());
		TipoDespesa tipoDespesa = tipoDespesaService.buscar(despesa.getTipoDespesa().getId());
		
		despesa.setUsuario(usuario);
		despesa.setCredor(credor);
		despesa.setTipoDespesa(tipoDespesa);
		
		return despesaRepository.save(despesa);
	}

	public void remover(Despesa despesas) {
		despesaRepository.delete(despesas);
	}
	
}
