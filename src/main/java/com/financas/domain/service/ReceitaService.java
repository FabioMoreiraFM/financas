package com.financas.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.domain.model.Receita;
import com.financas.domain.model.Terceiro;
import com.financas.domain.model.TipoReceita;
import com.financas.domain.model.Usuario;
import com.financas.domain.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private TerceiroService terceiroService;
	
	@Autowired
	private TipoReceitaService tipoReceitaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Receita> listar() {
		return receitaRepository.findAll();
	}
	
	public Receita getById(Long receitaId) {
		return receitaRepository.getById(receitaId);
	}
	
	public Receita salvar(Receita receita) {
		Terceiro fonte = terceiroService.getByIdTerceiro(receita.getFonte().getId());
		Usuario usuario = usuarioService.getUsuario(receita.getUsuario().getId());
		TipoReceita tipoReceita = tipoReceitaService.getTipoReceita(receita.getTipoReceita().getId());
		
		receita.setFonte(fonte);
		receita.setUsuario(usuario);
		receita.setTipoReceita(tipoReceita);
		
		return receitaRepository.save(receita);
	}

	public void deletar(Receita receita) {
		receitaRepository.delete(receita);
	}
	
}
