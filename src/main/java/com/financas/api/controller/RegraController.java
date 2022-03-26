package com.financas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.RegrasConverter;
import com.financas.api.model.RegrasModel;
import com.financas.domain.model.Regras;
import com.financas.domain.service.RegrasService;

@RestController
@RequestMapping(path = "/regras")
public class RegraController {
	
	@Autowired
	private RegrasService regrasService;
	
	@Autowired
	private RegrasConverter regrasConverter; 
	
	@GetMapping
	public List<Regras> listar() {
		return regrasService.listar();
	}
	
	@GetMapping("/{regrasId}")
	public Regras getById(@PathVariable Long regrasId) {
		return regrasService.getRegras(regrasId);
	}
	
	@PutMapping("/{regrasId}")
	public RegrasModel atualizar(@PathVariable Long regrasId,
			@RequestBody RegrasModel regrasAtualizadas) {
		
		Regras regras = regrasService.getRegras(regrasId);
		regrasConverter.copyToDomainObject(regrasAtualizadas, regras);
		
		regrasService.salvar(regras);
		
		return regrasConverter.toModel(regras);
	}
	
	
}
