package com.financas.api.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financas.api.assembler.GenericConverter;
import com.financas.api.controller.openapi.DespesaControllerOpenApi;
import com.financas.api.model.DespesaModel;
import com.financas.api.model.input.DespesaInputModel;
import com.financas.api.model.input.ListaParcelaDespesaInputModel;
import com.financas.domain.model.Despesa;
import com.financas.domain.model.ParcelaDespesa;
import com.financas.domain.model.Usuario;
import com.financas.domain.service.DespesaService;
import com.financas.domain.service.ParcelaDespesaService;
import com.financas.infrastructure.security.FinancasSecurity;

@RestController
@RequestMapping(path = "/despesas")
public class DespesaController implements DespesaControllerOpenApi {

	@Autowired
	private GenericConverter<DespesaModel, Despesa> despesaConverter;
	
	@Autowired
	private GenericConverter<DespesaInputModel, Despesa> inputConverter;
	
	@Autowired 
	private DespesaService despesaService;
	
	@Autowired
	private FinancasSecurity financasSecurity;
	
	@Autowired
	private ParcelaDespesaService parcelaDespesaService;
	
	@GetMapping
	public List<DespesaModel> listar() {
		List<Despesa> despesas = despesaService.listar();
		
		return despesaConverter.toCollectionModel(despesas);
	}
	
	@GetMapping("/{despesaId}")
	public DespesaModel buscar(@PathVariable Long despesaId) {
		Despesa despesas = despesaService.buscar(despesaId);
		
		return despesaConverter.toModel(despesas);
	}
	
	@PostMapping
	public DespesaModel adicionar(@RequestBody @Valid DespesaInputModel novaDespesa) {
		Despesa despesa = inputConverter.toDomainObject(novaDespesa);
		despesa.setUsuario(new Usuario(financasSecurity.getUsuarioId()));
		
		despesa = despesaService.salvar(despesa);
		
		return despesaConverter.toModel(despesa); 
	}
	
	@PostMapping("/{despesaId}/baixa/{parcelaId}")
	public void baixarParcela(@PathVariable Long despesaId,
			@PathVariable Long parcelaId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtPagamento
			) {
		
		Despesa despesas = despesaService.buscar(despesaId);
		
		ParcelaDespesa parcelaApagar = parcelaDespesaService.getParcelaABaixar(despesas, parcelaId);
		parcelaDespesaService.baixar(parcelaApagar, dtPagamento);
	}
	
	@PostMapping("/{despesaId}/baixa")
	public void baixarParcelas(@PathVariable Long despesaId,
			@RequestBody ListaParcelaDespesaInputModel parcelasABaixar) {
		
		Despesa despesas = despesaService.buscar(despesaId);
				
		parcelaDespesaService.baixarEmLote(despesas, parcelasABaixar);
	}
	
	@DeleteMapping("/{despesaId}")
	public void remover(@PathVariable Long despesaId) {
		Despesa despesas = despesaService.buscar(despesaId);
		
		despesaService.remover(despesas);
	}
	
}
