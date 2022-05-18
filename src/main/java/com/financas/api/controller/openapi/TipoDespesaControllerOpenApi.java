package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.model.TipoDespesaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Tipos de Despesa")
public interface TipoDespesaControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de tipos de despesa.")
	public List<TipoDespesaModel> listar();
	
	@ApiOperation("Busca um tipo de despesa por ID")
	public TipoDespesaModel buscar(@PathVariable Long tipoDespesaId) ;
	
	@ApiOperation("Adiciona um tipo de despesa por ID")
	public TipoDespesaModel adicionar(@RequestBody @Valid TipoDespesaModel tipoDespesaModel);
	
	@ApiOperation("Atualiza um tipo de despesa por ID")
	public TipoDespesaModel atualizar(@PathVariable Long tipoDespesaId,
			@RequestBody @Valid TipoDespesaModel tipoDespesaModel);
	
	@ApiOperation("Remove um tipo de despesa por ID")
	public void remover(@PathVariable Long tipoDespesaId);
	
}
