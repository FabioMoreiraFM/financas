package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.model.TipoReceitaModel;
import com.financas.domain.model.TipoReceita;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Tipos de Receita")
public interface TipoReceitaControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de tipos de receita.")
	public List<TipoReceitaModel> listar();
	
	@ApiOperation("Busca um tipo de receita por ID")
	public TipoReceitaModel buscar(@PathVariable Long idTipoReceita);
	
	@ApiOperation("Adiciona um tipo de receita por ID")
	public TipoReceitaModel adicionar(@RequestBody TipoReceita tipoReceita);
	
	@ApiOperation("Remove um tipo de receita por ID")
	public void remover(@PathVariable Long idTipoReceita);
	
	@ApiOperation("Atualiza um tipo de receita por ID")
	public TipoReceitaModel atualizar(@PathVariable Long idTipoReceita,
			@RequestBody @Valid TipoReceitaModel tipoReceitaModel);
}
