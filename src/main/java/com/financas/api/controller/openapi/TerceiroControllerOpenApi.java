package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.model.TerceiroModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Terceiros")
public interface TerceiroControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de terceiros.")
	public List<TerceiroModel> listar();
	
	@ApiOperation("Busca um terceiro por ID.")
	public TerceiroModel buscar(@PathVariable Long terceiroId);
	
	@ApiOperation("Adiciona um terceiro por ID.")
	public TerceiroModel adicionar(@RequestBody @Valid TerceiroModel terceiroModel);
	
	@ApiOperation("Remove um terceiro por ID.")
	public void remover(@PathVariable Long terceiroId);
	
}
