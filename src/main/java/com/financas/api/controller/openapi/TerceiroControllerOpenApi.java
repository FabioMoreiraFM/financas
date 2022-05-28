package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.exceptionHandler.ProblemDetails;
import com.financas.api.model.TerceiroModel;
import com.financas.api.model.input.TerceiroInputModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Terceiros")
public interface TerceiroControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de terceiros.")
	public List<TerceiroModel> listar();
	
	@ApiOperation("Busca um terceiro por ID.")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do terceiro inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Terceiro não encontrado", response = ProblemDetails.class)
	})
	public TerceiroModel buscar(@PathVariable Long terceiroId);
	
	@ApiOperation("Adiciona um terceiro por ID.")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do terceiro inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Terceiro não encontrado", response = ProblemDetails.class)
	})
	public TerceiroModel adicionar(@RequestBody @Valid TerceiroInputModel terceiroModel);
	
	@ApiOperation("Remove um terceiro por ID.")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do terceiro inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Terceiro não encontrado", response = ProblemDetails.class)
	})
	public void remover(@PathVariable Long terceiroId);
	
}
