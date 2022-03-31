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

import com.financas.api.assembler.TerceiroPFConverter;
import com.financas.api.assembler.TerceiroPJConverter;
import com.financas.api.model.TerceiroPFModel;
import com.financas.api.model.TerceiroPJModel;
import com.financas.domain.model.TerceiroPF;
import com.financas.domain.model.TerceiroPJ;
import com.financas.domain.service.TerceiroService;

@RestController
@RequestMapping(path = "/terceiros")
public class TerceiroController {

	@Autowired
	private TerceiroPFConverter terceiroPFConverter;
	
	@Autowired
	private TerceiroPJConverter terceiroPJConverter;
	
	@Autowired
	private TerceiroService terceiroService;
	
	@GetMapping("/pf")
	public List<TerceiroPFModel> listarPF() { 
		return terceiroPFConverter.toCollectionModel(terceiroService.listarPF());
	}
	
	@GetMapping("/pj")
	public List<TerceiroPJModel> listarPJ() { 
		return terceiroPJConverter.toCollectionModel(terceiroService.listarPJ());
	}
	
	@GetMapping("/pf/{terceiroPfId}")
	public TerceiroPFModel getByIdTerceiroPF(@PathVariable Long terceiroPfId) { 
		return terceiroPFConverter.toModel(terceiroService.getByIdTerceiroPF(terceiroPfId));
	}
	
	@GetMapping("/pj/{terceiroPJId}")
	public TerceiroPJModel getByIdTerceiroPJ(@PathVariable Long terceiroPJId) { 
		return terceiroPJConverter.toModel(terceiroService.getByIdTerceiroPJ(terceiroPJId));
	}
	
	@PostMapping("/pf")
	public TerceiroPFModel adicionarPF(@RequestBody @Valid TerceiroPFModel terceiroPFModel) { 
		TerceiroPF terceiroPF = new TerceiroPF();
		terceiroPFConverter.copyToDomainObject(terceiroPFModel, terceiroPF);
		
		terceiroPF = terceiroService.salvarTerceiroPF(terceiroPF);
		
		return terceiroPFConverter.toModel(terceiroPF);
	}
	
	@PostMapping("/pj")
	public TerceiroPJModel adicionarPJ(@RequestBody @Valid TerceiroPJModel terceiroPJModel) { 
		TerceiroPJ terceiroPJ = new TerceiroPJ();
		terceiroPJConverter.copyToDomainObject(terceiroPJModel, terceiroPJ);
		
		terceiroPJ = terceiroService.salvarTerceiroPJ(terceiroPJ);
		
		return terceiroPJConverter.toModel(terceiroPJ);
	}
	
	@DeleteMapping("/pf/{terceiroPfId}")
	public void removerPF(@PathVariable Long terceiroPfId) { 
		TerceiroPF terceiroPF = terceiroService.getByIdTerceiroPF(terceiroPfId);
		terceiroService.removerPF(terceiroPF);
	}
	
	@PostMapping("/pj/{terceiroPJId}")
	public void removerPJ(@PathVariable Long terceiroPJId) { 
		TerceiroPJ terceiroPJ = terceiroService.getByIdTerceiroPJ(terceiroPJId);
		terceiroService.removerPJ(terceiroPJ);
	}
		
}
