package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.exceptionHandler.ProblemDetails;
import com.financas.api.model.TipoDespesaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Tipos de Despesa")
public interface TipoDespesaControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de tipos de despesa.")
	public List<TipoDespesaModel> listar();
	
	@ApiOperation("Busca um tipo de despesa por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de despesa inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de despesa não encontrado", response = ProblemDetails.class)
	})
	public TipoDespesaModel buscar(@PathVariable Long tipoDespesaId) ;
	
	@ApiOperation("Adiciona um tipo de despesa por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de despesa inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de despesa não encontrado", response = ProblemDetails.class)
	})
	public TipoDespesaModel adicionar(@RequestBody @Valid TipoDespesaModel tipoDespesaModel);
	
	@ApiOperation("Atualiza um tipo de despesa por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de despesa inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de despesa não encontrado", response = ProblemDetails.class)
	})
	public TipoDespesaModel atualizar(@PathVariable Long tipoDespesaId,
			@RequestBody @Valid TipoDespesaModel tipoDespesaModel);
	
	@ApiOperation("Remove um tipo de despesa por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de despesa inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de despesa não encontrado", response = ProblemDetails.class)
	})
	public void remover(@PathVariable Long tipoDespesaId);
	
}
