package com.financas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.ReceitaConverter;
import com.financas.api.assembler.ReceitaInputConverter;
import com.financas.api.model.ReceitaModel;
import com.financas.api.model.input.ReceitaInputModel;
import com.financas.domain.model.Receita;
import com.financas.domain.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
	private ReceitaConverter receitaConverter;
	
	@Autowired
	private ReceitaInputConverter receitaInputConverter;
	
	@GetMapping
	public List<ReceitaModel> listar() {
		List<Receita> receitas = receitaService.listar();
		
		return receitaConverter.toCollectionModel(receitas);
	}
	
	@GetMapping("/{receitaId}")
	public ReceitaModel getReceita(@PathVariable Long receitaId) {
		Receita receita = receitaService.getById(receitaId);
		
		return receitaConverter.toModel(receita);
	}
	
	@PostMapping
	public ReceitaModel salvar(@RequestBody @Valid ReceitaInputModel novaReceita) {
		Receita receita = receitaInputConverter.toDomainObject(novaReceita);
		receita = receitaService.salvar(receita);
		
		return receitaConverter.toModel(receita);
	}
	
	@DeleteMapping("/{receitaId}")
	public void deletar(@PathVariable Long receitaId) {
		Receita receita = receitaService.getById(receitaId);
		receitaService.deletar(receita);
	}
	
}