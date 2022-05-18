package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.model.RegrasModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Regras")
public interface RegrasControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de regras do sistema")
	public List<RegrasModel> listar();
	
	@ApiOperation("Busca uma regra por ID")
	public RegrasModel buscar(@PathVariable Long regrasId);
	
	@ApiOperation("Atualiza uma regra por ID")
	public RegrasModel atualizar(@PathVariable Long regrasId,
			@RequestBody @Valid RegrasModel regrasAtualizadas);
	
}
