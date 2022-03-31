package com.financas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.TipoReceitaConverter;
import com.financas.api.model.TipoReceitaModel;
import com.financas.domain.model.TipoReceita;
import com.financas.domain.service.TipoReceitaService;

@RestController
@RequestMapping(path = "/tipos-receitas")
public class TipoReceitaController {
	
	@Autowired
	private TipoReceitaConverter tipoReceitaConverter;
	
	@Autowired
	private TipoReceitaService tipoReceitaService;
	
	@GetMapping
	public List<TipoReceitaModel> listar() {
		return tipoReceitaConverter.toCollectionModel(tipoReceitaService.getTiposReceitas());
	}
	
	@GetMapping("/{idTipoReceita}")
	public TipoReceitaModel getById(@PathVariable Long idTipoReceita) {
		return tipoReceitaConverter.toModel(tipoReceitaService.getTipoReceita(idTipoReceita));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TipoReceitaModel adicionar(@RequestBody TipoReceita tipoReceita) {
		tipoReceita = tipoReceitaService.salvar(tipoReceita);
		
		return tipoReceitaConverter.toModel(tipoReceita);
	}
	
	@DeleteMapping("/{idTipoReceita}")
	public void remover(@PathVariable Long idTipoReceita) {
		TipoReceita tipoReceita = tipoReceitaService.getTipoReceita(idTipoReceita);
		tipoReceitaService.delete(tipoReceita);
	}
	
	@PutMapping("/{idTipoReceita}")
	public TipoReceitaModel atualizar(@PathVariable Long idTipoReceita,
			@RequestBody @Valid TipoReceitaModel tipoReceitaModel) {
		
		TipoReceita tipoReceita = tipoReceitaService.getTipoReceita(idTipoReceita);
		tipoReceitaConverter.copyToDomainObject(tipoReceitaModel, tipoReceita);
		tipoReceita = tipoReceitaService.salvar(tipoReceita);
		
		return tipoReceitaConverter.toModel(tipoReceita);
	}
	
}
