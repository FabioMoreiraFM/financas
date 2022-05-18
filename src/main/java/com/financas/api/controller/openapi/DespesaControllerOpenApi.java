package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.model.DespesaModel;
import com.financas.api.model.input.DespesaInputModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Despesas")
public interface DespesaControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de despesas do usuário.")
	public List<DespesaModel> listar();
	
	@ApiOperation("Busca uma despesa por ID")
	public DespesaModel buscar(@PathVariable Long despesaId) ;
	
	@ApiOperation("Adiciona um nova despesa")
	public DespesaModel adicionar(@RequestBody @Valid DespesaInputModel novaDespesa);
	
	@ApiOperation("Remove uma despesa por ID")
	public void remover(@PathVariable Long despesaId);
	
}
