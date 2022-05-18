package com.financas.api.controller.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.financas.api.model.NovoUsuarioModel;
import com.financas.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {
	
	@ApiOperation("Lista todos os usuários do sistema.")
	public List<UsuarioModel> listar();
	
	@ApiOperation("Busca um usuário por ID")
	public UsuarioModel buscar(@PathVariable Long usuarioId);
	
	@ApiOperation("Adiciona um usuário por ID")
	public UsuarioModel adicionar(@RequestBody @Valid NovoUsuarioModel usuarioNovo);
	
	@ApiOperation("Atualiza um tipo de receita por ID")
	public UsuarioModel atualizar(@PathVariable Long usuarioId,
			@RequestBody @Valid UsuarioModel usuarioAtualizado);
	
	@ApiOperation("Remove um usuário por ID")
	public void remover(@PathVariable Long usuarioId);
	
}
