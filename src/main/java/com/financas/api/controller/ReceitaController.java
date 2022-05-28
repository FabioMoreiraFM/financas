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

import com.financas.api.assembler.GenericConverter;
import com.financas.api.controller.openapi.ReceitaControllerOpenApi;
import com.financas.api.model.ReceitaModel;
import com.financas.api.model.input.ReceitaInputModel;
import com.financas.domain.model.Receita;
import com.financas.domain.model.Usuario;
import com.financas.domain.service.ReceitaService;
import com.financas.infrastructure.security.FinancasSecurity;

@RestController
@RequestMapping("/receitas")
public class ReceitaController implements ReceitaControllerOpenApi{

	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
	private GenericConverter<ReceitaModel, Receita> receitaConverter;
	
	@Autowired
	private GenericConverter<ReceitaInputModel, Receita> receitaInputConverter;
	
	@Autowired
	private FinancasSecurity financasSecurity;
	
	@GetMapping
	public List<ReceitaModel> listar() {
		List<Receita> receitas = receitaService.listar();
		
		return receitaConverter.toCollectionModel(receitas);
	}
	
	@GetMapping("/{receitaId}")
	public ReceitaModel buscar(@PathVariable Long receitaId) {
		Receita receita = receitaService.buscar(receitaId);
		
		return receitaConverter.toModel(receita);
	}
	
	@PostMapping
	public ReceitaModel adicionar(@RequestBody @Valid ReceitaInputModel novaReceita) {
		Receita receita = receitaInputConverter.toDomainObject(novaReceita);
		receita.setUsuario(new Usuario(financasSecurity.getUsuarioId()));
		
		receita = receitaService.salvar(receita);
		
		return receitaConverter.toModel(receita);
	}
	
	@DeleteMapping("/{receitaId}")
	public void remover(@PathVariable Long receitaId) {
		Receita receita = receitaService.buscar(receitaId);
		receitaService.remover(receita);
	}
	
}