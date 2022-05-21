package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.exceptionHandler.ProblemDetails;
import com.financas.api.model.TipoReceitaModel;
import com.financas.domain.model.TipoReceita;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Tipos de Receita")
public interface TipoReceitaControllerOpenApi {
	
	@ApiOperation("Lista o conjunto de tipos de receita.")
	public List<TipoReceitaModel> listar();
	
	@ApiOperation("Busca um tipo de receita por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de receita inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de receita não encontrado", response = ProblemDetails.class)
	})
	public TipoReceitaModel buscar(@PathVariable Long idTipoReceita);
	
	@ApiOperation("Adiciona um tipo de receita por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de receita inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de receita não encontrado", response = ProblemDetails.class)
	})
	public TipoReceitaModel adicionar(@RequestBody TipoReceita tipoReceita);
	
	@ApiOperation("Remove um tipo de receita por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de receita inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de receita não encontrado", response = ProblemDetails.class)
	})
	public void remover(@PathVariable Long idTipoReceita);
	
	@ApiOperation("Atualiza um tipo de receita por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do tipo de receita inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Tipo de receita não encontrado", response = ProblemDetails.class)
	})
	public TipoReceitaModel atualizar(@PathVariable Long idTipoReceita,
			@RequestBody @Valid TipoReceitaModel tipoReceitaModel);
}
