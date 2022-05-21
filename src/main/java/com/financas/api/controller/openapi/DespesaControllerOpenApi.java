package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.exceptionHandler.ProblemDetails;
import com.financas.api.model.DespesaModel;
import com.financas.api.model.input.DespesaInputModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Despesas")
public interface DespesaControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de despesas do usuário.")
	public List<DespesaModel> listar();
	
	@ApiOperation("Busca uma despesa por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da despesa inválida", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Despesa não encontrada", response = ProblemDetails.class)
	})
	public DespesaModel buscar(@PathVariable Long despesaId) ;
	
	@ApiOperation("Adiciona um nova despesa")
	public DespesaModel adicionar(@RequestBody @Valid DespesaInputModel novaDespesa);
	
	@ApiOperation("Remove uma despesa por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da despesa inválida", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Despesa não encontrada", response = ProblemDetails.class)
	})
	public void remover(@PathVariable Long despesaId);
	
}
