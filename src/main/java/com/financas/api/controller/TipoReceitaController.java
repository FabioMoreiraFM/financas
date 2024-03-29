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

import com.financas.api.assembler.GenericConverter;
import com.financas.api.controller.openapi.TipoReceitaControllerOpenApi;
import com.financas.api.model.TipoReceitaModel;
import com.financas.api.model.input.TipoReceitaInputModel;
import com.financas.domain.model.TipoReceita;
import com.financas.domain.service.TipoReceitaService;

@RestController
@RequestMapping(path = "/tipos-receitas")
public class TipoReceitaController implements TipoReceitaControllerOpenApi{
	
	@Autowired
	private GenericConverter<TipoReceitaModel, TipoReceita> tipoReceitaConverter;
	
	@Autowired
	private TipoReceitaService tipoReceitaService;
	
	@Autowired
	private GenericConverter<TipoReceitaInputModel, TipoReceita> tipoReceitaInputConverter;
	
	@GetMapping
	public List<TipoReceitaModel> listar() {
		return tipoReceitaConverter.toCollectionModel(tipoReceitaService.getTiposReceitas());
	}
	
	@GetMapping("/{idTipoReceita}")
	public TipoReceitaModel buscar(@PathVariable Long idTipoReceita) {
		return tipoReceitaConverter.toModel(tipoReceitaService.getTipoReceita(idTipoReceita));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TipoReceitaModel adicionar(@RequestBody TipoReceitaInputModel tipoReceitaModel) {
		TipoReceita tipoReceita = new TipoReceita();
		tipoReceitaInputConverter.copyToDomainObject(tipoReceitaModel, tipoReceita);
		
		tipoReceita = tipoReceitaService.salvar(tipoReceita);
		
		return tipoReceitaConverter.toModel(tipoReceita);
	}	
	
	@DeleteMapping("/{idTipoReceita}")
	public void remover(@PathVariable Long idTipoReceita) {
		TipoReceita tipoReceita = tipoReceitaService.getTipoReceita(idTipoReceita);
		tipoReceitaService.remover(tipoReceita);
	}
	
	@PutMapping("/{idTipoReceita}")
	public TipoReceitaModel atualizar(@PathVariable Long idTipoReceita,
			@RequestBody @Valid TipoReceitaInputModel tipoReceitaModel) {
		
		TipoReceita tipoReceita = tipoReceitaService.getTipoReceita(idTipoReceita);
		tipoReceitaInputConverter.copyToDomainObject(tipoReceitaModel, tipoReceita);
		tipoReceita = tipoReceitaService.salvar(tipoReceita);
		
		return tipoReceitaConverter.toModel(tipoReceita);
	}
	
}
