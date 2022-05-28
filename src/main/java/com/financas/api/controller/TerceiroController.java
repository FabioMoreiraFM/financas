package com.financas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.GenericConverter;
import com.financas.api.controller.openapi.TerceiroControllerOpenApi;
import com.financas.api.model.TerceiroModel;
import com.financas.api.model.input.TerceiroInputModel;
import com.financas.domain.model.Terceiro;
import com.financas.domain.service.TerceiroService;

@RestController
@RequestMapping(path = "/terceiros")
public class TerceiroController implements TerceiroControllerOpenApi{

	@Autowired
	private GenericConverter<TerceiroModel, Terceiro> terceiroConverter;
	
	@Autowired
	private TerceiroService terceiroService;
	
	@Autowired
	private GenericConverter<TerceiroInputModel, Terceiro> terceiroInputConverter;
	
	@GetMapping
	public List<TerceiroModel> listar() { 
		return terceiroConverter.toCollectionModel(terceiroService.listar());
	}
	
	@GetMapping("/{terceiroId}")
	public TerceiroModel buscar(@PathVariable Long terceiroId) { 
		return terceiroConverter.toModel(terceiroService.buscar(terceiroId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TerceiroModel adicionar(@RequestBody @Valid TerceiroInputModel terceiroModel) { 
		Terceiro terceiro = new Terceiro();
		terceiroInputConverter.copyToDomainObject(terceiroModel, terceiro);
		
		terceiro = terceiroService.salvar(terceiro);
		
		return terceiroConverter.toModel(terceiro);
	}
	
	@DeleteMapping("/{terceiroId}")
	public void remover(@PathVariable Long terceiroId) { 
		Terceiro terceiro = terceiroService.buscar(terceiroId);
		terceiroService.remover(terceiro);
	}
		
}
