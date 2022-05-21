package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.exceptionHandler.ProblemDetails;
import com.financas.api.model.RegrasModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Regras")
public interface RegrasControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de regras do sistema")
	public List<RegrasModel> listar();
	
	@ApiOperation("Busca uma regra por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da regra inválida", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Regra não encontrada", response = ProblemDetails.class)
	})
	public RegrasModel buscar(@PathVariable Long regrasId);
	
	@ApiOperation("Atualiza uma regra por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da regra inválida", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Regra não encontrada", response = ProblemDetails.class)
	})
	public RegrasModel atualizar(@PathVariable Long regrasId,
			@RequestBody @Valid RegrasModel regrasAtualizadas);
	
}
