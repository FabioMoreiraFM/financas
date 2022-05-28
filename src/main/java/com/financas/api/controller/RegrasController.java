package com.financas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.GenericConverter;
import com.financas.api.controller.openapi.RegrasControllerOpenApi;
import com.financas.api.model.RegrasModel;
import com.financas.api.model.input.RegrasInputModel;
import com.financas.domain.model.Regras;
import com.financas.domain.service.RegrasService;

@RestController
@RequestMapping(path = "/regras")
public class RegrasController implements RegrasControllerOpenApi{
	
	@Autowired
	private RegrasService regrasService;
	
	@Autowired
	private GenericConverter<RegrasModel, Regras> regrasConverter;
	
	@Autowired
	private GenericConverter<RegrasInputModel, Regras> regrasInputConverter;
	
	@GetMapping
	public List<RegrasModel> listar() {
		return regrasConverter.toCollectionModel(regrasService.listar());
	}
	
	@GetMapping("/{regrasId}")
	public RegrasModel buscar(@PathVariable Long regrasId) {
		return regrasConverter.toModel(regrasService.buscar(regrasId));
	}
	
	@PutMapping("/{regrasId}")
	public RegrasModel atualizar(@PathVariable Long regrasId,
			@RequestBody @Valid RegrasInputModel regrasAtualizadas) {
		
		Regras regras = regrasService.buscar(regrasId);
		regrasInputConverter.copyToDomainObject(regrasAtualizadas, regras);
		
		regrasService.salvar(regras);
		
		return regrasConverter.toModel(regras);
	}
	
	
}
