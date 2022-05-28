package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.exceptionHandler.ProblemDetails;
import com.financas.api.model.UsuarioModel;
import com.financas.api.model.input.UsuarioInputComSenhaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {
	
	@ApiOperation("Lista todos os usuários do sistema.")
	public List<UsuarioModel> listar();
	
	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = ProblemDetails.class)
	})
	public UsuarioModel buscar(@PathVariable Long usuarioId);
	
	@ApiOperation("Adiciona um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = ProblemDetails.class)
	})
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInputComSenhaModel usuarioNovo);
	
	@ApiOperation("Atualiza um tipo de receita por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = ProblemDetails.class)
	})
	public UsuarioModel atualizar(@PathVariable Long usuarioId,
			@RequestBody @Valid UsuarioInputComSenhaModel usuarioAtualizado);
	
	@ApiOperation("Remove um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = ProblemDetails.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = ProblemDetails.class)
	})
	public void remover(@PathVariable Long usuarioId);
	
}
