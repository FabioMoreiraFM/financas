package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.model.ReceitaModel;
import com.financas.api.model.input.ReceitaInputModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Receitas")
public interface ReceitaControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de receitas do usuário.")
	public List<ReceitaModel> listar();
	
	@ApiOperation("Busca uma receita por ID")
	public ReceitaModel buscar(@PathVariable Long receitaId);
	
	@ApiOperation("Adiciona um nova receita")
	public ReceitaModel adicionar(@RequestBody @Valid ReceitaInputModel novaReceita);
	
	@ApiOperation("Remove uma receita por ID")
	public void remover(@PathVariable Long receitaId);
	
}
