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
import com.financas.api.controller.openapi.DespesaControllerOpenApi;
import com.financas.api.model.DespesaModel;
import com.financas.api.model.input.DespesaInputModel;
import com.financas.domain.model.Despesa;
import com.financas.domain.model.Usuario;
import com.financas.domain.service.DespesaService;
import com.financas.infrastructure.security.FinancasSecurity;

@RestController
@RequestMapping(path = "/despesas")
public class DespesaController implements DespesaControllerOpenApi {

	@Autowired
	private DespesaConverter despesaConverter;
	
	@Autowired
	private DespesaInputConverter inputConverter;
	
	@Autowired 
	private DespesaService despesaService;
	
	@Autowired
	private FinancasSecurity financasSecurity;
	
	@GetMapping
	public List<DespesaModel> listar() {
		List<Despesa> despesas = despesaService.listar();
		
		return despesaConverter.toCollectionModel(despesas);
	}
	
	@GetMapping("/{despesaId}")
	public DespesaModel buscar(@PathVariable Long despesaId) {
		Despesa despesas = despesaService.buscar(despesaId);
		
		return despesaConverter.toModel(despesas);
	}
	
	@PostMapping
	public DespesaModel adicionar(@RequestBody @Valid DespesaInputModel novaDespesa) {
		Despesa despesa = inputConverter.toDomainObject(novaDespesa);
		despesa.setUsuario(new Usuario(financasSecurity.getUsuarioId()));
		
		despesa = despesaService.salvar(despesa);
		
		return despesaConverter.toModel(despesa); 
	}  
	
	@DeleteMapping("/{despesaId}")
	public void remover(@PathVariable Long despesaId) {
		Despesa despesas = despesaService.buscar(despesaId);
		
		despesaService.remover(despesas);
	}
	
}
