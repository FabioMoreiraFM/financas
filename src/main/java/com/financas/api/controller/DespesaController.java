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

import com.financas.api.assembler.DespesaConverter;
import com.financas.api.assembler.DespesaInputConverter;
import com.financas.api.model.DespesaModel;
import com.financas.api.model.input.DespesaInputModel;
import com.financas.domain.model.Despesa;
import com.financas.domain.service.DespesaService;

@RestController
@RequestMapping(path = "/despesas")
public class DespesaController {

	@Autowired
	private DespesaConverter despesaConverter;
	
	@Autowired
	private DespesaInputConverter inputConverter;
	
	@Autowired 
	private DespesaService despesaService;
	
	@GetMapping
	public List<DespesaModel> listar() {
		List<Despesa> despesas = despesaService.listar();
		
		return despesaConverter.toCollectionModel(despesas);
	}
	
	@GetMapping("/{despesaId}")
	public DespesaModel getById(@PathVariable Long despesaId) {
		Despesa despesas = despesaService.getDespesa(despesaId);
		
		return despesaConverter.toModel(despesas);
	}
	
	@PostMapping
	public DespesaModel salvar(@RequestBody @Valid DespesaInputModel novaDespesa) {
		Despesa despesa = inputConverter.toDomainObject(novaDespesa);
		despesa = despesaService.salvar(despesa);
		
		return despesaConverter.toModel(despesa); 
	}  
	
	@DeleteMapping("/{despesaId}")
	public void deletar(@PathVariable Long despesaId) {
		Despesa despesas = despesaService.getDespesa(despesaId);
		
		despesaService.deletar(despesas);
	}
	
}
