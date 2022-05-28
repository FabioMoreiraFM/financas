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

import com.financas.api.assembler.GenericConverter;
import com.financas.api.controller.openapi.TipoDespesaControllerOpenApi;
import com.financas.api.model.TipoDespesaModel;
import com.financas.api.model.input.TipoDespesaInputModel;
import com.financas.domain.model.TipoDespesa;
import com.financas.domain.service.TipoDespesaService;

@RestController
@RequestMapping(path = "/tipos-despesas")
public class TipoDespesaController implements TipoDespesaControllerOpenApi{

	@Autowired
	private TipoDespesaService tipoDespesaService;
	
	@Autowired 
	private GenericConverter<TipoDespesaModel, TipoDespesa> tipoDespesaConverter;
	
	@Autowired 
	private GenericConverter<TipoDespesaInputModel, TipoDespesa> tipoDespesaInputConverter;
	
	@GetMapping
	public List<TipoDespesaModel> listar() {
		return tipoDespesaConverter.toCollectionModel(tipoDespesaService.getTiposDespesas());
	}
	
	@GetMapping("/{tipoDespesaId}")
	public TipoDespesaModel buscar(@PathVariable Long tipoDespesaId) {
		return tipoDespesaConverter.toModel(tipoDespesaService.buscar(tipoDespesaId));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TipoDespesaModel adicionar(@RequestBody @Valid TipoDespesaInputModel tipoDespesaModel) {
		TipoDespesa tipoDespesa = new TipoDespesa();
		tipoDespesaInputConverter.copyToDomainObject(tipoDespesaModel, tipoDespesa);
		
		tipoDespesa = tipoDespesaService.salvar(tipoDespesa);
		return tipoDespesaConverter.toModel(tipoDespesa);
	}
	
	@PutMapping("/{tipoDespesaId}")
	public TipoDespesaModel atualizar(@PathVariable Long tipoDespesaId,
			@RequestBody @Valid TipoDespesaInputModel tipoDespesaModel) {
		
		TipoDespesa tipoDespesa = tipoDespesaService.buscar(tipoDespesaId);
		tipoDespesaInputConverter.copyToDomainObject(tipoDespesaModel, tipoDespesa);
		tipoDespesa = tipoDespesaService.salvar(tipoDespesa);		
		
		return tipoDespesaConverter.toModel(tipoDespesa);
	}
	
	@DeleteMapping("/{tipoDespesaId}")
	public void remover(@PathVariable Long tipoDespesaId) {
		TipoDespesa tipoDespesa = tipoDespesaService.buscar(tipoDespesaId);
		tipoDespesaService.remover(tipoDespesa);
	}
	
	
}
