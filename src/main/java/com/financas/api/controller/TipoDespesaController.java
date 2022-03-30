package com.financas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.TipoDespesaConverter;
import com.financas.api.model.TipoDespesaModel;
import com.financas.domain.model.TipoDespesa;
import com.financas.domain.service.TipoDespesaService;

@RestController
@RequestMapping(path = "/tipos-despesas")
public class TipoDespesaController {

	@Autowired
	private TipoDespesaService tipoDespesaService;
	
	@Autowired 
	private TipoDespesaConverter tipoDespesaConverter;
	
	@GetMapping
	public List<TipoDespesa> listar() {
		return tipoDespesaService.getTiposDespesas();
	}
	
	@GetMapping("/{tipoDespesaId}")
	public TipoDespesa getById(@PathVariable Long tipoDespesaId) {
		return tipoDespesaService.getTipoDespesa(tipoDespesaId);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TipoDespesaModel adicionar(@RequestBody @Valid TipoDespesaModel tipoDespesaModel) {
		TipoDespesa tipoDespesa = new TipoDespesa();
		tipoDespesaConverter.copyToDomainObject(tipoDespesaModel, tipoDespesa);
		
		tipoDespesa = tipoDespesaService.salvar(tipoDespesa);
		return tipoDespesaConverter.toModel(tipoDespesa);
	}
	
	@PutMapping("/{tipoDespesaId}")
	public TipoDespesaModel atualizar(@PathVariable Long tipoDespesaId,
			@RequestBody @Valid TipoDespesaModel tipoDespesaModel) {
		
		TipoDespesa tipoDespesa = tipoDespesaService.getTipoDespesa(tipoDespesaId);
		tipoDespesaConverter.copyToDomainObject(tipoDespesaModel, tipoDespesa);
		tipoDespesa = tipoDespesaService.salvar(tipoDespesa);		
		
		return tipoDespesaConverter.toModel(tipoDespesa);
	}
	
	@DeleteMapping("/{tipoDespesaId}")
	public void remover(@PathVariable Long tipoDespesaId) {
		TipoDespesa tipoDespesa = tipoDespesaService.getTipoDespesa(tipoDespesaId);
		tipoDespesaService.delete(tipoDespesa);
	}
	
	
}
