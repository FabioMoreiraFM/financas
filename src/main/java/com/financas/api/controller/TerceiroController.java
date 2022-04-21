package com.financas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.TerceiroConverter;
import com.financas.api.model.TerceiroModel;
import com.financas.domain.model.Terceiro;
import com.financas.domain.service.TerceiroService;

@RestController
@RequestMapping(path = "/terceiros")
public class TerceiroController {

	@Autowired
	private TerceiroConverter terceiroConverter;
	
	@Autowired
	private TerceiroService terceiroService;
	
	@GetMapping
	public List<TerceiroModel> listar() { 
		return terceiroConverter.toCollectionModel(terceiroService.listar());
	}
	
	@GetMapping("/{terceiroId}")
	public TerceiroModel getByIdTerceiro(@PathVariable Long terceiroId) { 
		return terceiroConverter.toModel(terceiroService.getByIdTerceiro(terceiroId));
	}
	
	@PostMapping
	public TerceiroModel adicionar(@RequestBody @Valid TerceiroModel terceiroModel) { 
		Terceiro terceiro = new Terceiro();
		terceiroConverter.copyToDomainObject(terceiroModel, terceiro);
		
		terceiro = terceiroService.salvarTerceiro(terceiro);
		
		return terceiroConverter.toModel(terceiro);
	}
	
	@DeleteMapping("/{terceiroId}")
	public void remover(@PathVariable Long terceiroId) { 
		Terceiro terceiro = terceiroService.getByIdTerceiro(terceiroId);
		terceiroService.remover(terceiro);
	}
		
}