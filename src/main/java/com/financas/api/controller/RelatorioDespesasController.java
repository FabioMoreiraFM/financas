package com.financas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.domain.model.Despesa;
import com.financas.domain.service.DespesaService;
import com.financas.domain.service.reports.ExtratoDespesasService;

@RestController
@RequestMapping("/extrato-despesas")
public class RelatorioDespesasController {

	@Autowired
	private ExtratoDespesasService extratoDespesasService;
	
	@Autowired
	private DespesaService despesaService;
	
	@GetMapping(path = "/{despesaId}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> emitirExtratoDespesasPDF(
				@PathVariable Long despesaId
			) {
		
		Despesa despesa = despesaService.buscar(despesaId);
		
		byte[] bytesPdf = extratoDespesasService.emitirExtratoDespesas(despesa);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=extrato-despesas.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}
	
}
