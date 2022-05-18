package com.financas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.UsuarioConverter;
import com.financas.api.controller.openapi.UsuarioControllerOpenApi;
import com.financas.api.model.NovoUsuarioModel;
import com.financas.api.model.UsuarioModel;
import com.financas.domain.model.Usuario;
import com.financas.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi{

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioConverter usuarioConverter;
	
	@GetMapping
	public List<UsuarioModel> listar() {
		List<Usuario> usuarios = usuarioService.getUsuarios();
		
		return usuarioConverter.toCollectionModel(usuarios);
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscar(usuarioId);
		
		return usuarioConverter.toModel(usuario);
	}
	
	@PostMapping
	public UsuarioModel adicionar(@RequestBody @Valid NovoUsuarioModel usuarioNovo) {
		Usuario usuario = usuarioConverter.toDomainObject(usuarioNovo);
		usuario = usuarioService.salvar(usuario);
		
		return usuarioConverter.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId,
			@RequestBody @Valid UsuarioModel usuarioAtualizado) {
		
		Usuario usuario = usuarioService.buscar(usuarioId);
		
		usuarioConverter.copyToDomainObject(usuarioAtualizado, usuario);
		
		usuario = usuarioService.salvar(usuario);
		
		return usuarioConverter.toModel(usuario);
	}
	
	@DeleteMapping("/{usuarioId}")
	public void remover(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscar(usuarioId);
		usuarioService.remover(usuario);
	}
	
	
}
